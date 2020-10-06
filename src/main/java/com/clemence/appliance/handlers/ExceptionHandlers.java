package com.clemence.appliance.handlers;

import com.clemence.appliance.exceptions.ApplianceAlreadyExistsException;
import com.clemence.appliance.exceptions.ApplianceNotFoundException;
import com.clemence.appliance.exceptions.WrongDatePatternException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ApplianceAlreadyExistsException.class)
    public String handleApplianceAlreadyExists(RuntimeException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ApplianceNotFoundException.class)
    public String handleApplianceNotFound(RuntimeException ex){
        return ex.getMessage();
    }

}
