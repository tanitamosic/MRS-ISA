package com.Projekat.exception;

public class ReservationDoesNotExistException extends RuntimeException{

    public ReservationDoesNotExistException(String message) {
        super(message);
    }
}
