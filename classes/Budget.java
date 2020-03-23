package classes;

public class Budget {
	
	//instance data
	private String category;
	private Double amount;
	private Double spent;
	
	
	//constructor
	public Budget(String category) {
		this.category = category;
	}

	
	//methods
	public void setAmount(Double value) {
		amount = value;
	}

	public void increaseSpent(Double value) {
		spent += value;
	}

	public double getSpent() {
		return spent;
	}

	public double getAmount(Item item) {
		return amount;
	}




}
