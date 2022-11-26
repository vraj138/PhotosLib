package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class GlobalUser implements Serializable {

    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";

    // keep a list of all users
    public ArrayList<User> allUsers;

    // keep track of current user
    public User currentUser;

    public boolean isUserLoggedIn;

    public GlobalUser() {
        allUsers = new ArrayList<User>();

        // add admin as first user to the arraylist
        allUsers.add(new User("admin"));
        allUsers.add(new User("stock"));

        this.currentUser = null;

        this.isUserLoggedIn = false;
    }

    public void addUser(User u) {
        allUsers.add(u);
    }

    public void deleteUser(int index) {
        allUsers.remove(index);
    }

    public void deleteUserByUsername(String username) {
        User u = new User(username);
        allUsers.remove(u);
    }

    public boolean checkUser(String username) {
        int userIndex = -1;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equals(username)) {
                userIndex = i;
                break;
            }
        }

        if (userIndex == -1) {
            // the user does nbot exist
            return false;
        }

        this.setCurrentUser(allUsers.get(userIndex));
        this.isUserLoggedIn = true;
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User u) {
        this.currentUser = u;
    }

    // save current state of the app to the dat file
    public static void saveGlobalUser(GlobalUser gu) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(gu);
        oos.close();
    }

    // deserializing objects from storage
    public static GlobalUser loadGlobalUser() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
        GlobalUser globalUserList = (GlobalUser) ois.readObject();
        ois.close();
        return globalUserList;
    }

}
