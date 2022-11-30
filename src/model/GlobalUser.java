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


	private static final long serialVersionUID = 1L;
    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";

    // keep a list of all users
    public ArrayList<User> allUsers;

    // keep track of current user
    public User currentUser;

    public boolean isUserLoggedIn;

    public GlobalUser(){
        allUsers = new ArrayList<User>();

        // add admin as first user to the arraylist
        // allUsers.add(new User("admin"));
        allUsers.add(new User("stock"));
        this.currentUser = null;
        this.isUserLoggedIn = false;
    }

    public void addUser(String username) {
        allUsers.add(new User(username));
    }

    public void deleteUser(int index) {
        allUsers.remove(index);
    }

    public void deleteUserByUsername(String username) {
        User u = new User(username);
        allUsers.remove(u);
    }

    public boolean checkUser(String username)  {
        int userIndex = -1;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equals(username)) {
                userIndex = i;
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

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<String> getAllUsernames() {
        ArrayList<String> names = new ArrayList<String>();
        for (User u : allUsers) {
            names.add(u.getUsername());
        }

        return names;
    }

    public void setAllUsers(ArrayList<User> users) {
        this.allUsers = users;
    }

    public User getUser(String username) {
        for (User u : allUsers) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }

        return null;
    }

    public boolean userExists(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // save current state of the app to the txt file
    public static void saveGlobalUser(GlobalUser u) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(u);
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
