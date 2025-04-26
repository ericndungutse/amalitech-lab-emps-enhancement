package org.ndungutse.ems.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

      public EmployeeNotFoundException(String message) {
            super(message);
      }

      public String getMessage() {
            return super.getMessage();
      }

}
