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
import javafx.scene.control.Button;

public class PhotosDisplayController {

    @FXML
    public Button addPhotosBtn, backBtn, copyPhotoBtn, editCaptionBtn, logOutBtn, movePhotoBtn, removePhotosBtn;

    @FXML
    public TextField tfCopyAlbum, tfMoveAlbum;

    public void start(){

    }
    
    @FXML
    public void onAddPhotosBtn(ActionEvent event) {

    }

    @FXML
    public void onBackBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserAlbums.fxml"));
        Parent root = (Parent) loader.load();
        UserController userController = loader.getController();
        Scene adminSettingsScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        userController.start(appStage);
        appStage.setScene(adminSettingsScene);
        appStage.show();
    }

    @FXML
    public void onCopyPhotoBtn(ActionEvent event) {

    }

    @FXML
    public void onEditCaptionBtn(ActionEvent event) {

    }

    @FXML
    public void onLogOutBtnClicked(ActionEvent event) {

    }

    @FXML
    public void onMovePhotoBtn(ActionEvent event) {

    }

    @FXML
    public void onRemovePhotosBtn(ActionEvent event) {

    }

}

