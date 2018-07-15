package ckcc.assignment.AirlineProject;

import java.util.ArrayList;

public class Airline {
	
	private String name;
	private String code;
	private ArrayList<Aircraft> aircraft;
	
	public Airline(String name, String code) {
		this.name = name;
		this.code = code;
		this.aircraft = new ArrayList<Aircraft>();
	}
	
	public void add(Aircraft aircraft) {
		this.aircraft.add(aircraft);
	}
	
	public String getCode() {
		return this.code;
	}

	public ArrayList<Aircraft> getAircraft(){
		return this.aircraft;
	}
	
	public void remove(Aircraft aircraft) {
		this.aircraft.remove(aircraft);
	}
	
}
