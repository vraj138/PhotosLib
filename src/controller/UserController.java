package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import application.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.Album;
import model.GlobalUser;
import model.User;

public class UserController implements LogoutController{
    
    @FXML
    public ListView<Album> albumlistview;

    @FXML
    public Text dateRange, numOfPhotos;

    @FXML
    public Button createAlbumBtn, deleteAlbumBtn, logOutBtn,renameAlbumBtn, searchPhotosBtn;

    

    // @FXML
    // public Button mLogOut, mDisplay, mOpenAlbum, mRenameAlbum, mDeleteAlbum, mSearch, mAddAlbum;

    // @FXML
    // public MenuButton mSortBy;

    // @FXML
    // public Text tUser, tNumber, tDateSpan;

    // @FXML
    // public TextField tfName, tfNewAlbum;

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

        stage.setTitle(" Collection of Photos");
        if (!albumlist.isEmpty()) {
            albumlistview.getSelectionModel().select(0);
        }

        // Listen for selection changes
        // if (albumlist.size() > 0) {
        // tfName.setText(albumlist.get(0).albumName);
        // tNumber.setText("Number of Photos: " + albumlist.get(0).numPhotos);
        // tDateSpan.setText("Date Span (First, Last): \n\t" +
        // albumlist.get(0).getFirstDate() + "\n\t"
        // + albumlist.get(0).getLastDate());
    }

    // albumlistview.getSelectionModel().selectedItemProperty()
    // .addListener((v, oldValue, newValue) -> updateContent(newValue));
    // }

    /**
     * Updates the values of the Album properties
     * 
     * @param newValue The new album value
     */
    // private void updateContent(Album newValue) {
    // if (newValue != null) {
    // tfName.setText(newValue.albumName);
    // tNumber.setText("Number of Photos: " + newValue.numPhotos);
    // tDateSpan.setText("Date Span: \n\t" + newValue.getFirstDate() + " \n\t" +
    // newValue.getLastDate());
    // }
    // }

    /**
     * Updates properties of the album
     */
    // public void updateContentBack() {
    // if (albumlist.size() > 0) {
    // Album alb = albumlistview.getSelectionModel().getSelectedItem();
    // tNumber.setText("Number of Photos: " + alb.numPhotos);
    // tDateSpan.setText("Date Span: \n\t" + alb.getFirstDate() + "\n\t" +
    // alb.getLastDate());
    // }
    // }

    /**
     * Add album to as user's album list
     * 
     * @throws IOException
     */
    public void addAlbum() throws IOException {
        // String albumname = tfNewAlbum.getText().trim();
        // Album album = new Album(albumname);

        // if (albumname.isEmpty() || albumname == null) {
        //     Alert alert = new Alert(AlertType.ERROR);
        //     alert.setTitle("Empty Field");
        //     alert.setContentText("Please enter an album name.");
        //     alert.showAndWait();
        //     return;
        // } else if (user.exists(album)) {
        //     Alert alert = new Alert(AlertType.ERROR);
        //     alert.setTitle("Album already exists.");
        //     alert.setContentText("Try entering a new album!");
        //     alert.showAndWait();
        //     return;
        // } else {
        //     user.addNewAlbum(album);
        //     update();
        //     tfNewAlbum.clear();
        // }
        // User.saveUser(user);
    }

    /**
     * Renames an album
     * 
     * @throws IOException
     */
    public void renameAlbum() throws IOException {
    //     String newName = tfName.getText().trim();

    //     int index = albumlistview.getSelectionModel().getSelectedIndex();
    //     Album album = user.getAlbum(index);
    //     Optional<ButtonType> result;
    //     Album tempAlbum = new Album(newName);

    //     if (newName.length() == 0) {
    //         Alert alert2 = new Alert(AlertType.ERROR);
    //         alert2.setTitle("Rename Error");
    //         alert2.setContentText("Please enter a valid album name.");
    //         alert2.showAndWait();
    //         return;
    //     } else if (newName.equals(album.albumName)) {
    //         Alert alert2 = new Alert(AlertType.ERROR);
    //         alert2.setTitle("Rename Error");
    //         alert2.setContentText("No changes made. Please enter a valid album name before clicking 'Rename'.");
    //         alert2.showAndWait();
    //         return;
    //     } else if (user.exists(tempAlbum)) {
    //         Alert alert2 = new Alert(AlertType.ERROR);
    //         alert2.setTitle("Rename Error");
    //         alert2.setContentText("Album name already in use.");
    //         alert2.showAndWait();
    //         return;
    //     } else {
    //         Alert alert = new Alert(AlertType.CONFIRMATION);
    //         alert.setTitle("Confirm Rename");
    //         alert.setHeaderText(null);
    //         alert.setContentText("Are you sure you want to rename this album?");
    //         result = alert.showAndWait();
    //     }

    //     if (result.get() == ButtonType.OK) {
    //         album.setAlbumName(newName);
    //         update();
    //         User.saveUser(user);
    //     } else {
    //         return;
    //     }
    //     return;
    }

    /**
     * Opens an album and sends it to the photoview scene
     * 
     * @param event
     * @throws IOException
     */
    // public void openAlbum(ActionEvent event) throws IOException {
    // PhotoViewController.user = user;
    // PhotoViewController.album = albumlistview.getSelectionModel().getSelectedItem();
    // PhotoViewController.albumlist = albumlist;

    // // Changed
    // int albumindex = albumlistview.getSelectionModel().getSelectedIndex();
    // int currentuserindex = adminuser.getUserIndex();
    // if (adminuser.getAllUsers().get(currentuserindex).getAllAlbums().size() == 0)
    // {
    // Alert alert = new Alert(AlertType.ERROR);
    // alert.setTitle("Empty Deletion");
    // alert.setHeaderText(null);
    // alert.setContentText("Cannot delete something that isn't there");
    // alert.showAndWait();
    // return;
    // }
    // Album album =
    // adminuser.getAllUsers().get(currentuserindex).getAllAlbums().get(albumindex);

    // adminuser.getAllUsers().get(currentuserindex).setCurrentAlbum(album);
    // // End Change

    // FXMLLoader fxmlLoader = new
    // FXMLLoader(getClass().getResource("/view/PhotoView.fxml"));
    // Parent sceneManager = (Parent) fxmlLoader.load();
    // PhotoViewController photoController = fxmlLoader.getController();
    // Scene adminScene = new Scene(sceneManager);
    // Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // photoController.start(appStage);
    // appStage.setScene(adminScene);
    // appStage.show();
    // }

    /**
     * Deletes an album from the user list
     * 
     * @throws IOException
     */
    public void deleteAlbum() throws IOException {
        // int index = albumlistview.getSelectionModel().getSelectedIndex();
        // Alert alert = new Alert(AlertType.CONFIRMATION);
        // alert.setTitle("Confirm Delete");
        // alert.setHeaderText(null);
        // alert.setContentText("Are you sure you want to delete this album?");

        // Optional<ButtonType> result = alert.showAndWait();
        // if (result.get() == ButtonType.OK) {
        //     user.removeAlbum(index);
        //     update();
        //     User.saveUser(user);

        //     if (user.getAllAlbums().size() == 0) {
        //         mDeleteAlbum.setVisible(false);
        //     } else {
        //         int lastuserindex = user.getAllAlbums().size();
        //         if (user.getAllAlbums().size() == 1) {
        //             albumlistview.getSelectionModel().select(0);
        //         } else if (index == lastuserindex) {
        //             albumlistview.getSelectionModel().select(lastuserindex - 1);
        //         } else {
        //             albumlistview.getSelectionModel().select(index);
        //         }
        //     }
        // } else {
        //     return;
        // }
        // return;
    }

    /**
     * Redirects the user to the search page
     * 
     * @param event
     * @throws IOException
     */
    // public void search(ActionEvent event) throws IOException {
    // FXMLLoader fxmlLoader = new
    // FXMLLoader(getClass().getResource("/view/Search.fxml"));
    // Parent sceneManager = (Parent) fxmlLoader.load();
    // SearchController searchController = fxmlLoader.getController();
    // Scene adminScene = new Scene(sceneManager);
    // Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // searchController.start();
    // appStage.setScene(adminScene);
    // appStage.show();

    // }

    /**
     * Logs the current user out
     * 
     * @param event
     * @throws IOException
     */
    // public void logOut(ActionEvent event) throws IOException {
    // logUserOut(event);
    // // System.out.println("Logged Out");
    // }

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
            User.saveUser(user);
        } else{
            userDialog.close();
        }



    }

    @FXML
    public void onDeleteAlbum(ActionEvent event) throws IOException {
        // int index = albumlistview.getSelectionModel().getSelectedIndex();
        // Alert alert = new Alert(AlertType.CONFIRMATION);
        // alert.setTitle("Confirm Delete");
        // alert.setHeaderText(null);
        // alert.setContentText("Are you sure you want to delete this album?");

        // Optional<ButtonType> result = alert.showAndWait();
        // if (result.get() == ButtonType.OK) {
        //     user.removeAlbum(index);
        //     update();
        //     User.saveUser(user);

        //     if (user.getAllAlbums().size() == 0) {
        //         deleteAlbumBtn.setVisible(false);
        //     } else {
        //         // deleteAlbumBtn.setVisible(true);
        //         int lastuserindex = user.getAllAlbums().size();
        //         if (user.getAllAlbums().size() == 1) {
        //             albumlistview.getSelectionModel().select(0);
        //         } else if (index == lastuserindex) {
        //             albumlistview.getSelectionModel().select(lastuserindex - 1);
        //         } else {
        //             albumlistview.getSelectionModel().select(index);
        //         }
        //     }
        // } else {
        //     alert.close();
        // }
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
                User.saveUser(user);
            }
        }else{
            userDialog.close();
        }    
    }

    @FXML
    public void onSearchPhotos(ActionEvent event) {

    }
}
