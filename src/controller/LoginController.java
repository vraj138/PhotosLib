package controller;

import java.io.IOException;
import java.util.*;
import application.Photos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.GlobalUser;
import model.*;

public class LoginController {

    @FXML
    private TextField username;

    public static GlobalUser gu = Photos.gu;

    @FXML
    public void btnLoginClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        // Stage mainWindow = (Stage) username.getScene().getWindow();
        String user = username.getText().trim();
        // System.out.println(gu.getAllUsers());
        

        if (user.equals("admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminSettings.fxml"));
            Parent root = (Parent) loader.load();
            AdminSettingsController asController = loader.getController();
            Scene adminSettingsScene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            asController.start();
            appStage.setScene(adminSettingsScene);
            appStage.show();

            // FXMLLoader loader = new FXMLLoader();
            // loader.setLocation(getClass().getResource("../view/SceneA1.fxml"));
            // AnchorPane root = (AnchorPane) loader.load();

            // // Creates Stage for PhotoPage
            // // Stage stage = new Stage();
            // stage.initModality(Modality.WINDOW_MODAL);
            // // stage.initOwner(currStage);
            // Scene scene = new Scene(root);
            // stage.setScene(scene);
            // stage.setResizable(false);
            // stage.show();

        } else if (gu.checkUser(user)) {
            // User currentUser = gu.getCurrentUser();
            System.out.println("Hi" + gu.getAllUsers());
            UserController.username = user;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserAlbums.fxml"));
            Parent root = (Parent) loader.load();

            UserController userController = loader.getController();

            Scene userAlbumScene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            userController.start(appStage);

            appStage.setScene(userAlbumScene);
            appStage.show();

        } else if (user.isEmpty() || user == null) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter a username");

            Optional<ButtonType> buttonClicked = alert.showAndWait();
            if (buttonClicked.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }

        } else {
            System.out.println("Incorrect Input");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Issue Encountered");
            alert.setHeaderText("Please enter a valid username");
            Optional<ButtonType> buttonClicked = alert.showAndWait();
            if (buttonClicked.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }

        }
    }

}