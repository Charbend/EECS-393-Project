package classes;

public class List {

	//instance data
	private List<Item> inventory;
	private String title;
	private List<User> users;
	
	
	//constructors
	public List(String name, User creator) {
		title = name;
		inventory = new ArrayList<Item>();
		users = new ArrayList<User>();
		users.add(creator)
	}
	
	
	//methods
	public boolean addItem(Item item) {
		return inventory.add(item);
	}

	public boolean removeItem(Item item) {
		return inventory.remove(item);
	}

	public boolean addQuantity(int qty) {

	}

	public boolean removeQuantity(int qty) {

	}

	public boolean share(User user) {
		return users.add(user);
	}

	public boolean delete() {
		
	}
	
	public User getUser() {
		return users.get(0);
	}


}

//@author Sharan Mehta
//EECS 393 Project
//Name : HomeInventory

/*
 * This is the list class
 * it creates, edits lists
 */
public class List {
  private String listName;
  private Arraylist itemList = new Arraylist;           //I want the name of the arraylist to be the name input by the user but unsure about how syntax for that looks
  private Arraylist userList = new Arraylist;
  
  public List (String listName, User user) {
    this.listName = listName;
    user.setType(1);
    this.userList.add(user);
    this.itemList = null;
  }
  
  public boolean addItem(Item item) {
    boolean result = false;
    if (!this.getItems().contains (item)){
        this.getItems().add(item);
        result = true;
    }
    return result;
  }
  
   public boolean removeItem(Item item) {
     boolean result = false;
     for (i=0;i<this.getItems().length();i++) {
       if(item == this.getItems()[i]) {
         this.getItems.remove(i);
         result = true;
       }
     }
     return result;
   }
   
   public Arraylist getUsers() {
     return this.userList;
   }
   
   public Arraylist getItems() {
     return this.itemList;
   }
   
   public String getListName() {
     return this.listName;
   }
   
   public boolean addUser(User user) {
     boolean result = false;
     if(!this.getUsers.contains(user)) {
       this.getUsers().add(user);
       result = true;
     }
     return result;
   }
   
   public boolean shareList(User user) {
     user.setType(2);
     boolean result = addUser(user);
     return result;
   }
   
}
	  
    
