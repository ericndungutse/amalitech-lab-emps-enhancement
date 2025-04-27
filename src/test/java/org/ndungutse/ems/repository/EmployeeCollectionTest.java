package org.ndungutse.ems.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.models.Department;
import org.ndungutse.ems.models.Employee;

class EmployeeCollectionTest {

        private final EmployeeCollection<String> collection = new EmployeeCollection<>();

        // Verify Get employee by id
        @Test
        void getEmployeeById() {
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId,
                                "Jane Doe", Department.HR, 4500, 4.0, 3, true);
                collection.addEmployee(employee); // Add to collection first

                // Act
                Employee<String> retrievedEmployee = collection
                                .getEmployeeById(employeeId);

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
                String employeeId = collection.generateNewEmployeeId(); // UUID
                                                                        // as
                                                                        // String
                Employee<String> employee = new Employee<>(employeeId,
                                "Eric Tuyizere", Department.IT, 5000, 4.5, 2,
                                true);

                // Act
                collection.addEmployee(employee);

                // Assert
                Employee<String> savedEmployee = collection
                                .getEmployeeById(employeeId);

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
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId, " ", // blank
                                                                            // name
                                Department.IT, 5000, 4.5, 2, true);

                InvalidInputException exception = assertThrows(
                                InvalidInputException.class, () -> {
                                        collection.addEmployee(employee);
                                });

                assertEquals("Employee name cannot be empty",
                                exception.getMessage());
        }

        // Verify exception thrown when salary is negative
        @Test
        void addEmployee_shouldThrowException_whenSalaryIsNegative() {
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId,
                                "Eric Tuyizere", Department.IT, -1000, // negative
                                                                       // salary
                                4.5, 2, true);

                InvalidInputException exception = assertThrows(
                                InvalidInputException.class, () -> {
                                        collection.addEmployee(employee);
                                });

                assertTrue(exception.getMessage()
                                .contains("Salary should be greater than 0"));
        }

        // Verify addEmployee throws exception when rating is not between 0-5
        @Test
        void addEmployee_shouldThrowException_whenRatingIsOutOfBounds() {
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId,
                                "Eric Tuyizere", Department.IT, 5000, 6.0, // invalid
                                                                           // rating
                                                                           // >
                                                                           // 5
                                2, true);

                InvalidInputException exception = assertThrows(
                                InvalidInputException.class, () -> {
                                        collection.addEmployee(employee);
                                });

                assertTrue(exception.getMessage()
                                .contains("Rating should be between 0 and 5"));
        }

        // Verify an exception is thrown when experience is negative
        @Test
        void addEmployee_shouldThrowException_whenExperienceIsNegative() {
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId,
                                "Eric Tuyizere", Department.IT, 5000, 4.0, -2,
                                true);

                InvalidInputException exception = assertThrows(
                                InvalidInputException.class, () -> {
                                        collection.addEmployee(employee);
                                });

                assertTrue(exception.getMessage().contains(
                                "Experience value cannot be less than zero"));
        }

        // Verify exception is thrown when department is null
        @Test
        void addEmployee_shouldThrowException_whenDepartmentIsNull() {
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId,
                                "Eric Tuyizere", null, 5000, 4.0, 2, true);

                InvalidInputException exception = assertThrows(
                                InvalidInputException.class, () -> {
                                        collection.addEmployee(employee);
                                });

                assertEquals("Department cannot be null",
                                exception.getMessage());
        }

        // Verify search employee by department throws an exception when
        // department
        // is missing
        @Test
        void searchEmployeesByDepartment_shouldThrowException_WhenDepartmentIsNull() {
                InvalidInputException exception = assertThrows(
                                InvalidInputException.class, () -> {
                                        collection.getEmployeesByDepartment(
                                                        null);
                                });

                assertEquals("Error while retrieving employees by department: Department is required!",
                                exception.getMessage());
        }

        // Verify getEmployeesByDepartment
        @Test
        void getEmployeesByDepartment_shouldReturnEmployeesInGivenDepartment() {
                String employeeId1 = collection.generateNewEmployeeId();
                Employee<String> employee1 = new Employee<>(employeeId1,
                                "Eric Tuyizere", Department.IT, 5000, 4.5, 2,
                                true);

                String employeeId2 = collection.generateNewEmployeeId();
                Employee<String> employee2 = new Employee<>(employeeId2,
                                "Alice Doe", Department.HR, 4000, 4.0, 3, true);

                String employeeId3 = collection.generateNewEmployeeId();
                Employee<String> employee3 = new Employee<>(employeeId3,
                                "John Smith", Department.IT, 5500, 4.2, 5,
                                true);

                collection.addEmployee(employee1);
                collection.addEmployee(employee2);
                collection.addEmployee(employee3);

                List<Employee<String>> itEmployees = collection
                                .getEmployeesByDepartment(Department.IT);

                assertEquals(2, itEmployees.size());
                assertTrue(itEmployees.stream().allMatch(
                                e -> e.getDepartment() == Department.IT));
        }

        // Verify removeEmployee works and employee no longer exists in the
        // collection
        @Test
        void removeEmployee_shouldDeleteEmployeeFromCollection() {
                // Arrange
                String employeeId = collection.generateNewEmployeeId();
                Employee<String> employee = new Employee<>(employeeId,
                                "Eric Tuyizere", Department.IT, 5000, 4.5, 2,
                                true);
                collection.addEmployee(employee);

                // Pre-check: make sure the employee is added
                assertNotNull(collection.getEmployeeById(employeeId));

                // Act
                collection.removeEmployee(employeeId);

                // Assert
                Employee<String> deletedEmployee = collection
                                .getEmployeeById(employeeId);
                assertNull(deletedEmployee,
                                "Employee should be null after being removed");
        }
}
