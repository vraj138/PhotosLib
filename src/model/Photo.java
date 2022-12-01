package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Photo implements Serializable {
    public String name;
    public File photo;
    public String caption;
    public Boolean stockPhoto;
    public ArrayList<Tag> tags;
    public Calendar cal;
	public Date date;

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
        cal = new GregorianCalendar();
		cal.set(Calendar.MILLISECOND, 0);
		this.date = cal.getTime();

        // need to get current date and store it in date variable
    }

    public String getPhotoName(){
        return this.name;
    }

    public String getPhotoCaption(){
        return this.caption;
    }

    public void setPhotoCaption(String newCaption){
        this.caption = newCaption;
    }

    public File getPhoto(){
        return this.photo;
    }

    public ArrayList<Tag> getTagsList(){
        return this.tags;
    }

    public void addTag(String name, String value) {
		tags.add(new Tag(name,value));
	}

    public void removeTag(String name, String value) {
		for(int i = 0; i < tags.size(); i++) {
			Tag cur = tags.get(i);
			if(cur.tagName.toLowerCase().equals(name.toLowerCase()) && cur.value.toLowerCase().equals(value.toLowerCase())) {
				tags.remove(i);
			}
		}
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
