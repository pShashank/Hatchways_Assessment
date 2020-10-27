package com.pshashank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SortByInvalidException extends InvalidParameterException{
    public SortByInvalidException(String sortBy){
        super("sortBy parameter is invalid"+ sortBy);
    }

}