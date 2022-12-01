package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import application.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Album;
import model.GlobalUser;
import model.Tag;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Deep Parekh and Vraj Patel
 */
public class SearchPhotosController implements LogoutController {

    @FXML
    public Button addTagBtn, backBtn, searchByDateBtn, searchByTagsBtn;

    @FXML
    public ListView<String> listview;

    @FXML
    public TextField tfName, tfValue;

    public ObservableList<String> observableList;

    public static GlobalUser adminuser = Photos.gu;

    public ArrayList<Tag> listOfTags = new ArrayList<Tag>();

    public ArrayList<String> displayListOfTags = new ArrayList<String>();

    public void start() {
        // update();
        // if (!allUsernames.isEmpty()) {
        // listView.getSelectionModel().select(0);
        // }
        // listView.setItems(usernamesList);
        // listView.refresh();
    }

    /**
     * @param event
     */
    @FXML
    public void onAddTagBtn(ActionEvent event) {

        if (tfName.getText().trim().isEmpty() || tfValue.getText().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Missing Values");
            alert.setHeaderText("Enter a key and its value!");
            alert.setContentText("The key and value must be filled!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
            return;
        }
        Tag tag = new Tag(tfName.getText().trim(), tfValue.getText().trim());
        listOfTags.add(tag);
        update();
    }

    /**
     * @param event
     * @throws IOException
     */
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

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    public void onLogOutBtnClicked(ActionEvent event) throws IOException {
        logUserOut(event);
    }

    /**
     * @param event
     */
    @FXML
    public void onSearchByTagsBtn(ActionEvent event) {

    }

    /**
     * @param event
     */
    @FXML
    public void onSearchByDateBtn(ActionEvent event) {

    }

    public void update() {
        displayListOfTags.clear();
        for (Tag tag : listOfTags) {
            displayListOfTags.add(tag.tagName + " = " + tag.value);
        }
        observableList = FXCollections.observableArrayList(displayListOfTags);
        listview.setItems(observableList);

        tfName.clear();
        tfValue.clear();

    }

}
