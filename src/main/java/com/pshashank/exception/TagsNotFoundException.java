package com.pshashank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TagsNotFoundException extends InvalidParameterException {
    public TagsNotFoundException(){
        super("Tags parameter is required");
    }

}
