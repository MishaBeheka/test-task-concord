package com.test.concord.exceptions;

public class UserNotFountException extends RuntimeException{
    public UserNotFountException(String message) {
        super(message);
    }
}
