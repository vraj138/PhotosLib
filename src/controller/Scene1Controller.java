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

public class Scene1Controller {
    
    @FXML
    private TextField username;

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException {
        Stage mainWindow = (Stage) username.getScene().getWindow();
        String user = username.getText();

        if(user.equals("admin")){
            mainWindow.setTitle("Welcome " + user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SceneA1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            
        }
        
    }

}