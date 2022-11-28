package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import controller.LogoutController;

public class SceneB1Controller implements LogoutController {

    @FXML
    private ListView<?> albumlistview;

    @FXML
    private TextField searchphoto;

    @FXML
    void onCreateAlbum(ActionEvent event) {

    }

    @FXML
    void onDeleteAlbum(ActionEvent event) {

    }

    @FXML
    void onLogOutBtnClicked(ActionEvent event) throws IOException {
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

    @FXML
    void onRenameAlbum(ActionEvent event) {

    }

    @FXML
    void onSearchPhotos(ActionEvent event) {

    }

}
