package com.clemence.appliance.exceptions;

public class ApplianceNotFoundException extends RuntimeException{
    public ApplianceNotFoundException(String message){
        super(message);
    }
}
