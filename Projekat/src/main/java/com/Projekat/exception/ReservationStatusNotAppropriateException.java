package com.Projekat.exception;

public class ReservationStatusNotAppropriateException extends RuntimeException {

    public ReservationStatusNotAppropriateException(String message) {
        super(message);
    }
}
