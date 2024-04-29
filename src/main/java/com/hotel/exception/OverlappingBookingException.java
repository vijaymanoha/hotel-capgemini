package com.hotel.exception;

public class OverlappingBookingException extends RuntimeException{
    public  OverlappingBookingException(String message){
        super(message);
    }
}

