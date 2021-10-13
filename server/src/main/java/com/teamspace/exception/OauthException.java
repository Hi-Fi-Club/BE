package com.teamspace.exception;

public class OauthException extends RuntimeException {

    public OauthException(String message, String method) {
        super(method + ":   " + message);
    }
}
