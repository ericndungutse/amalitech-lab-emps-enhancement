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

        // Check if Department is present
        if (employee.getDepartment() == null) {
            throw new InvalidInputException("Department must be selected.",
                    employee.getName(), "department");
        }

        if (employee.getSalary() < 0) {
            throw new InvalidInputException("Salary must be a positive number.",
                    employee.getSalary(), "Salary");
        }

        double rating = employee.getPerformanceRating();
        if (rating < 0 || rating > 5) {
            throw new InvalidInputException(
                    "Invalid rating. It should be between 0 and 5", rating,
                    "rating");
        }

        if (employee.getYearsOfExperience() < 0) {
            throw new InvalidInputException(
                    "Years of experience must be a non-negative value.",
                    employee.getYearsOfExperience(), "yearsOfExperience");
        }

    }

    public static <T> void validateRating(Double rating) {
        if (rating < 0 || rating > 5)
            throw new AppException(
                    "Rating should be between 0 and 5 both included");
    }

    public static <T> void validateSalary(Double salary) {
        if (salary <= 0)
            throw new AppException("Salary should be greater than 0.");
    }

    public static void validateName(String name) {
        if (name.trim().isEmpty() || name.isBlank()) {
            throw new InvalidInputException("Employee name cannot be empty",
                    name, "name");
        }
    }
}
