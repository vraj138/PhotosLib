package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.GlobalUser;

/**
 * @author Deep Parekh and Vraj Patel
 */
public class Photos extends Application {

    public static GlobalUser gu = new GlobalUser();
    public Stage appStage;

    /**
     * @param primaryStage main stage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            appStage = primaryStage;

            Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeLogin.fxml"));
            Scene scene = new Scene(root);

            appStage.setTitle("Photo Gallery");
            appStage.setScene(scene);
            appStage.setResizable(false);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    GlobalUser.saveGlobalUser(gu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print("Closed");
            }
        });

    }

    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            gu = GlobalUser.loadGlobalUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}