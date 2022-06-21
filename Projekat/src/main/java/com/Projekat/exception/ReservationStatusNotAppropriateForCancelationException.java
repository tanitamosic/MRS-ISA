package com.Projekat.exception;

public class ReservationStatusNotAppropriateForCancelationException extends RuntimeException{

    public ReservationStatusNotAppropriateForCancelationException(String message) {
        super(message);
    }
}
