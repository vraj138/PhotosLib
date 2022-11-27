package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.*;

import java.io.IOException;



public class SceneA1Controller {
    @FXML
    ListView<String> listView;

    // ArrayList<User> users = new ArrayList<User>();
    private ObservableList<String> usernamesList = FXCollections.observableArrayList();

    public Stage currStage;

    public void start(Stage stage) throws IOException {
        // System.out.println("Hi: ");
        currStage = stage;
        System.out.println("Hi: ");
        listView.setItems(usernamesList);
    }

    

    @FXML
    void onAddUser(ActionEvent event) throws IOException {
        TextInputDialog userDialog = new TextInputDialog();
        userDialog.setTitle("Add username");
        userDialog.setContentText("Enter username: ");

        Optional<String> result = userDialog.showAndWait();

        TextField input = userDialog.getEditor();
        // && input.getText() != null && input.getText().toString().length() != 0
        // System.out.println("Hi: ");
        if (result.isPresent()) {

            usernamesList.add(input.getText());
            listView.setItems(usernamesList);

            return;
        } else {
            userDialog.close();
        }
    }

    @FXML
    void onDeleteUser(ActionEvent event) {
        
    }

    // public void saveUsers(ObservableList<String> userList) throws IOException {
    // JSONArray userLists = new JSONArray(usernamesList);
    // try (FileWriter file = new FileWriter("userdata/userLists.json")) {
    // file.write(userLists.toString());
    // }
    // }

    public void updateUsers() {
        listView.refresh();
    }
}
