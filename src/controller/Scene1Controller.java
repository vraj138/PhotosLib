package controller;

import java.io.IOException;
import java.util.*;
import application.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Album;
import model.User;
import model.GlobalUser;

public class Scene1Controller {

    @FXML
    private TextField username;

    public static GlobalUser gu = App.gu;

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException {
        Stage mainWindow = (Stage) username.getScene().getWindow();
        String user = username.getText().trim();

        if (user.equals("admin")) {
            mainWindow.setTitle("Welcome " + user);
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SceneA1.fxml"));
            // Parent root = loader.load();
            // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Scene scene = new Scene(root);
            // stage.setScene(scene);
            // stage.show();

            // SceneA1Controller adminController = loader.getController();
            // adminController.start(stage);

            FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/SceneA1.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);	
			stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            SceneA1Controller adminController = loader.getController();
            adminController.start(stage);
        } else if (gu.checkUser(user)) {
            User currentUser = gu.getCurrentUser();
            // ArrayList<Album> userAlbums = currentUser.getAllAlbums();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SceneB1.fxml"));
            Parent sceneManager = (Parent) fxmlLoader.load();
            Scene userScene = new Scene(sceneManager);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(userScene);
            appStage.show();
        } else if (user.isEmpty() || user == null) {
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