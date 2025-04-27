package org.ndungutse.ems;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.ndungutse.ems.exceptions.AppException;
import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.exceptions.InvalidSalaryException;
import org.ndungutse.ems.models.Department;
import org.ndungutse.ems.models.Employee;
import org.ndungutse.ems.repository.EmployeeCollection;
import org.ndungutse.ems.utils.DialogUtility;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddEmployee implements Initializable {
    @FXML
    private ComboBox<Department> departmentComboBox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField performanceRatingField;
    @FXML
    private TextField yearsOfExperienceField;
    @FXML
    private CheckBox activeCheckBox;
    @FXML
    private Button cancelButton;
    private HelloController helloController;
    private final EmployeeCollection<String> employeeCollection = AppContext
            .getEmployeeCollection();

    public void initialize(URL location, ResourceBundle resources) {
        AppContext.logger.info("Initializing...");
        departmentComboBox.getItems().addAll(Department.values());
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    private void handleAddEmployee() {
        try {
            String name = nameField.getText().trim();
            String salaryText = salaryField.getText().trim();
            String ratingText = performanceRatingField.getText().trim();
            String experienceText = yearsOfExperienceField.getText().trim();
            Department department = departmentComboBox.getValue();
            boolean isActive = activeCheckBox.isSelected();

            // if (name.isEmpty() || salaryText.isEmpty() ||
            // ratingText.isEmpty()
            // || experienceText.isEmpty()) {
            // throw new AppException("All fields must be filled.");
            // }

            Employee<String> employee = new Employee<>(
                    employeeCollection.generateNewEmployeeId(), name,
                    department, Double.parseDouble(salaryText),
                    Double.parseDouble(ratingText),
                    Integer.parseInt(experienceText), isActive);
            employeeCollection.addEmployee(employee);

            this.closeModel();
            DialogUtility.showAlert("New employee",
                    "Employee added successful");
            List<Employee<String>> employees = new ArrayList<>(
                    helloController.getEmployees(null, null, null, null, 0));
            helloController.displayEmployees(employees);

        } catch (AppException e) {
            DialogUtility.showErrorAlert("Error1", e.getMessage());
            AppContext.logger.severe(e.toString());
        } catch (NumberFormatException e) {
            DialogUtility.showErrorAlert("Invalid Input",
                    "Invalid input " + e.getMessage());
            AppContext.logger.severe(e.getMessage());
        } catch (InvalidInputException e) {
            DialogUtility.showErrorAlert("Invalid Inputs", e.getMessage());
            AppContext.logger.severe(e.getMessage());
        } catch (InvalidSalaryException e) {
            DialogUtility.showErrorAlert("Salary Error", e.getMessage());
            AppContext.logger.severe(e.getMessage());
        } catch (Exception e) {
            DialogUtility.showErrorAlert("Error",
                    "Something went wrong! Try again later.");
            AppContext.logger.severe(e.getMessage());
        }

    }

    // CLose Add Employee Form
    @FXML
    private void closeModel() {
        cancelButton.getScene().getWindow().hide();
    }

}
