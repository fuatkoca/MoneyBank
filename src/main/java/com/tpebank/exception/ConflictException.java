package com.tpebank.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
