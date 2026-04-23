public abstract class User {
    private String userId;
    private String username;
    private String password; 

    private static User[] userList = new User[10];
    private static int userCount = 0;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public static void initializeUserData() {
        userList[userCount++] = new Admin("A001", "admin", "admin123", "E001") {
        };
        userList[userCount++] = new Sender("S001", "edric", "123456", "SD001") {
        };
        userList[userCount++] = new Courier("C001", "abu", "123456") {
        };
    }

    // Checks input against the stored array.
    public static User authenticate(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (userList[i].username.equals(username) && userList[i].password.equals(password)) {
                return userList[i];
            }
        }
        return null;
    }

	public boolean login(String inputUser, String inputPass) { 
		return this.username.equals(inputUser) && this.password.equals(inputPass); 
	}
    public void logout() { 
    	System.out.println("System: " + username + " has logged out successfully."); 
    }


	public static User[] getUserList() {
    	return userList;
	}

    public String getUserId() { 
    	return userId; 
    }
    public String getUsername() { 
    	return username; 
    }
    public String getPassword() { 
    	return password; 
    }

    @Override
    public String toString() {
        return "User ID: " + userId + " | Username: " + username;
    }
}