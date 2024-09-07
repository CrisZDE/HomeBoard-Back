package com.homeboard.homeboard.config;

import static com.homeboard.homeboard.config.ConstansSecurity.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
// import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{


	public static Key getSigningKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    private Claims parseClaims(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY);
        if (jwtToken != null && jwtToken.startsWith(TOKEN_BEARER_PREFIX)) {
            jwtToken = jwtToken.replace(TOKEN_BEARER_PREFIX, "");
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(SECRET_KEY))
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        }
        return null;
    }

    private void setAuthentication(Claims claims) {
        Object authoritiesObj = claims.get("authorities");
        List<String> authorities = null;

        if (authoritiesObj instanceof List<?>) {
            authorities = ((List<?>) authoritiesObj).stream()
                            .filter(String.class::isInstance)
                            .map(String.class::cast)
                            .collect(Collectors.toList());
        }
        if (authorities != null) {
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), 
                    null, 
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
    

    private boolean isJWTValid(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_BEARER_PREFIX);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        try {
            if (isJWTValid(request)) {
                Claims claims = parseClaims(request);
                setAuthentication(claims);
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}
