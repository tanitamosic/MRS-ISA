package com.Projekat.exception;

public class RequestNotValidException extends RuntimeException{

    public RequestNotValidException(String message) {
        super(message);
    }
}
