package org.ndungutse.ems.validation;

import org.ndungutse.ems.exceptions.AppException;
import org.ndungutse.ems.exceptions.InvalidDepartmentException;
import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.exceptions.InvalidSalaryException;
import org.ndungutse.ems.models.Employee;

public class Validator {

    public static <T> void validateNewEmployee(Employee<T> employee) {

        if (employee == null) {
            throw new AppException("Employee cannot be null.");
        }

        String name = employee.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new AppException("Employee name cannot be empty.");
        }

        // Check if Department is present
        if (employee.getDepartment() == null) {
            throw new InvalidDepartmentException(employee.getDepartment(),
                    "Department must be selected.");
        }

        if (employee.getSalary() < 0) {
            throw new InvalidSalaryException(
                    "Salary must be a positive number.", employee.getSalary());
        }

        double rating = employee.getPerformanceRating();
        if (rating < 0 || rating > 5) {
            throw new InvalidInputException(
                    "Invalid rating. It should be between 0 and 5", rating,
                    "rating");
        }

        if (employee.getYearsOfExperience() < 0) {
            throw new AppException("Years of experience must be non-negative.");
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
}
