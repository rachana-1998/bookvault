package com.bookvault.bookvault.exp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
