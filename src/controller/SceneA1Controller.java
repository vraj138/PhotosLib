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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class SceneA1Controller {
    @FXML
    ListView<String> listView;

    // ArrayList<User> users = new ArrayList<User>();
    private ObservableList<String> usernamesList = FXCollections.observableArrayList();

    public void start(Stage mainStage) throws IOException {
        String content = readFile("userdata/usrerLists.json");

        // JSONArray userArray = new JSONArray(content);
        // for (int i = 0; i < userArray.length(); i++) {
        // usernamesList.add(userArray.getJSONObject(i).toString());
        // }

        listView.setItems(usernamesList);
    }

    private String readFile(String path) throws IOException {
        File file = new File(path);

        // Build a string from file's contents
        StringBuilder content = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine() + System.lineSeparator());
            }
            return content.toString();
        }
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
