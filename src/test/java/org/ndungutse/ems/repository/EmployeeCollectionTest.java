package org.ndungutse.ems.repository;

import org.junit.jupiter.api.Test;
import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.models.Department;
import org.ndungutse.ems.models.Employee;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCollectionTest {

    private final EmployeeCollection<String> collection = new EmployeeCollection<>();

    // Verify Get employee by id
    @Test
    void getEmployeeById() {
        // Arrange
        String employeeId = collection.generateNewEmployeeId();
        Employee<String> employee = new Employee<>(
                employeeId,
                "Jane Doe",
                Department.HR,
                4500,
                4.0,
                3,
                true
        );
        collection.addEmployee(employee); // Add to collection first

        // Act
        Employee<String> retrievedEmployee = collection.getEmployeeById(employeeId);

        // Assert
        assertNotNull(retrievedEmployee);
        assertEquals(employeeId, retrievedEmployee.getEmployeeId());
        assertEquals("Jane Doe", retrievedEmployee.getName());
        assertEquals(4500, retrievedEmployee.getSalary());
        assertEquals(3, retrievedEmployee.getYearsOfExperience());
        assertEquals(4.0, retrievedEmployee.getPerformanceRating());
        assertEquals(Department.HR, retrievedEmployee.getDepartment());
    }

    // Verify add employee
    @Test
    void addEmployee() {
        // Arrange
        String employeeId =  collection.generateNewEmployeeId(); // UUID as String
        Employee<String> employee = new Employee<>(
                employeeId,
                "Eric Tuyizere",
                Department.IT,
                5000,
                4.5,
                2,
                true
        );

        // Act
        collection.addEmployee(employee);

        // Assert
        Employee<String> savedEmployee = collection.getEmployeeById(employeeId);

        assertNotNull(savedEmployee);
        assertEquals("Eric Tuyizere", savedEmployee.getName());
        assertEquals(5000, savedEmployee.getSalary());
        assertEquals(2, savedEmployee.getYearsOfExperience());
        assertEquals(4.5, savedEmployee.getPerformanceRating());
        assertEquals(Department.IT, savedEmployee.getDepartment());
    }

    // Missing Name
    @Test
    void addEmployee_shouldThrowException_whenNameIsBlank() {
        // Arrange
        String employeeId = collection.generateNewEmployeeId();
        Employee<String> employee = new Employee<>(
                employeeId,
                " ", // blank name
                Department.IT,
                5000,
                4.5,
                2,
                true
        );

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            collection.addEmployee(employee);
        });

        assertEquals("Employee name cannot be empty", exception.getMessage());
    }

    // Verify exception thrown when salary is negative
    @Test
    void addEmployee_shouldThrowException_whenSalaryIsNegative() {
        // Arrange
        String employeeId = collection.generateNewEmployeeId();
        Employee<String> employee = new Employee<>(
                employeeId,
                "Eric Tuyizere",
                Department.IT,
                -1000, // negative salary
                4.5,
                2,
                true
        );

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            collection.addEmployee(employee);
        });

        assertTrue(exception.getMessage().contains("Salary should be greater than 0"));
    }


}
