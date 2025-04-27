package org.ndungutse.ems;

import java.util.List;

import org.ndungutse.ems.exceptions.InvalidInputException;
import org.ndungutse.ems.models.Employee;
import org.ndungutse.ems.repository.EmployeeCollection;
import org.ndungutse.ems.utils.DialogUtility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RaiseSalaryController {
    public Button cancelButton;
    @FXML
    private TextField ratingField;
    @FXML
    private TextField percentageField;
    private HelloController helloController;

    private final EmployeeCollection<String> employeeCollection = AppContext
            .getEmployeeCollection();

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void handleApplyRaise(ActionEvent event) {
        try {
            String percentageText = percentageField.getText();
            String ratingText = ratingField.getText();

            if (percentageText.isEmpty() || ratingText.isEmpty()) {
                DialogUtility.showErrorAlert("Missing Input",
                        "Please enter both percentage and minimum rating.");
                return;
            }

            double percentage = Double.parseDouble(percentageText);
            double minRating = Double.parseDouble(ratingText);

            if (percentage <= 0 || minRating < 0) {
                DialogUtility.showErrorAlert("Invalid Input",
                        "Percentage must be positive and rating cannot be negative.");
                return;
            }

            employeeCollection.giveSalaryRaise(percentage, minRating);

            // Optionally clear the fields
            percentageField.clear();
            ratingField.clear();
            this.handleClose();
            DialogUtility.showAlert("Success",
                    "Salary raise applied successfully.");
            List<Employee<String>> employees = helloController
                    .getEmployees(null, null, null, null, 0);
            helloController.displayEmployees(employees);

        } catch (NumberFormatException e) {
            DialogUtility.showErrorAlert("Invalid Format",
                    "Please enter valid numbers for percentage and rating.");
        } catch (InvalidInputException e) {
            DialogUtility.showErrorAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void handleClose() {
        cancelButton.getScene().getWindow().hide();
    }
}
