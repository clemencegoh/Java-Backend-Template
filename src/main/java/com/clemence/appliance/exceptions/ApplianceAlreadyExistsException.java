package com.clemence.appliance.exceptions;


public class ApplianceAlreadyExistsException extends RuntimeException {
    public ApplianceAlreadyExistsException(String message){
        super(message);
    }
}
