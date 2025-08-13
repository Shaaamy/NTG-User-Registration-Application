package com.ntg.Ntg_User_Registration_App.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).findFirst()
                .orElse("Validation error");

           ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String lowerCaseMessage = ex.getMostSpecificCause().getMessage().toLowerCase();
        String message;

        if (lowerCaseMessage.contains("username")) {
            message = "Username already exists";
        } else if (lowerCaseMessage.contains("email")) {
            message = "Email already exists";
        } else {
            message = "Duplicate value found";
        }

        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.CONFLICT.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Catch-all for unexpected errors
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Log the exception for developers (optional but recommended)
        ex.printStackTrace();

        String message = "An unexpected error occurred. You can contact us for support.";
        return new ResponseEntity<>(
                new ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.toString()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }



}
