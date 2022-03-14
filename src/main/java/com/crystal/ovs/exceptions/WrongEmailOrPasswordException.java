package com.crystal.ovs.exceptions;

public class WrongEmailOrPasswordException extends Exception{
    private static final String message = "Email or password is wrong!";

    public String getMessage() {
        return WrongEmailOrPasswordException.message;
    }
}
