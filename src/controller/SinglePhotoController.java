package controller;

import application.Photos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Album;
import model.GlobalUser;
import model.Photo;
import model.Tag;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class SinglePhotoController {

    @FXML
    public Button addTagsBtn, nextPhotoBtn, previousPhotoBtn, removeTagsBtn, setCaptionBtn;

    @FXML
    public Label photoCaptionLabel, dateLabel, photoTagsLabel;

    @FXML
    public ImageView photoImage;

    @FXML
    public ListView<String> tagslistview;

    @FXML
    public TextField tfTagName, tfTagValue;
    
    public static Photo photo;

    public static Album userAlbum;

    public static GlobalUser gu = Photos.gu;
	
	public static ArrayList<Tag> taglist = new ArrayList<>();
	
	public static ArrayList<String> tagdisplay = new ArrayList<>();
	
	public ObservableList<String> observableList;
	

    public void start(Stage stage){
        update();
		if(!taglist.isEmpty()) {
    		tagslistview.getSelectionModel().select(0); //select first user
		}
    }

    @FXML
    public void onAddTags(ActionEvent event) throws IOException {
        String tagName = tfTagName.getText().trim();
		String tagValue = tfTagValue.getText().trim();
		if (tagName.isEmpty() || tagValue.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Tag Add Error");
			alert.setContentText("Tag Infomration Incomplete.");
			alert.showAndWait();
			return;
		} else {
			Tag tag = new Tag(tagName, tagValue);
			// gu.getCurrentUser().getCurrentAlbum().getCurrentPhoto().addTag(tag.name, tag.value);
            photo.addTag(tag.tagName, tag.value);
			// System.out.println(adminuser.getCurrent().getCurrentAlbum().getCurrentPhoto().getTagList());
            // System.out.println(photo.getTagsList());

		// LEfT OFF HERE
			update();
			GlobalUser.saveGlobalUser(gu);
		}
    }

    @FXML
    public void onNextPhotoBtnClicked(ActionEvent event) {
        int photoIndex = userAlbum.getIndexByPhoto(photo);
        
        if(photoIndex != userAlbum.getAllPhotos().size() - 1){
            Photo newPhoto = userAlbum.getAllPhotos().get(photoIndex + 1);
            File file = newPhoto.getPhoto();
            Image image = new Image(file.toURI().toString());
			photoImage.setImage(image);

            dateLabel.setText("Date: " + newPhoto.date);

            photoCaptionLabel.setText(newPhoto.getPhotoCaption());

            tagdisplay.clear();
            ArrayList<Tag> tags = newPhoto.getTagsList();
            
            for(Tag tag : tags) {
                tagdisplay.add(tag.tagName + " = " + tag.value);
            }
            observableList = FXCollections.observableArrayList(tagdisplay);
		    tagslistview.setItems(observableList);

            

            photo = newPhoto;
            tagslistview.refresh();
        }

    }

    @FXML
    public void onPreviousPhotoBtnClicked(ActionEvent event) {
        int photoIndex = userAlbum.getIndexByPhoto(photo);
        // System.out.println("Curr photo inde " + photoIndex);
        if(photoIndex != 0){
            Photo newPhoto = userAlbum.getAllPhotos().get(photoIndex - 1);
            File file = newPhoto.getPhoto();
            Image image = new Image(file.toURI().toString());
			photoImage.setImage(image);

            dateLabel.setText("Date: " + newPhoto.date);

            photoCaptionLabel.setText(newPhoto.getPhotoCaption());

            tagdisplay.clear();
            ArrayList<Tag> tags = newPhoto.getTagsList();
            
            for(Tag tag : tags) {
                tagdisplay.add(tag.tagName + " = " + tag.value);
            }
            observableList = FXCollections.observableArrayList(tagdisplay);
		    tagslistview.setItems(observableList);

            
            photo = newPhoto;
            tagslistview.refresh();
        }
    }

    @FXML
    public void onRemoveTags(ActionEvent event) throws IOException {
        int index = tagslistview.getSelectionModel().getSelectedIndex();
		
		ArrayList<Tag> taglist = photo.getTagsList();
		photo.removeTag(taglist.get(index).tagName, taglist.get(index).value);
		
		update();
		GlobalUser.saveGlobalUser(gu);
    }

    @FXML
    public void onSetCaptionBtn(ActionEvent event) throws IOException {
        TextInputDialog userDialog = new TextInputDialog();
        userDialog.setTitle("Set Caption");
        userDialog.setHeaderText(null);
        userDialog.setContentText("Enter Caption: ");
        userDialog.setGraphic(null);

        Optional<String> result = userDialog.showAndWait();
        TextField newCaption = userDialog.getEditor();
        String captionName = newCaption.getText().trim();
        // Album album = new Album(albumname);

        if (result.isPresent()) {
            photo.setPhotoCaption(captionName);
            GlobalUser.saveGlobalUser(gu);
        }
        update();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhotosDisplay.fxml"));
        PhotosDisplayController pDisplayController = loader.getController();
        pDisplayController.update();
        // photoCaptionLabel.setText(photo.getPhotoCaption());
    }

    public void update(){
        File file;
		if (photo != null) {
			file = photo.getPhoto();
			Image image = new Image(file.toURI().toString());
			photoImage.setImage(image);
		}
    
		dateLabel.setText("Date: " + photo.date);
		tagdisplay.clear();
		ArrayList<Tag> tags = photo.getTagsList();
		
		for(Tag tag : tags) {
			tagdisplay.add(tag.tagName + " = " + tag.value);
		}

        photoCaptionLabel.setText(photo.getPhotoCaption());
		observableList = FXCollections.observableArrayList(tagdisplay);
		tagslistview.setItems(observableList);
		// System.out.println(taglist.toString());
		tfTagName.clear();
		tfTagValue.clear();
    }

}
