package org.ndungutse.ems.exceptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    public String getMessage() {
        return "{ message: " + super.getMessage() + " }";
    }
}
