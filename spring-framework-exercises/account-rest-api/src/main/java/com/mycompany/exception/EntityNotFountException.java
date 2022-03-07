package com.mycompany.exception;

public class EntityNotFountException extends RuntimeException {
    public EntityNotFountException(String message) {
        super(message);
    }
}
