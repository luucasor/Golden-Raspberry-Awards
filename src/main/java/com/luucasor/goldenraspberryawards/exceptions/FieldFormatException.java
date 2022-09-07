package com.luucasor.goldenraspberryawards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldFormatException extends RuntimeException {

    public FieldFormatException(String message){
        super(message);
    }
}
