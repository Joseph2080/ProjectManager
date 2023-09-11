package org.chitsa.projectmanagement.exceptions;

public class InvalidParameterException extends IllegalArgumentException {
    public InvalidParameterException(String message){
        super(message);
    }
}
