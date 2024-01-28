package com.travel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // Indicates that this class is a global exception handler for controllers.
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        // This method is called when a validation error occurs in a controller method.
        // It handles MethodArgumentNotValidException, which is thrown when request parameter or payload validation fails.

        Map<String, String> errors = new HashMap<>();
        // Create a map to store field names and corresponding error messages.

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Loop through all validation errors in the exception.
            String fieldName = ((FieldError) error).getField();
            // Get the field name that caused the validation error.
            String errorMessage = error.getDefaultMessage();
            // Get the error message associated with the validation error.
            errors.put(fieldName, errorMessage);
            // Add the field name and error message to the map.
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        // Return a ResponseEntity with a map of field names and error messages as the response body,
        // and set the HTTP status to BAD_REQUEST (400).
    }
}