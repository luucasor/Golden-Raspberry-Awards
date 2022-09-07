package com.luucasor.goldenraspberryawards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class DomainException extends RuntimeException {

    public DomainException(String message){
        super(message);
    }
}
