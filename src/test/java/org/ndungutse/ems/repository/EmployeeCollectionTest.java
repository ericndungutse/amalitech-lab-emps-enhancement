package org.ndungutse.ems.repository;

import org.junit.jupiter.api.Test;
import org.ndungutse.ems.models.Department;
import org.ndungutse.ems.models.Employee;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCollectionTest {

    @Test
    void addEmployee() {
        // Arrange
        EmployeeCollection<String> collection = new EmployeeCollection<>();
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
}
