package com.bookvault.bookvault.exp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }
    public BadRequestException(String message,Throwable cause){
        super(message,cause);
    }
}
