package com.db.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class LivroAtreladoException extends RuntimeException{

    public LivroAtreladoException(String msg){
        super(msg);
    }
}
