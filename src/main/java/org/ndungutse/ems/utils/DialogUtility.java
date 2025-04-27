package org.ndungutse.ems.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.Optional;

public class DialogUtility {
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(DialogUtility.class
                .getResource("/styles/dialog-style.css").toExternalForm());
        alert.showAndWait();
    }

    public static void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(DialogUtility.class
                .getResource("/styles/dialog-style.css").toExternalForm());
        alert.showAndWait();
    }

    public static boolean showConfirmation(String title, String headerText) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(title);
        confirmation.setHeaderText(headerText);
        DialogPane dialogPane = confirmation.getDialogPane();
        dialogPane.getStylesheets().add(DialogUtility.class
                .getResource("/styles/dialog-style.css").toExternalForm());
        Optional<ButtonType> result = confirmation.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
