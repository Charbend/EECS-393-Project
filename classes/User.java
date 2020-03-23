package classes;

public class User {
	
	//instance data
	private String password;
	private String username;
	private Type userType;
	private String userID;
	
	//constructor
	public User (String name, Type type, String pwd, String id) {
		username = name;
		usertype = type;
		password = pwd;
		userID = id;
	}
	
	
	//methods

	public boolean confirmPassword(String pwd) {
		return password.equals(pwd);
	}

	public void editUser(User user, String name, Type type) {
		user.setUserName(name);
		user.setUserType(type);
	}

	public void setUserName(String name) {
		username = name;
	}
	
	public String getUserName() {
		return username;
	}

	public void setUserType(Type type) {
		userType = type;
	}

	public void setUserPassword(String pwd) {
		password = pwd;
	}

	public String getUserPassword() {
		return pwd;
	}

	public Type getUserType() {
		return userType;
	}


	public void setUserID(String id) {
		userID = id;
	}

	public String getUserID() {
		return userID;
	}

	public boolean makeList(String name) {
		
	}

	public boolean shareList(String name, User user) {

	}

	public boolean deleteList(String Name) {

	}

	public boolean leaveList(String Name) {

	}


}
