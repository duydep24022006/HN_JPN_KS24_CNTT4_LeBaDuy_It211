package com.example.bt6.exception;

public class WishLimitExceededException extends RuntimeException{
    public WishLimitExceededException(String message) {
        super(message);
    }
}
