package org.ndungutse.ems.exceptions;

public class InvalidDepartmentException extends RuntimeException {
      Object invalidValue;

      public InvalidDepartmentException(Object invalidValid, String message) {
            super(message);
            this.invalidValue = invalidValid;
      }

}
