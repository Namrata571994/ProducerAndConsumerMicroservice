package com.example.product.ControllerAdvice;

import java.time.LocalDate;

public class ErrorResponse extends RuntimeException{

    String message;
    LocalDate timestamp;

    public ErrorResponse(String message, LocalDate timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
