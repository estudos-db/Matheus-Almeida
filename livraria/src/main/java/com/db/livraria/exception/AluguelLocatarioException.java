package com.db.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class AluguelLocatarioException extends RuntimeException {

    public AluguelLocatarioException(String msg){
        super(msg);
    }
}
