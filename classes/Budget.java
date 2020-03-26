package classes;

public class Budget {
	
	//instance data
	private String category;
	private Double amount;
	private Double spent;
	
	
	//constructor
	public Budget(String category) {
		this.category = category;
		this.amount = 0.0;
		this.spent = 0.0;
	}

	
	//methods
	public void setAmount(Double value) {
		this.amount = value;
	}

	public void increaseSpent(Double value) {
		this.spent += value;
	}

	public double getSpent() {
		return this.spent;
	}

	public double getAmount() {
		return this.amount;
	}
	
	public String getCategory() {
		return this.category;
	}

}
