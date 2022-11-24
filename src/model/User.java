package model;

import java.util.*;
import java.io.File;

public class User {
    public String username;
    public ArrayList<Album> userAlbums;
    public Album currentAlbum;

    public User(String usernmae){
        this.username = username;
        this.userAlbums = new ArrayList<Album>();
    }
}
