package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneB2Controller {

    @FXML
    private TextField searchphoto;

    @FXML
    void onAddPhotos(ActionEvent event) {

    }

    @FXML
    void onBackBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/WelcomeLogin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onEditCaption(ActionEvent event) {

    }

    @FXML
    void onRemovePhotos(ActionEvent event) {

    }

    @FXML
    void onSearchPhotos(ActionEvent event) {

    }

}
