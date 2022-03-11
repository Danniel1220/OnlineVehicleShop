package com.crystal.ovs.exceptions;

public class WrongPasswordException extends Exception{
    private static final String message = "Wrong password";

    public String getMessage() {
        return WrongPasswordException.message;
    }
}
