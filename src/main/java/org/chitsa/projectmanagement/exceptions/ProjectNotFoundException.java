package org.chitsa.projectmanagement.exceptions;

public class ProjectNotFoundException extends IllegalArgumentException{
    public ProjectNotFoundException(String message){
        super(message);
    }
}
