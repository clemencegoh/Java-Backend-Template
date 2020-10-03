package com.clemence.appliance.exceptions;

public class WrongDatePatternException extends RuntimeException {
    public WrongDatePatternException(String message){
        super(message);
    }
}
