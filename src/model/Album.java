package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Deep Parekh and Vraj Patel
 */
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";

    public String albumName;
    public ArrayList<Photo> photoList;
    public int numPhotos;
    public Photo currPhoto;

    // constructor of Album class
    public Album(String name) {
        this.albumName = name;
        this.photoList = new ArrayList<Photo>();
    }

    /**
     * @return String album name
     */
    public String getAlbumName() {
        return this.albumName;
    }

    /**
     * @param newName
     */
    public void setAlbumName(String newName) {
        // check for newName being null
        this.albumName = newName;
    }

    /**
     * @param p
     */
    // add photo to the photo list
    public void addPhotoToAlbum(Photo p) {
        photoList.add(p);

        // increase the photo count as well
        numPhotos++;
    }

    /**
     * @param index
     */
    // given the index, remove photo from the list
    public void deletePhotoToAlbum(int index) {
        photoList.remove(index);

        // decrease the photos count
        numPhotos--;
    }

    /**
     * @return ArrayList<Photo>
     */
    public ArrayList<Photo> getAllPhotos() {
        return photoList;
    }

    /**
     * @return Photo
     */
    public Photo getCurrentPhoto() {
        return currPhoto;
    }

    /**
     * @param p
     */
    public void setCurrentPhoto(Photo p) {
        this.currPhoto = p;
    }

    /**
     * @param p
     * @return int
     */
    public int getIndexByPhoto(Photo p) {
        return photoList.indexOf(p);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return getAlbumName();
    }

    /**
     * @return String
     */
    public String getFirstDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, M-d-y 'at' h:m:s a");
        Date date = null;
        String dateStr = "No Date";
        if (!photoList.isEmpty()) {
            date = this.getAllPhotos().get(0).date;
            for (Photo photo : photoList) {
                if (photo.date.before(date)) {
                    date = photo.date;
                }
            }
            dateStr = dateFormatter.format(date);
        }

        return dateStr;
    }

    /**
     * @return String
     */
    public String getLastDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, M-d-y 'at' h:m:s a");
        Date date = null;
        String dateStr = "No Date";
        if (!photoList.isEmpty()) {
            date = this.getAllPhotos().get(this.getAllPhotos().size() - 1).date;
            for (Photo photo : photoList) {
                if (photo.date.after(date)) {
                    date = photo.date;
                }
            }
            dateStr = dateFormatter.format(date);
        }

        return dateStr;
    }

    /**
     * @param u
     * @throws IOException
     */
    // save current state of the app to the dat file
    public static void saveAlbum(Album u) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(u);
        oos.close();
    }

    /**
     * @return GlobalUser
     * @throws IOException
     * @throws ClassNotFoundException
     */
    // deserializing objects from storage
    public static GlobalUser loadAlbum() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
        GlobalUser userList = (GlobalUser) ois.readObject();
        ois.close();
        return userList;
    }

}
