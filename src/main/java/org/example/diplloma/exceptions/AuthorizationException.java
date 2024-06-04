package org.example.diplloma.exceptions;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String msg) {
        super(msg);
    }
}