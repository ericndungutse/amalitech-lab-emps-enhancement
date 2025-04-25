package org.ndungutse.ems.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.ndungutse.ems.AppContext;
import org.ndungutse.ems.exceptions.AppException;
import org.ndungutse.ems.exceptions.EmployeeNotFoundException;
import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.exceptions.InvalidSalaryException;
import org.ndungutse.ems.models.Department;
import org.ndungutse.ems.models.Employee;
import org.ndungutse.ems.utils.AppConstants;
import org.ndungutse.ems.validation.Validator;

public class EmployeeCollection<T> {
    private HashMap<T, Employee<T>> employees = new HashMap<>();
    private int pageSize = AppConstants.PAGE_SIZE; // Default page size

    public int generateNewEmployeeId() {
        return AppContext.getEmployeeCollection().getAllEmployees().size() + 1;
    }

    // Add employee
    public void addEmployee(Employee<T> employee) {
        try {

            // Validate Employee
            Validator.validateNewEmployee(employee);

            // Save new employee
            this.employees.put(employee.getEmployeeId(), employee);

        } catch (AppException e) {
            System.out.println(e.getMessage());
        } catch (InvalidSalaryException e) {
            System.out.println("{ message: " + e.getMessage()
                    + ", invalidValue: " + e.getInvalidValue() + " }");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    // Remove Employee
    public void removeEmployee(T employeeId) {
        try {
            // Check if employee with id exists
            if (this.employees.get(employeeId) == null)
                throw new EmployeeNotFoundException(
                        "Employee with id: " + employeeId + "does not exists.");

            // Remove the employee
            employees.remove(employeeId);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update Employee Details
    public void updateEmployeeEmployeeDetails(T employeeId, String field,
            Object newValue) {
        try {

            // Find the employee
            Employee<T> employeeToUpdate = this.employees.get(employeeId);

            // Check if employee exists
            if (employeeToUpdate == null)
                throw new EmployeeNotFoundException("Employee with id: "
                        + employeeId + " does not exists.");

            // Restrict Id Update
            if (field == "employeeId")
                throw new AppException("Employee Id cannot be update.");

            // Update employee field
            switch (field) {
            case "name":
                // validate employee
                Validator.validateName((String) newValue);
                employeeToUpdate.setName((String) newValue);
                break;
            case "department":
                Validator.validateDepartment(newValue);
                employeeToUpdate.setDepartment((Department) newValue);
                break;
            case "salary":
                Validator.validateSalary(newValue);
                double newSalary = newValue instanceof Integer
                        ? ((Integer) newValue).doubleValue()
                        : (double) newValue;

                employeeToUpdate.setSalary(newSalary);
                break;
            case "performanceRating":
                Validator.validateRating(newValue);

                double newRating = newValue instanceof Integer
                        ? ((Integer) newValue).doubleValue()
                        : (double) newValue;
                employeeToUpdate.setPerformanceRating((double) newRating);
                break;
            case "yearsOfExperience":
                // Validator
                Validator.validateExperience(newValue);
                employeeToUpdate.setYearsOfExperience((Integer) newValue);
                break;
            case "isActive":
                employeeToUpdate.setActive((Boolean) newValue);
                break;
            default:
                throw new AppException("Invalid field.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Get All employees and display them
    public List<Employee<T>> getAllEmployees() {
        List<Employee<T>> employeesList = new ArrayList<>(
                this.employees.values());
        displayEmployees(employeesList, "All Employees");
        return employeesList;
    }

    // Paginated Get Employees
    public List<Employee<T>> getAllEmployees(int pageNumber) {
        List<Employee<T>> allEmployees = new ArrayList<>(
                this.employees.values());
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, allEmployees.size());

        if (fromIndex >= allEmployees.size() || fromIndex < 0) {
            return new ArrayList<>();
        }

        List<Employee<T>> page = allEmployees.subList(fromIndex, toIndex);
        displayEmployees(page, "Employees - Page " + pageNumber);
        return page;
    }

    // Get employees by department
    public List<Employee<T>> getEmployeesByDepartment(Department department) {
        List<Employee<T>> departmentEmployees = this.employees.values().stream()
                .filter((employee) -> employee.getDepartment()
                        .equals(department))
                .collect(Collectors.toList());

        return departmentEmployees;
    }

    // Get Employees by name based on a search term
    public List<Employee<T>> getEmployeeByName(String name) {
        List<Employee<T>> employeesByName = this.employees.values().stream()
                .filter(employee -> employee.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());

        return employeesByName;
    }

    // Get EMployees based on salary
    public List<Employee<T>> getEmployeesBySalaryRange(double minSalary,
            double maxSalary) {
        List<Employee<T>> emp = this.employees.values().stream()
                .filter(employee -> employee.getSalary() >= minSalary
                        && employee.getSalary() <= maxSalary)
                .collect(Collectors.toList());

        return emp;
    }

    // Employees with minimum performance rating (e.g., rating >= 4.0).
    public List<Employee<T>> getEmployeesByPerformanceRating(double minRating) {
        List<Employee<T>> emp = this.employees.values().stream().filter(
                employee -> employee.getPerformanceRating() >= minRating)
                .collect(Collectors.toList());
        return emp;
    }

    // Combined Filters
    public List<Employee<T>> filter(Department department, Double minSalary,
            Double maxSalary, Double minRating, int pageNumber) {
        // Validate rating
        if (minRating != null)
            Validator.validateRating(minRating);

        // Validate salary
        if (minSalary != null)
            Validator.validateSalary(minSalary);
        if (maxSalary != null)
            Validator.validateSalary(maxSalary);

        // Apply filters
        List<Employee<T>> filteredList = this.employees.values().stream()
                .filter(employee -> department == null
                        || employee.getDepartment() == department)
                .filter(employee -> minSalary == null
                        || employee.getSalary() >= minSalary)
                .filter(employee -> maxSalary == null
                        || employee.getSalary() <= maxSalary)
                .filter(employee -> minRating == null
                        || employee.getPerformanceRating() >= minRating)
                .collect(Collectors.toList());

        // Apply pagination
        int fromIndex = Math.max(0, (pageNumber - 1) * pageSize);
        int toIndex = Math.min(fromIndex + pageSize, filteredList.size());

        if (fromIndex >= filteredList.size())
            return Collections.emptyList();

        return filteredList.subList(fromIndex, toIndex);
    }

    // Sort employees by years of experience in descending order
    public List<Employee<T>> sortEmployeesByExperienceDesc() {
        List<Employee<T>> employeesList = this.employees.values().stream()
                .collect(Collectors.toList());
        Collections.sort(employeesList);
        displayEmployees(employeesList, "Sorted Employees by Experience");
        return employeesList;
    }

    // Sort employees by salary in descending order
    public List<Employee<T>> sortEmployeesBySalaryDesc() {
        List<Employee<T>> employeesList = this.employees.values().stream()
                .collect(Collectors.toList());
        employeesList.sort(
                (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        displayEmployees(employeesList, "Sorted Employees by Salary");
        return employeesList;
    }

    // Sorts employees by performance rating (best first).
    public List<Employee<T>> sortEmployeesByPerformanceRatingDesc() {
        List<Employee<T>> employeesList = this.employees.values().stream()
                .collect(Collectors.toList());
        Collections.sort(employeesList, (e1, e2) -> Double
                .compare(e2.getPerformanceRating(), e1.getPerformanceRating()));

        displayEmployees(employeesList,
                "Sorted Employees by Performance Rating");
        return employeesList;
    }

    // Give a salary raise to employees with high performance ratings (e.g.,
    // rating
    // ≥ 4.5).
    public void giveSalaryRaise(double percentage, double minRating) {
        Iterator<Employee<T>> iterator = this.employees.values().iterator();

        while (iterator.hasNext()) {
            Employee<T> employee = iterator.next();
            if (employee.getPerformanceRating() >= minRating) {
                double newSalary = employee.getSalary()
                        + (employee.getSalary() * percentage / 100);
                employee.setSalary(newSalary);
            }
        }
    }

    // Retrieve the top 5 highest-paid employees.
    public List<Employee<T>> getTop5HighestPaidEmployees() {
        List<Employee<T>> employeesList = this.employees.values().stream()
                .collect(Collectors.toList());
        Collections.sort(employeesList,
                (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        // Use min method to ensure that if list contains less than 5 employees,
        // it will
        // not throw an exception, but will display the available employees.
        // and will return the available employees.
        return employeesList.subList(0, Math.min(5, employeesList.size()));
    }

    // Calculate the average salary of employees by department.
    public double calculateAverageSalaryByDepartment(Department department) {
        List<Employee<T>> departmentEmployees = this
                .getEmployeesByDepartment(department);

        return departmentEmployees.stream().mapToDouble(Employee::getSalary)
                .average().orElse(0.0);
    }

    // Display employees
    public void displayEmployees(List<Employee<T>> employees, String title) {

        System.out.println("\n\n");

        System.out.println("================================== " + title
                + "==================================");
        System.out.println(
                "____________________________________________________________________________________________________");
        String header = String.format(
                "%-15s | %-15s | %-15s | %-10s | %-10s | %-12s | %-8s",
                "Employee ID", "Name", "Department", "Salary", "Rating",
                "Experience", "Active");
        String divider = "----------------+-----------------+-----------------+------------+------------+--------------+-------";

        System.out.println(header);
        System.out.println(divider);

        // Print each employee
        employees.stream().forEach(e -> System.out.print(String.format(
                "%-15s | %-15s | %-15s | %-10s | %-10s | %-12s | %-8s%n",
                e.getEmployeeId(), e.getName(), e.getDepartment(),
                e.getSalary(), e.getPerformanceRating(),
                e.getYearsOfExperience() + " yrs",
                e.isActive() ? "Yes" : "No")));
    }

    @Override
    public String toString() {
        return "EmployeeCollection [employees=" + employees + "]";
    }

}
