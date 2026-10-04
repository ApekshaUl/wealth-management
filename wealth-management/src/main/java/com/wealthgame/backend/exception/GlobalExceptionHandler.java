package com.wealthgame.backend.exception;

import com.wealthgame.backend.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error ->
                                errors.put(error.getField(),error.getDefaultMessage())
                );
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                400,
                "Validation failed",
                errors
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                404,
                ex.getMessage(),
                null

        );
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                400,
                ex.getMessage(),
                null
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
