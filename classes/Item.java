package classes;

public class Item {

	//instance data
	private String title;
	private String ID;
	private int quantity;
	private String category;
	private Retailer store;
	private String location;
	private Double cost;
	
	
	
	//constructors
	public Item (String name, int qty, double price, String category, Retailer retailer) {
		title = name;
		quantity = qty;
		cost = price;
		this.category = category;
		store = retailer;		
	}

	public Item (String name, int qty, double price, String category, String location, Retailer retailer) {
		title = name;
		quantity = qty;
		cost = price;
		this.category = category;
		this.location = location;
		store = retailer;
	}
	
	public Item (String name, int qty, double price, String category, String location) {
		title = name;
		quantity = qty;
		cost = price;
		this.category = category;
		this.location = location;
	}

	public Item (String name, int qty, double price, String category) {
		title = name;
		quantity = qty;
		cost = price;
		this.category = category;
	}


	//methods
	public String getName() {
		return title;
	}


	public double getPrice() {
		return cost;
	}

	public String getCategory() {
		return category;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getLocation() {
		return location;
	}

	public void addLocation(String itemID, String location) {
		if (this.location == null) {
			this.location = location;
		}
	}

	public void changeLocation(String itemID, String location) {
		
	}

	public void removeLocation(String itemID) {
		location = null;
	}

	public Item getItem() {

	}

	public Retailer getRetailer() {
		return store;
	}

	public void setRetailer(String code) {

	}



}
