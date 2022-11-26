package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GlobalUser;

public class App extends Application {

    public static GlobalUser gu = new GlobalUser();

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Scene1.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            gu = GlobalUser.loadGlobalUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}