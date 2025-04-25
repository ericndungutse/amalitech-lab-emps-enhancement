package org.ndungutse.ems.exceptions;

public class InvalidSalaryException extends RuntimeException {
      Object invalidValue;

      public InvalidSalaryException(String message, Object invalidValue) {
            super(message);
            this.invalidValue = invalidValue;
      }

      public Object getInvalidValue() {
            return invalidValue;
      }

}
