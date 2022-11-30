package controller;

import java.io.File;
import java.io.IOException;
import java.util.*;


import javafx.util.Callback;

import application.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.GlobalUser;
import model.Photo;
import model.User;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonType;

public class PhotosDisplayController implements LogoutController{

    @FXML
    public Button addPhotosBtn, backBtn, copyPhotoBtn, editCaptionBtn, logOutBtn, movePhotoBtn, removePhotosBtn;

    @FXML
    public TextField tfCopyAlbum, tfMoveAlbum;

    @FXML
    public Label albumNameLabel, captionLabel;

    @FXML
    public ListView<Photo> photoListview;

    public static User currentUser;

    public static ArrayList<Photo> albumPhotos = new ArrayList<>();
	
	public ObservableList<Photo> observableList;
    
	public static ArrayList<Album> allUserAlbums;
    
	public static Album album; 

    public static GlobalUser gu = Photos.gu;

    public void start(Stage appStage){

        int currentUserIndex = gu.getCurrentUserIndex();
		gu.getAllUsers().get(currentUserIndex).setCurrentAlbum(album);
        albumNameLabel.setText(gu.getCurrentUser().getCurrentalbum().getAlbumName());
        update();

        
    }
    
    @FXML
    public void onAddPhotosBtn(ActionEvent event) throws IOException {
        FileChooser choosePhotos = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.bmp", "*.jpeg", "*.gif", "*.jpg");
		choosePhotos.getExtensionFilters().add(extFilterJPG);
		File photoFile = choosePhotos.showOpenDialog(null);
		
		if (photoFile == null) {
			return;
		} /*else if (album.exists(photoFile.getAbsolutePath())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Photo already exists.");
			alert.setContentText("Try entering a new photo!");
			alert.showAndWait();
			return;
		}*/ else {
			
			
				String filepath = photoFile.getAbsolutePath();
				Photo newPhoto;
				if(gu.getCurrentUser().getUsername().equals("stock")) {
					int index;
					if (filepath.contains("stockphotos")) {
						index = filepath.indexOf("stockphotos");
						String newfilepath = filepath.substring(index,filepath.length());
						Photo newPhoto2 = new Photo(photoFile, newfilepath);
						newPhoto2.stockPhoto = true;
						album.addPhotoToAlbum(newPhoto2);
					} else {
                        
						newPhoto = new Photo(photoFile, filepath);	
                        for(int i = 0; i <= album.getAllPhotos().size(); i++){
                            System.out.println("Inside for loop");
                            if(album.getAllPhotos().get(i).getPhotoName().equals(newPhoto.getPhotoName())){
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Add Photo Error");
                                alert.setHeaderText("Cannot add same photo again");
                                alert.showAndWait();
                                Optional<ButtonType> buttonClicked=alert.showAndWait();
                                if (buttonClicked.get()==ButtonType.OK) {
                                    alert.close();
                                }
                                else {
                                    alert.close();
                                }
                            }else{
                                album.addPhotoToAlbum(newPhoto);
                            }
                        }
						
                        
					}
				} else {
                    System.out.println("Inside else");
					newPhoto = new Photo(photoFile, filepath);	
                    if(album.getAllPhotos().size() == 0){
                        System.out.println("Inside if");
                        album.addPhotoToAlbum(newPhoto);
                        update();
                        return;
                    }
                    for(int i = 0; i <= album.getAllPhotos().size(); i++){
                        System.out.println("Inside for loop");
                        if(album.getAllPhotos().get(i).getPhotoName().equals(newPhoto.getPhotoName())){
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Add Photo Error");
                            alert.setHeaderText("Cannot add same photo again");
                            alert.showAndWait();
                            Optional<ButtonType> buttonClicked=alert.showAndWait();
                            if (buttonClicked.get()==ButtonType.OK) {
                                alert.close();
                            }
                            else {
                                alert.close();
                            }
                        }else{
                            album.addPhotoToAlbum(newPhoto);
                            update();
                        }
                    }
					// album.addPhotoToAlbum(newPhoto);
                    for (int i = 0; i < album.getAllPhotos().size(); i++) {
                        System.out.println(album.getAllPhotos().get(i).getPhotoName());
                    }
				}
				update();
			
		}
		// if(gu.getCurrent().getCurrentAlbum().getPhotos().size() > 0) {
		// 	mDelete.setVisible(true);
		// }
		if(!albumPhotos.isEmpty()) {
    		photoListview.getSelectionModel().select(0); //select first user
		}
		
		// Album.saveAlbum(album);
		
    }

    @FXML
    public void onBackBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserAlbums.fxml"));
        Parent root = (Parent) loader.load();
        UserController userController = loader.getController();
        Scene adminSettingsScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        userController.start(appStage);
        appStage.setScene(adminSettingsScene);
        appStage.show();
    }

    @FXML
    public void onCopyPhotoBtn(ActionEvent event) {
        allUserAlbums = gu.getCurrentUser().getAllAlbums();
        System.out.println(allUserAlbums);
        
        String destAlbumName = tfCopyAlbum.getText().trim();
		boolean checkAlbuminList = false;
		int albumIndex = 0;
		for (int i = 0; i < allUserAlbums.size(); i++) {
			Album x = allUserAlbums.get(i);
			if (x.getAlbumName().equals(destAlbumName)) {
				checkAlbuminList = true;
				albumIndex = i;
			}
		}

		// Copy
		if (!destAlbumName.isEmpty() && checkAlbuminList) {
			
            Album newAlbum = allUserAlbums.get(albumIndex);
            Photo photo = photoListview.getSelectionModel().getSelectedItem();
            newAlbum.addPhotoToAlbum(photo);
            
            // newAlbum.save(newAlbum);

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Copy Photo Error");
			alert.setHeaderText("Album not found or does not exist.");
			alert.showAndWait();
			Optional<ButtonType> buttonClicked=alert.showAndWait();
			if (buttonClicked.get()==ButtonType.OK) {
				alert.close();
			}
			else {
				alert.close();
			}
			return;
		}
		System.out.println("move");
    }

    @FXML
    public void onEditCaptionBtn(ActionEvent event) {

    }

    @FXML
    public void onLogOutBtnClicked(ActionEvent event) throws IOException {
        logUserOut(event);
    }

    @FXML
    public void onMovePhotoBtn(ActionEvent event) {

    }

    @FXML
    public void onRemovePhotosBtn(ActionEvent event) {
        int index = photoListview.getSelectionModel().getSelectedIndex();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Photo Delete Operation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to remove this photo?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			album.deletePhotoToAlbum(index);
			update();
			   
            int lastuserindex = album.getAllPhotos().size();
            if (album.getAllPhotos().size() == 1) {
                photoListview.getSelectionModel().select(0);
            } else if (index == lastuserindex) {
                photoListview.getSelectionModel().select(lastuserindex-1);
            } else { 
                photoListview.getSelectionModel().select(index);
            }
			
			// Album.save(album);
		} else {
			return;
		}
		return;
    }

    public void update() {
		albumPhotos.clear();
		for (int i = 0; i < album.getAllPhotos().size(); i++) {
			albumPhotos.add(album.getAllPhotos().get(i));
		}

		observableList = FXCollections.observableArrayList(albumPhotos);
        System.out.println(observableList);
		photoListview.setItems(observableList);
		photoListview.refresh();

        photoListview.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){
			@Override
			public ListCell<Photo> call(ListView<Photo> p){
				return new Results();
			}
			
		});
		
	    if(!observableList.isEmpty()) {
	    		photoListview.getSelectionModel().select(0); //select first photo of album
	    }
        
        // captionLabel.setText(photoListview.getSelectionModel().getSelectedItem().getPhotoCaption());
	}

    private class Results extends ListCell<Photo>{
		AnchorPane anchor = new AnchorPane();
		StackPane stackpane = new StackPane();
		
		ImageView imageView = new ImageView();
		
		public Results() {
			super();
			
			imageView.setFitWidth(110.0);
			imageView.setFitHeight(110.0);
			imageView.setPreserveRatio(true);

			StackPane.setAlignment(imageView, Pos.TOP_LEFT);
			stackpane.getChildren().add(imageView);			
			stackpane.setPrefHeight(110.0);
			stackpane.setPrefWidth(90.0);
			
			AnchorPane.setLeftAnchor(stackpane, 0.0);
			anchor.getChildren().addAll(stackpane);
			anchor.setPrefHeight(110.0);
			setGraphic(anchor);	
			
		}
		
		@Override
		public void updateItem(Photo photo, boolean empty) {
			super.updateItem(photo, empty);
			
			setText(null);
			if(photo == null)
			{				
				
			}
			
			else{
				Image img = new Image(photo.photo.toURI().toString());
				imageView.setImage(img);
			}
			
		}
	}

}

