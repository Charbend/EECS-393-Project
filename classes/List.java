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
