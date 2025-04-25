package org.ndungutse.ems.validation;

import org.ndungutse.ems.exceptions.AppException;
import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.models.Employee;

public class Validator {

    public static <T> void validateNewEmployee(Employee<T> employee) {

        if (employee == null) {
            throw new AppException("Employee cannot be null.");
        }

        // Validate Name
        validateName(employee.getName());

        // Validate Department
        validateDepartment(employee.getDepartment());

        // Validate Rating
        validateRating(employee.getPerformanceRating());

        // Validate Salary
        validateSalary(employee.getSalary());

        // Validate Experience
        validateExperience(employee.getYearsOfExperience());

    }

    // Rating validator
    public static void validateRating(Double rating) {
        if (rating < 0 || rating > 5)
            throw new InvalidInputException(
                    "Invalid input: " + rating + " should be between 0 and 5",
                    "performanceRating");
    }

    // Salary Validator
    public static void validateSalary(Double salary) {
        if (salary <= 0)
            throw new InvalidInputException("Invalid input: " + salary
                    + ". Salary should be greater than 0", "Salary");
    }

    // Name Validator
    public static void validateName(String name) {
        if (name.trim().isEmpty() || name.isBlank()) {
            throw new InvalidInputException("Employee name cannot be empty",
                    "name");
        }
    }

    // Department Validator
    public static void validateDepartment(Object newValue) {
        // Check if Department is present
        if (newValue == null) {
            throw new InvalidInputException("Department cannot be null",
                    "department");
        }

    }

    public static void validateExperience(Object newValue) {
        if (newValue instanceof Number) {
            double value = ((Number) newValue).doubleValue();

            if (value < 0) {
                throw new InvalidInputException(
                        "Invalid value: " + newValue
                                + ". Experience value cannot be less than zero",
                        "Years of experience");
            }
        } else {
            throw new InvalidInputException(
                    "Invalid input: " + newValue
                            + ". The experience value must be a number",
                    "Years of experience");
        }
    }
}
