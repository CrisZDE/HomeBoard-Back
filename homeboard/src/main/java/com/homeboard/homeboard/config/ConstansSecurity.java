package com.homeboard.homeboard.config;

public class ConstansSecurity {

    //Spring Secutiry
    public static final String LOGIN_URL = "/auth/login";
    public static final String SIGNIN_URL = "/auth/signin";
    public static final String PUBLIC_IDEAS_URL = "api/public";
    public static final String BOARDS_URL = "api/board";
    public static final String BOARD_ID_URL = "api/board/{id}";
    public static final String USER_IDEAS_URL = "/api/user/{userId}/category/{categoryId}";
    public static final String NEW_IDEA_URL = "api/idea";
    public static final String UPDATE_IDEA_URL = "api/idea/update/{id}";
    public static final String DELETE_IDEA_URL = "api/idea/delete/{id}";
    public static final String LOCALHOST_FRONT_URL = "http://localhost:3001";
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";


    //JWT
    public static final String SECRET_KEY = "miClaveSecretaMuySegura123!sdjflskdjflsf";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;
}
