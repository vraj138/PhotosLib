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
import model.GlobalUser;
import model.User;

import java.io.IOException;

public class SceneA1Controller {
    @FXML
    ListView<String> listView;

    GlobalUser gu = new GlobalUser();

    ArrayList<String> allUsernames = gu.getAllUsernames();

    // ArrayList<User> users = new ArrayList<User>();
    public ObservableList<String> usernamesList = FXCollections.observableArrayList(allUsernames);

    public void start(Stage stage) {
        System.out.println(allUsernames);
        System.out.println("hi there");
        listView.setItems(usernamesList);
        listView.refresh();
    }

    @FXML
    void onAddUser(ActionEvent event) throws IOException {
        TextInputDialog userDialog = new TextInputDialog();
        userDialog.setTitle("Add username");
        userDialog.setContentText("Enter username: ");

        Optional<String> result = userDialog.showAndWait();

        TextField input = userDialog.getEditor();
        // && input.getText() != null && input.getText().toString().length() != 0

        if (result.isPresent()) {

            usernamesList.add(input.getText());
            gu.addUser(input.getText());

            System.out.println(gu.getAllUsers());
            listView.setItems(usernamesList);
            listView.refresh();

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
