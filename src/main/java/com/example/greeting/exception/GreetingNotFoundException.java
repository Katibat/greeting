package com.example.greeting.exception;

public class GreetingNotFoundException extends RuntimeException {
    public GreetingNotFoundException(String message) {
        super(message);
    }
}
