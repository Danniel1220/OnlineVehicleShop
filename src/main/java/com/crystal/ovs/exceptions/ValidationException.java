package com.crystal.ovs.exceptions;

import java.util.List;

public class ValidationException extends Exception{
    private List<String> validationErrors;

    public ValidationException(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors(){
        return this.validationErrors;
    }
}
