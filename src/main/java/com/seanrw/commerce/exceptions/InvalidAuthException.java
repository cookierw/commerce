package com.seanrw.commerce.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidAuthException extends RuntimeException {
    
    public InvalidAuthException(String message) {
        super(message);
    }

    public InvalidAuthException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidAuthException(Throwable cause) {
        super(cause);
    }
    
    public InvalidAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
