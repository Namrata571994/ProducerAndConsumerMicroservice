package com.example.product.Controller;

import com.example.product.ControllerAdvice.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.PayloadTooLargeException;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle PayloadTooLargeException (413 Payload Too Large)
    @ExceptionHandler(PayloadTooLargeException.class)
    public ResponseEntity<ErrorResponse> payloadTooLargeException(PayloadTooLargeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "The request payload is too large. Please reduce the size of the file or data and try again.",
                LocalDate.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}
