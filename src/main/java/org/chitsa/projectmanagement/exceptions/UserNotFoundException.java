package org.chitsa.projectmanagement.exceptions;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException(String message){
        super(message);
    }
}
