package com.example.BookStoreProject.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
	
	
	@ExceptionHandler(LowQuantityException.class)
    public ResponseEntity<String> handleLowQuantityException(LowQuantityException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
	
	
	@ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
