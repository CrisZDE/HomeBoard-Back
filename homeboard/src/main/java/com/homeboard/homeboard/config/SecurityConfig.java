package com.homeboard.homeboard.config;
import static com.homeboard.homeboard.config.ConstansSecurity.*;
import static org.springframework.security.config.Customizer.withDefaults;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.common.lang.NonNull;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter){
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                                .requestMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                                .requestMatchers(HttpMethod.POST, SIGNIN_URL).permitAll()
                                .requestMatchers(HttpMethod.GET, PUBLIC_IDEAS_URL).authenticated()
                                .requestMatchers(HttpMethod.GET, BOARDS_URL).authenticated()
                                .requestMatchers(HttpMethod.GET, BOARD_ID_URL).authenticated()
                                .requestMatchers(HttpMethod.GET, USER_IDEAS_URL).authenticated()
                                .requestMatchers(HttpMethod.POST, NEW_IDEA_URL).authenticated()
                                .requestMatchers(HttpMethod.PUT, UPDATE_IDEA_URL).authenticated()
                                .requestMatchers(HttpMethod.DELETE, DELETE_IDEA_URL).authenticated()
                                .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar BCrypt para cifrar las contraseñas
    }


    @Configuration
    public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(LOCALHOST_FRONT_URL)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
            }
    }
}
