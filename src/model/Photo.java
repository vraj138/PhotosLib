package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Photo implements Serializable {
    public String name;
    public File photo;
    public String caption;
    public Date date;
    public Boolean stockPhoto;
    public ArrayList<Tag> tags;

	private static final long serialVersionUID = 1L;
    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";

    public Photo(File photo, String photoName) {
        this.name = photoName;

        if (photo != null) {
            this.photo = new File(name);
        } else {
            this.photo = photo;
        }

        this.tags = new ArrayList<Tag>();

        // need to get current date and store it in date variable
    }

    public String getPhotoName(){
        return this.name;
    }

    public String getPhotoCaption(){
        return this.caption;
    }

    // save current state of the app to the .dat file
    public static void savePhoto(Photo p) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(p);
        oos.close();
    }

    // deserializing objects from storage
    public static User loadPhotos() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
        User userList = (User) ois.readObject();
        ois.close();
        return userList;
    }
}
