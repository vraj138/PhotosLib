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

public class SceneB1Controller {

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Scene1.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onRenameAlbum(ActionEvent event) {

    }

    @FXML
    void onSearchPhotos(ActionEvent event) {

    }

}
