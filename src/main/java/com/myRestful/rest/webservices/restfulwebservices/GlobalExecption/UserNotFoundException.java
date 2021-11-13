package com.myRestful.rest.webservices.restfulwebservices.GlobalExecption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
