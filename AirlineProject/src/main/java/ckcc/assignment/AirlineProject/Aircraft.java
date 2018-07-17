package ckcc.assignment.AirlineProject;

import javax.persistence.*;

@Embeddable
public class Aircraft {
	@Column(name = "aircraftModel")
	private String model;
	@Column(name = "firstClass")
	private int firstClassCapacity;
	@Column(name = "businessClass")
	private int businessClassCapacity;
	@Column(name = "economyClass")
	private int economyClassCapacity;
	
	public Aircraft() {
		
	}	
	public Aircraft(String model, int firstClassCapacity, int businessClassCapacity, int economyClassCapacity) {
		this.model = model;
		this.firstClassCapacity = firstClassCapacity;
		this.businessClassCapacity = businessClassCapacity;
		this.economyClassCapacity = economyClassCapacity;
	}
	public String toString(){
		String str = "";
		str += "Model : " + this.model + "\n";
 		str += "First Class Capacity : " + this.firstClassCapacity + "\n";
 		str += "Business Class Capacity : " + this.businessClassCapacity + "\n";
 		str += "Economy Class Capacity : " + this.economyClassCapacity + "\n";
		return str;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getFirstClassCapacity() {
		return firstClassCapacity;
	}
	public void setFirstClassCapacity(int firstClassCapacity) {
		this.firstClassCapacity = firstClassCapacity;
	}
	public int getBusinessClassCapacity() {
		return businessClassCapacity;
	}
	public void setBusinessClassCapacity(int businessClassCapacity) {
		this.businessClassCapacity = businessClassCapacity;
	}
	public int getEconomyClassCapacity() {
		return economyClassCapacity;
	}
	public void setEconomyClassCapacity(int economyClassCapacity) {
		this.economyClassCapacity = economyClassCapacity;
	}
	
}
