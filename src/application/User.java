package application;

import java.io.Serializable;

public class User implements Serializable{
    
  public String username;

  public User(String newUsername){
    // username = newUserName;
    this.username = new String(newUsername);
  }

  public String getName() {
    return username;
  }

  public void changeUserName(String newUserName) {
  username = newUserName;
	}

}
