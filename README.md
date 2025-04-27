Here’s your updated version, combining your original README and the new objectives and description you provided:

---

# Employee Management System

[![build_and_test](https://github.com/ericndungutse/amalitech-lab-emps-enhancement/actions/workflows/ci.yml/badge.svg)](https://github.com/ericndungutse/amalitech-lab-emps-enhancement/actions/workflows/ci.yml)

## **Objectives**

- Implement proper exception handling using `try-catch`, `throw`, and `throws`.
- Create and use custom exceptions to handle specific error cases.
- Debug the Employee Management System using IDE tools like breakpoints and step execution.
- Write and execute basic JUnit tests to verify CRUD operations and sorting functionalities.
- Apply Git workflows, including branching, merging, and rebasing, to manage code changes efficiently.

## **Features**

- Add new employees
- Update employee information
- Delete employee records
- Search for employees
- Filter and sort employee records
- User-friendly GUI
- Exception handling for error scenarios
- JUnit tests for core functionalities

## **Technologies Used**

- **Java**: Core programming language
- **Maven**: Dependency management
- **JavaFX**: Platform for building the GUI
- **JUnit**: For unit testing

## **Prerequisites**

- Java Development Kit (JDK) 17 or higher
- IDE (e.g., IntelliJ IDEA, Eclipse)

## **Setup Instructions**

1. Clone the repository:
   ```bash
   git clone https://github.com/ericndungutse/amalitech-lab-ems.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ems
   ```
3. Install dependencies:
   ```bash
   mvn install
   ```
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn javafx:run
   ```

---