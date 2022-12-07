package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Deep Parekh and Vraj Patel
 */
public interface LogoutController {
    default void logUserOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out of your account?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WelcomeLogin.fxml"));
            Parent root = (Parent) loader.load();
            Scene adminScene = new Scene(root);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(adminScene);
            app.show();
        } else {
            return;
        }
    }
}
