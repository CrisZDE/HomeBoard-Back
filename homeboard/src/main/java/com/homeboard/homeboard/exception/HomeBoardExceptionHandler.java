package com.homeboard.homeboard.exception;


import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HomeBoardExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(HomeBoardException.class)
    public ResponseEntity<ErrorMessage> handleHomeBoardException(HomeBoardException exception){
        HttpStatus httpStatus = exception.getHttpStatuts();
        if (httpStatus == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        System.out.println("Exception manejada: " + exception.getMessage());
        
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setHttpStatus(httpStatus.value());
        errorMessage.setMessage(exception.getMessage());
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorMessage> manejarErrorDeConexion(DataAccessException ex) {
        ErrorMessage response = new ErrorMessage();
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Error de conexión con la base de datos: " + ex.getMessage()); 
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex){
        ErrorMessage message = new ErrorMessage();
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage("Error inesperado " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        // Aquí puedes registrar el error
        System.err.println("Authentication error: " + ex.getMessage());
        return new ResponseEntity<>("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // @ExceptionHandler(AccessDeniedException.class)
    // public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
    //     // Aquí puedes registrar el error
    //     System.err.println("Access denied: " + ex.getMessage());
    //     return new ResponseEntity<>("Access denied: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    // }
}
