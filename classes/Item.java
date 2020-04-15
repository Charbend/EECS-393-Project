package classes;

public class Item {

	//instance data
	private String itemName;
	private int quantity;
	private String category;
	private Retailer store;
	private String location;
	private Double cost;
	
	
	
	//constructors
	public Item (String itemName, int qty, double price, String category, Retailer retailer) {
		this.itemName = itemName;
		this.quantity = qty;
		this.cost = price;
		this.category = category;
		this.store = retailer;		
	}

	public Item (String itemName, int qty, double price, String category, String location, Retailer retailer) {
		this.itemName = itemName;
		this.quantity = qty;
		this.cost = price;
		this.category = category;
		this.location = location;
		this.store = retailer;
	}
	
	public Item (String itemName, int qty, double price, String category, String location) {
                this.itemName = itemName;
		this.quantity = qty;
		this.cost = price;
		this.category = category;
		this.location = location;
	}

	public Item (String itemName, int qty, double price, String category) {
		this.itemName = itemName;
		this.quantity = qty;
		this.cost = price;
		this.category = category;
	}


	//methods
	public String getName() {
		return this.itemName;
	}


	public double getPrice() {
		return this.cost;
	}

	public String getCategory() {
		return this.category;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Retailer getRetailer() {
		return this.store;
	}

	public void setRetailer(String store) {
		this.store = store;

	}



}
