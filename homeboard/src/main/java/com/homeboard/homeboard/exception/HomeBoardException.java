package com.homeboard.homeboard.exception;

import org.springframework.http.HttpStatus;

public class HomeBoardException extends Exception {

    private HttpStatus httpStatuts;

    public HomeBoardException(String message){
        super(message);
    }

    public HomeBoardException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatuts = httpStatus;
    }

    public HttpStatus getHttpStatuts() {
        return httpStatuts;
    }

    public void setHttpStatuts(HttpStatus httpStatuts) {
        this.httpStatuts = httpStatuts;
    }

    
}
