package org.ndungutse.ems.exceptions;

public class InvalidInputException extends RuntimeException {
      private Object invalidValue;
      private String field;

      public InvalidInputException(String message, Object invalidValue,
                  String field) {
            super(message);
            this.invalidValue = invalidValue;
            this.field = field;
      }

      public Object getInvalidValue() {
            return invalidValue;
      }

      public String getField() {
            return field;
      }

}
