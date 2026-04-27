import java.io.*;

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
        File file = new File("users.txt");
        if (!file.exists()) {
            userList[userCount++] = new Admin("A001", "admin", "admin123", "E001");
            userList[userCount++] = new Sender("S001", "edric", "123456", "SD001");
            userList[userCount++] = new Courier("C001", "abu", "123456");
            saveUsers();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 4) {
                        String role = parts[0];
                        String uId = parts[1];
                        String uName = parts[2];
                        String pass = parts[3];
                        if (role.equals("Admin") && parts.length >= 5) {
                            userList[userCount++] = new Admin(uId, uName, pass, parts[4]);
                        } else if (role.equals("Sender") && parts.length >= 5) {
                            userList[userCount++] = new Sender(uId, uName, pass, parts[4]);
                        } else if (role.equals("Courier")) {
                            userList[userCount++] = new Courier(uId, uName, pass);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading users: " + e.getMessage());
            }
        }
    }

    public static void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
            for (int i = 0; i < userCount; i++) {
                User u = userList[i];
                if (u instanceof Admin) {
                    bw.write("Admin|" + u.getUserId() + "|" + u.getUsername() + "|" + u.getPassword() + "|" + ((Admin) u).getEmployeeId() + "\n");
                } else if (u instanceof Sender) {
                    bw.write("Sender|" + u.getUserId() + "|" + u.getUsername() + "|" + u.getPassword() + "|" + ((Sender) u).getSenderId() + "\n");
                } else if (u instanceof Courier) {
                    bw.write("Courier|" + u.getUserId() + "|" + u.getUsername() + "|" + u.getPassword() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
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