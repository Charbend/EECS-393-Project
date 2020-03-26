package classes;

public class Retailer {

	//instance data
	private String storeName;
	private String storeURL;
	
	//constructors
	public Retailer(String name, String url) {
		this.storeName = name;
		this.storeURL = url;
	}
	
	public Retailer(String name) {
		this.storeName = name;
		this.storeURL = null;
	}
	
	
	//methods
	public String getRetailerName() {
		return this.storeName;
	}

	public String getImage() {
	}

	public double getPrice() {
	}

	public void openRetailer(String name) {
	}


	public String getRetailerURL() {
		return this.storeURL;
	}

	public void setRetailerName(String name) {
		this.storeName = name;
	}

	public void setRetailerURL(String url) {
		this.storeURL = url;
	}

}
