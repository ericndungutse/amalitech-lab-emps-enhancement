package org.ndungutse.ems;

import java.io.IOException;
import java.util.List;

import org.ndungutse.ems.models.Department;
import org.ndungutse.ems.models.Employee;
import org.ndungutse.ems.repository.EmployeeCollection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(
                                        HelloApplication.class.getResource(
                                                        "hello-view.fxml"));

                        Scene scene = new Scene(fxmlLoader.load());

                        stage.setTitle("Employee Management System");

                        stage.setScene(scene);
                        stage.show();

                } catch (IOException e) {
                        AppContext.logger.severe(e.getMessage());
                }
        }

        public static final EmployeeCollection<String> employeeCollection = AppContext
                        .getEmployeeCollection();

        public static void main(String[] args) {
                // TODO: Pass only values to the add employee and create
                // // employee from there
                // System.out.println(
                // employeeCollection.getEmployeeByName("Eric"));

                // employeeCollection.getAllEmployees(-1);
                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Eric", Department.IT, 1500, 5, 3, true));

                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Camariza", Department.FINANCE, 3200, 3.9, 5,
                // true));
                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Lodrigues", Department.HR, 2700, 4.1, 4,
                // true));
                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Elina", Department.IT, 8000, 4.8, 6, true));
                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Jean", Department.HR, 900, 3.3, 1, false));
                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Nadine", Department.FINANCE, 1200, 4.0, 2,
                // true));
                // employeeCollection.addEmployee(new Employee<String>(
                // employeeCollection.generateNewEmployeeId(),
                // "Patrick", Department.IT, 5500, 4.6, 7, true));

                // employeeCollection.getEmployeesByPerformanceRating(-2);
                // employeeCollection.addEmployee(new Employee<>(7, "",
                // Department.IT, 5500, 4.6, 7, true));

                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                null, Department.HR, 2000, 4, 3, false));
                // employeeCollection.addEmployee(new Employee<>(9, "Albert",
                // Department.FINANCE, 10000, 4.9, 10, true));
                // employeeCollection.addEmployee(new Employee<>(10,
                // "Christine", Department.IT, 1500, 4.3, 3, true));
                // employeeCollection.addEmployee(new Employee<>(11, "Olivia",
                // Department.HR, 1800, 3.8, 2, true));
                // employeeCollection.addEmployee(new Employee<>(12, "Thierry",
                // Department.IT, 7000, 4.7, 8, true));
                // employeeCollection.addEmployee(new Employee<>(13, "Aline",
                // Department.FINANCE, 2600, 3.9, 4, true));
                // employeeCollection.addEmployee(new Employee<>(14, "Claude",
                // Department.HR, 1500, 3.1, 2, false));
                // employeeCollection.addEmployee(new Employee<>(15,
                // "Michaella", Department.IT, 4200, 4.4, 5, true));
                // employeeCollection.addEmployee(new Employee<>(16, "Samuel",
                // Department.FINANCE, 3800, 4.2, 6, true));
                // employeeCollection.addEmployee(new Employee<>(17, "Diane",
                // Department.HR, 3200, 3.5, 3, false));
                // employeeCollection.addEmployee(new Employee<>(18, "Kevin",
                // Department.IT, 6100, 4.6, 7, true));
                // employeeCollection.addEmployee(new Employee<>(19, "Beatrice",
                // Department.FINANCE, 2900, 4.1, 4, true));
                // employeeCollection.addEmployee(new Employee<>(20, "Brian",
                // Department.IT, 4700, 4.0, 5, true));
                // employeeCollection.addEmployee(new Employee<>(21, "Sonia",
                // Department.HR, 2100, 3.3, 2, false));
                // employeeCollection.addEmployee(new Employee<>(22,
                // "Jean-Paul", Department.FINANCE, 6000, 4.8, 9, true));
                // employeeCollection.addEmployee(new Employee<>(23, "Bridget",
                // Department.IT, 3500, 3.9, 4, true));
                // employeeCollection.addEmployee(new Employee<>(24, "Nathan",
                // Department.HR, 2500, 3.6, 3, true));
                // employeeCollection.addEmployee(new Employee<>(25, "Grace",
                // Department.FINANCE, 4000, 4.3, 6, true));
                // employeeCollection.addEmployee(new Employee<>(26, "Ericson",
                // Department.IT, 3100, 3.7, 3, true));
                // employeeCollection.addEmployee(new Employee<>(27, "Jasmine",
                // Department.HR, 2300, 3.2, 2, true));
                // employeeCollection.addEmployee(new Employee<>(28, "Frank",
                // Department.FINANCE, 7500, 4.5, 8, true));
                // employeeCollection.addEmployee(new Employee<>(29, "Laetitia",
                // Department.IT, 1800, 3.4, 1, false));
                // employeeCollection.addEmployee(new Employee<>(30, "Lionel",
                // Department.HR, 5000, 4.6, 7, true));
                launch();
        }
}
