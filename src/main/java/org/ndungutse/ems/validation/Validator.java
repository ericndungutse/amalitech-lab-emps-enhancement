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
    public static void validateRating(Object rating) {
        double newRating;
        if (rating == null || rating == "") {
            throw new InvalidInputException("Rating is required",
                    "performanceRating");
        }

        if (rating instanceof Integer) {
            newRating = ((Integer) rating).doubleValue();
        } else if (rating instanceof Double) {
            newRating = (double) rating;
        } else {
            throw new InvalidInputException(
                    "Invalid input: " + rating + ". Rating must be a number",
                    "performanceRating");
        }

        if (newRating < 0 || newRating > 5)
            throw new InvalidInputException(
                    "Invalid input: " + rating
                            + ". Rating should be between 0 and 5",
                    "performanceRating");

    }

    // Salary Validator
    public static void validateSalary(Object salary) {
        double newSalary;
        if (salary == null || salary == "") {
            throw new InvalidInputException("Salary is required", "salary");
        }

        if (salary instanceof Integer) {
            newSalary = ((Integer) salary).doubleValue();
        } else if (salary instanceof Double) {
            newSalary = (double) salary;
        } else {
            throw new InvalidInputException(
                    "Invalid input: " + salary + ". Salary must be a number",
                    "salary");
        }

        if (newSalary <= 0)
            throw new InvalidInputException("Invalid input: " + salary
                    + ". Salary should be greater than 0", "salary");
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

    // Experience validator
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
