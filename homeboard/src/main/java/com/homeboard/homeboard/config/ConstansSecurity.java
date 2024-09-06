package com.homeboard.homeboard.config;

public class ConstansSecurity {

    //Spring Secutiry
    public static final String LOGIN_URL = "/auth/log_in";
    public static final String SIGNIN_URL = "/auth/sign_in";
    


    //JWT
    public static final String SECRET_KEY = "miClaveSecretaMuySegura123!sdjflskdjflsf";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;
}
