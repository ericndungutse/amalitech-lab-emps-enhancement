package org.ndungutse.ems;

import java.io.IOException;

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

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class
                                .getResource("hello-view.fxml"));

                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Employee Management System");

                stage.setScene(scene);
                stage.show();

        }

        public static final EmployeeCollection<String> employeeCollection = AppContext
                        .getEmployeeCollection();

        public static void main(String[] args) {
                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Eric", Department.IT, 1500, 5, 3, true));

                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Camariza", Department.FINANCE, 3200, 3.9, 5,
                                true));

                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Lodrigues", Department.HR, 2700, 4.1, 4,
                                true));
                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Elina", Department.IT, 8000, 4.8, 6, true));
                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Jean", Department.HR, 900, 3.3, 1, false));
                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Nadine", Department.FINANCE, 1200, 4.0, 2,
                                true));
                employeeCollection.addEmployee(new Employee<String>(
                                employeeCollection.generateNewEmployeeId(),
                                "Patrick", Department.IT, 5500, 4.6, 7, true));

                launch();
        }
}
