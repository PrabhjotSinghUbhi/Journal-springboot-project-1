package me.prabh.journal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //-> acts as a giant catch block for the entire application.
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(ResourceNotFoundException ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //handle general error.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "An unknown error occurred.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
