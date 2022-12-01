package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.*;

import application.Photos;
import model.GlobalUser;
import model.User;
import java.io.IOException;

/**
 * @author Deep Parekh and Vraj Patel
 */
public class AdminSettingsController implements LogoutController {
    @FXML
    public ListView<String> listView;

    @FXML
    public Button adminAdd, adminDelete, adminLogOut;

    public static GlobalUser gu = Photos.gu;

    public static ArrayList<String> allUsernames = new ArrayList<String>();

    public ObservableList<String> usernamesList;

    public void start() {
        update();
        if (!allUsernames.isEmpty()) {
            listView.getSelectionModel().select(0);
        }
        // listView.setItems(usernamesList);
        // listView.refresh();
    }

    public void update() {
        if (gu.getAllUsers().size() == 1) {
            adminDelete.setVisible(false);
        } else {
            adminDelete.setVisible(true);
        }

        allUsernames.clear();
        for (int i = 0; i < gu.getAllUsers().size(); i++) {
            allUsernames.add(gu.getAllUsers().get(i).getUsername());
        }
        usernamesList = FXCollections.observableArrayList(allUsernames);
        listView.setItems(usernamesList);
        listView.refresh();
    }

    /**
     * @param event action event
     * @throws IOException
     */
    @FXML
    void onAddUser(ActionEvent event) throws IOException {
        TextInputDialog userDialog = new TextInputDialog();
        userDialog.setTitle("Add username");
        userDialog.setContentText("Enter username: ");

        Optional<String> result = userDialog.showAndWait();

        TextField input = userDialog.getEditor();
        String user = input.getText();
        // && input.getText() != null && input.getText().toString().length() != 0

        if (result.isPresent()) {

            // check if user already exists
            if (gu.userExists(user)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Error.");
                alert.setContentText("Username already exists. Try entering a new username!");
                alert.showAndWait();
                return;
            } else if (user.isEmpty() || user == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Error");
                alert.setContentText("Empty Field: Please enter a username.");
                alert.showAndWait();
                return;
            } else if (user.equals("admin")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Error");
                alert.setContentText("Cannot add 'admin' to Users.");
                alert.showAndWait();
                return;
            } else {
                gu.addUser(user);
                update();
                userDialog.close();
                // System.out.println(gu.getAllUsers());
            }
            GlobalUser.saveGlobalUser(gu);

        } else {
            userDialog.close();
        }
    }

    /**
     * @param event action event from button clicked
     * @throws IOException
     */
    @FXML
    void onDeleteUser(ActionEvent event) throws IOException {
        int index = listView.getSelectionModel().getSelectedIndex();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete User Operation");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to delete this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            gu.deleteUser(index);
            update();
            // System.out.println("Delete " + gu.getAllUsers());
            GlobalUser.saveGlobalUser(gu);

            if (gu.getAllUsers().size() == 1) {
                adminDelete.setVisible(false);
            } else {
                int lastuserindex = gu.getAllUsers().size();
                if (gu.getAllUsers().size() == 1) {
                    listView.getSelectionModel().select(0);
                } else if (index == lastuserindex) {
                    listView.getSelectionModel().select(lastuserindex - 1);
                } else {
                    listView.getSelectionModel().select(index);
                }
            }

        } else {
            return;
        }
        return;

    }

    /**
     * @param event action event from button clicked
     * @throws IOException
     */
    @FXML
    void onLogOutBtnClicked(ActionEvent event) throws IOException {
        logUserOut(event);
    }

}
