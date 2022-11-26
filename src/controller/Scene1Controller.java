package controller;

import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Album;
import model.User;

public class Scene1Controller {

    @FXML
    private TextField username;

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException {
        Stage mainWindow = (Stage) username.getScene().getWindow();
        String user = username.getText().trim();

        if (user.equals("admin")) {
            mainWindow.setTitle("Welcome " + user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SceneA1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (checkUser(username)) {
            User currentUser = getCurrentUser();
            ArrayList<Album> useralbums = currentUser.getAllAlbums();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SceneB1.fxml"));
            Parent sceneManager = (Parent) fxmlLoader.load();
            Scene userScene = new Scene(sceneManager);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(userScene);
            appStage.show();
        } else if (username.isEmpty() || username == null) {
            // System.out.print("Empty String");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter a username");
            // alert.showAndWait();
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