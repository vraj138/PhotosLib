package application;

public class User {
    
    public String username;

    public User(String newUserName){
        username = newUserName;
    }

    public String getName() {
		return username;
	}

    public void changeUserName(String newUserName) {
		username = newUserName;
	}

}
