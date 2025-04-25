package org.ndungutse.ems.dtos;

public class ExceptionDTO {
      private String message;
      private String field;

      public ExceptionDTO(String message, String field) {
            this.message = message;
            this.field = field;
      }

      public String getFormattedErrorMessage() {
            return "{message: " + this.message + ", field: " + this.field + "}";
      }
}
