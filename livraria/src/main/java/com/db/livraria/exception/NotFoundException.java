package com.db.livraria.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg){
         super(msg);
    }
}
