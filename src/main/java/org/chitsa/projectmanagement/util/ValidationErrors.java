package org.chitsa.projectmanagement.util;

public enum ValidationErrors {
    NullField("cannot be null"),
    EmptyField("cannot be empty"),
    InvalidEmail("email is not valid");
    public final String label;

    private ValidationErrors(String label) {
        this.label = label;
    }
}
