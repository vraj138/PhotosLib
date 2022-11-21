package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;

import application.User;

public class SceneA1Controller {
    @FXML ListView<User> listView;

    private ObservableList<User> obListUsers;

    public void start(Stage mainStage) throws IOException{
        String content = readFile("userdata/usrerLists.json");

        JSONArray userArray = new JSONArray(content);
        ArrayList<User> users = new ArrayList<User>();

        for(int i = 0; i < userArray.length(); i++) {
            User fromstorage = new User(userArray.getJSONObject(i).getString("username"));
            users.add(fromstorage);
        }

        obListUsers = FXCollections.observableArrayList(users);
		listView.setItems(obListUsers);
    }

    private String readFile(String path) throws IOException {
		File file = new File(path);
		
		// Build a string from file's contents
		StringBuilder content = new StringBuilder((int)file.length());
		
		try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
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

        if(result.isPresent() && input.getText() != null && input.getText().toString().length() != 0){
            User newUser = new User(input.getText().trim());
            System.out.println("Added " + newUser);
            obListUsers.add(newUser);
            
            listView.getSelectionModel().clearSelection();
            listView.getSelectionModel().select(obListUsers.indexOf(newUser));

            saveUsers(obListUsers);
            return;
        }else{
            userDialog.close();
        }
    }

    @FXML
    void onDeleteUser(ActionEvent event) {

    }

    public void saveUsers(ObservableList<User> userList) throws IOException {
		JSONArray userLists = new JSONArray(obListUsers);
		try (FileWriter file = new FileWriter("userdata/userLists.json")) {
			file.write(userLists.toString());
        }
    }
}
