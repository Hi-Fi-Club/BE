package com.teamspace.exception;

public class JWTTokenException extends RuntimeException {

    public JWTTokenException(String message) {
        super(message);
    }
}
