package classes;
java.util.Scanner;
java.util.ArrayList<E>

//EECS 393 Project
//Name : HomeInventory

/*
 * This is the user class
 * it creates, edits users and lists belonging to those users
 */
public class User {
  private String name;
  private int type;
  private String pwd;
  private String id;
  
  public User (String name, int type, String pwd, String id) {
    this.name = name;
    this.type = type;
    this.pwd = pwd;
    this.id = id;
  }
  
  public boolean confirmPassword (String pwd) {
    boolean result = false;
    if (this.pwd = pwd) 
      result = true;
    return result
  }
  
  public void editUser (User user, String name, int type) {
    user.name = name;
    user.type = type;
  }
  
  public void setUserName (String name) {
    this.name = name;
  }
  
  public String getUserName () {
    return this.name;
  }
  
    public String setUserTpye (int type) {
      String result = "Type out of bounds";
      if (type == 1 || type == 2) {
        this.type = type;
        result = "Type succesfully set";
      }
      return result;
  }
  
  public int getUserType () {
    return this.type;
  }
  
  public String displayUserType (User user) {
    String result = "";
    if (user.type == 1)
      result = user.getUserName() + "is a primary user";
    else if (user.type == 2)
      result = user.getUserName() + "is a secondary user";
    else
      result = "error: user type out of bounds"
  }
  
    public void setUserPassword (String pwd) {
    this.pwd = pwd;
  }
  
  public String getUserPassword () {
    return this.pwd;
  }
  
    public void setUserID (String id) {
    this.id = id;
  }
  
  public String getUserID () {
    return this.id;
  }
 
  
  // Sharan: This bit needs work as I haven't visualized whether the user can make lists only inside rooms or anywhere is their database/space.
  // Team input needed
  /*
  public boolean createRoom (String roomName) {
    boolean result = false;
    
  
  public boolean makeList (String listName) {
    boolean result = false; 
    for (i=0;i < this.getUserRoom.length; i++) {
      if (listName != userRoom[i].getName())
        result = true;
    }
    if (result)
      List listName = new List(listName);
    return result;
  }
  */    
  public boolean shareList (String listName, User user) {
  	boolean result = false;
	if (user.getType == 1)
		return result;
	else {
		user.setType(2);
		listName.addUser(user);
		result = true;
	}
  }
  
  public boolean leaveList(String listName) {
	  if (listName.userList.length()==1)
		  return listName.deleteList();
	  boolean result = false;
	  int count = 0;
	  for (i=0;i<listName.userList.length();i++){
		  if (userList[i].gtType == 1)
			  count++;
	  }
	  if (count > 1) {
		  if(listName.userList.contains(this)){
			  listName.userList.remove(this);
		          result = true;
		  }
		  return result;
	  }
	  else {
		  Scanner sc = new Scanner(System.in);
		  System.out.println("who would you like to make owner of the list?");
		  User user = sc.next();
		  result = this.leaveList(listName, user);
	  }
	  return result;
  }
  
//Polymorph method when the user leaving is the last primary user
  public boolean leaveList(String listName, User user) {
	  boolean result = false;
	  if (listName.userList.contains(this)){
	          listName.userList.remove(this);
		  result = true;
	  }
	  return result;
  }	
}
  
