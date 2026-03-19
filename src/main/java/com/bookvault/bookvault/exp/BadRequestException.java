package com.bookvault.bookvault.exp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BadRequestException extends Exception{
    public BadRequestException(String message){
        super(message);
    }

}
