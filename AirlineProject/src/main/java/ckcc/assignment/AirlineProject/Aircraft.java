package ckcc.assignment.AirlineProject;

public class Aircraft {

	private String model;
	private int firstClassCapacity;
	private int businessClassCapacity;
	private int economyClassCapacity;
	
	public Aircraft(String model, int fC, int bC, int eC) {
		this.model = model;
		this.firstClassCapacity = fC;
		this.businessClassCapacity = bC;
		this.economyClassCapacity = eC;
	}
	
	public String toString() {
		String str = "";
		str += "Model : " + this.model + "\n";
 		str += "First Class Capacity : " + this.firstClassCapacity + "\n";
 		str += "Business Class Capacity : " + this.businessClassCapacity + "\n";
 		str += "Economy Class Capacity : " + this.economyClassCapacity + "\n";
		return str;
	}
	
}
