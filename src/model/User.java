package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * @author Deep Parekh and Vraj Patel
 */
public class User implements Serializable {
    public String username;
    public ArrayList<Album> userAlbums;
    public Album currentAlbum;

    private static final long serialVersionUID = 1L;
    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";

    public User(String name) {
        this.username = name;
        userAlbums = new ArrayList<Album>();
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        // System.out.println(getUsername());
        return getUsername();
    }

    // display the list of all albums created by this user
    public void printAllAlbums() {
        for (Album a : userAlbums) {
            System.out.println(a.getAlbumName());
        }
    }

    /**
     * @return ArrayList<Album>
     */
    public ArrayList<Album> getAllAlbums() {
        return userAlbums;
    }

    /**
     * @param albums
     */
    public void setAllAlbums(ArrayList<Album> albums) {
        this.userAlbums = albums;
    }

    /**
     * @param a
     */
    // add an album to user's album arraylist
    public void addNewAlbum(Album a) {
        userAlbums.add(a);
    }

    /**
     * @param index
     */
    // remove an album from user's album arraylist
    public void removeAlbum(int index) {
        userAlbums.remove(index);
    }

    /**
     * @param albumname
     * @return boolean
     */
    // check if an album is created by this user or not
    public boolean exists(String albumname) {
        for (Album album : userAlbums) {
            if (album.getAlbumName().equals(albumname)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param newName
     */
    public void setUsername(String newName) {
        this.username = newName;
    }

    /**
     * @param a
     * @return boolean
     */
    // add a method to check if an album exists
    // question - should I pass in the album or its name??
    public boolean albumExists(Album a) {
        // search through album arraylist to find the given album
        for (Album alb : userAlbums) {
            if (alb.getAlbumName().equals(a.getAlbumName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param allAlbums
     */
    // I dont think we will need this but still I will add it
    public void setAlbumsList(ArrayList<Album> allAlbums) {
        this.userAlbums = allAlbums;
    }

    /**
     * @return Album
     */
    public Album getCurrentalbum() {
        return currentAlbum;
    }

    /**
     * @param a
     */
    public void setCurrentAlbum(Album a) {
        this.currentAlbum = a;
    }

    /**
     * @param index
     * @return Album
     */
    public Album getAlbum(int index) {
        return userAlbums.get(index);
    }

    /**
     * @param u
     * @throws IOException
     */
    // save current state of the app to the txt file
    public static void saveUser(User u) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(u);
        oos.close();
    }

    /**
     * @return User
     * @throws IOException
     * @throws ClassNotFoundException
     */
    // deserializing objects from storage
    public static User loadUser() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
        User userList = (User) ois.readObject();
        ois.close();
        return userList;
    }

}
