package com.erkanerturk.petclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
