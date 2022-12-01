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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Album;
import model.GlobalUser;
import model.User;

public class UserController implements LogoutController{
    
    @FXML
    public ListView<Album> albumlistview;

    @FXML
    public Label tfNumPhotos;

    @FXML
    public Text tfDateRange;

    @FXML
    public Button createAlbumBtn, deleteAlbumBtn, logOutBtn,renameAlbumBtn, searchPhotosBtn;

    public static String username;

    /**
     * Stores instances of all albumss
     */
    public static ArrayList<Album> albumlist = new ArrayList<>();

    /**
     * Helps display the albumlist
     */
    public ObservableList<Album> observableList;

    /**
     * A GlobalUser instance that helps maintain the state of the program
     */
    public static GlobalUser adminuser = Photos.gu;

    /**
     * Stores current user
     */
    public static User user;

    /**
     * Current stock photo
     */
    public static boolean stock;

    // When the scene loads the page updates the album albumlistview
    public void start(Stage stage) {
        update();
        if (!albumlist.isEmpty()) {
            albumlistview.getSelectionModel().select(0);
        }

        if (albumlist.size() > 0) {
			tfNumPhotos.setText(albumlist.get(0).numPhotos + "");
			tfDateRange.setText("Date Range: \n\t" +albumlist.get(0).getFirstDate() + "\n\t" + albumlist.get(0).getLastDate());
		}
		albumlistview.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> updateAlbumDetails(newValue) );

    }

    /**
     * Updates the albums contents
     */
    public void update() {
        // tUser.setText(username + "'s Album List:");

        // we pass in the current logged in user's username and get that user
        user = adminuser.getUser(username);

        // empty the albumlist in case it is filled with previous user's albums
        albumlist.clear();

        // fill the albumlist with the albums that the logged in user created
        for (int i = 0; i < user.getAllAlbums().size(); i++) {
            albumlist.add(user.getAllAlbums().get(i));
        }

        observableList = FXCollections.observableArrayList(albumlist);
        albumlistview.setItems(observableList);
        albumlistview.refresh();
    }

    @FXML
    public void onCreateAlbum(ActionEvent event) throws IOException {
        TextInputDialog userDialog = new TextInputDialog();
        userDialog.setTitle("Create Album");
        userDialog.setHeaderText(null);
        userDialog.setContentText("Enter an Album Name: ");
        userDialog.setGraphic(null);

        Optional<String> result = userDialog.showAndWait();
        TextField newName = userDialog.getEditor();
        String albumname = newName.getText().trim();
        // Album album = new Album(albumname);

        if (result.isPresent()) {
            
            if(user.exists(albumname)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Album already exists.");
                alert.setContentText("Try entering a new album!");
                alert.showAndWait();
                return;
            } else {
                user.addNewAlbum(new Album(albumname));
                System.out.println("Albums: " + user.getAllAlbums());
                update();
                newName.clear();
            }
            GlobalUser.saveGlobalUser(adminuser);
        } else{
            userDialog.close();
        }

    }

    @FXML
    public void onDeleteAlbum(ActionEvent event) throws IOException {
        int index = albumlistview.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this album?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            user.removeAlbum(index);
            update();
            GlobalUser.saveGlobalUser(adminuser);

            int lastuserindex = user.getAllAlbums().size();
            if (user.getAllAlbums().size() == 1) {
                albumlistview.getSelectionModel().select(0);
            } else if (index == lastuserindex) {
                albumlistview.getSelectionModel().select(lastuserindex - 1);
            } else {
                albumlistview.getSelectionModel().select(index);
            }
            
        } else {
            alert.close();
        }
    }

    @FXML
    public void onLogOutBtnClicked(ActionEvent event) throws IOException {
        logUserOut(event);
    }

    @FXML
    public void onRenameAlbum(ActionEvent event) throws IOException {
        TextInputDialog userDialog = new TextInputDialog();
        userDialog.setTitle("Rename Album");
        userDialog.setHeaderText(null);
        userDialog.setContentText("Enter new Album Name: ");
        userDialog.setGraphic(null);

        Optional<String> result = userDialog.showAndWait();
        TextField newName = userDialog.getEditor();

        String newReName = newName.getText().trim();

        int index = albumlistview.getSelectionModel().getSelectedIndex();
        Album album = user.getAlbum(index);
        Album tempAlbum = new Album(newReName);

        if (result.isPresent()) {
            if(newReName.equals(album.getAlbumName())) {
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Rename Error");
                alert2.setContentText("Cannot use same name again");
                alert2.showAndWait();
                return;
            } else if (user.albumExists(tempAlbum)) {
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Rename Error");
                alert2.setContentText("Album name already in use.");
                alert2.showAndWait();
                return;
            } else {
                album.setAlbumName(newReName);
                update();
                GlobalUser.saveGlobalUser(adminuser);
            }
        }else{
            userDialog.close();
        }    
    }

    @FXML
    public void onSearchPhotos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchPhotos.fxml"));
        Parent root = (Parent) loader.load();
        SearchPhotosController sPhotosController = loader.getController();
        Scene adminSettingsScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sPhotosController.start();
        appStage.setScene(adminSettingsScene);
        appStage.show();
    }

    public void updateAlbumDetails(Album newValue) {
		if (albumlist.size() > 0) {
			tfNumPhotos.setText(newValue.numPhotos + "");
			tfDateRange.setText("Date Range: \n\t" +newValue.getFirstDate() + "\n\t" + newValue.getLastDate());
		}
	}

    @FXML
    public void onMouseClicked(MouseEvent event) throws IOException {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){

                PhotosDisplayController.currentUser = user;
                PhotosDisplayController.album = albumlistview.getSelectionModel().getSelectedItem();
                PhotosDisplayController.allUserAlbums = albumlist;

                // System.out.println("Double clicked");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhotosDisplay.fxml"));
                Parent root = (Parent) loader.load();
                PhotosDisplayController pDisplayController = loader.getController();
                Scene adminSettingsScene = new Scene(root);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                pDisplayController.start(appStage);
                appStage.setScene(adminSettingsScene);
                appStage.show();
            }
        }
    }
}
