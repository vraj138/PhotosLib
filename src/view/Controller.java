package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    // @FXML
    // private TextField tfTitle;

    // @FXML
    // void btnOKClicked(ActionEvent event) {
    //     Stage mainWindow = (Stage) tfTitle.getScene().getWindow();
    //     String title = tfTitle.getText();
    //     mainWindow.setTitle(title);
    // }
    
    @FXML
    private TextField username;

    @FXML
    void btnLoginClicked(ActionEvent event) {
        Stage mainWindow = (Stage) username.getScene().getWindow();
        String title = username.getText();
        mainWindow.setTitle("Welcome " + title);
    }

}