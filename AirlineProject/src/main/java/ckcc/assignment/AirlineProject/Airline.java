package ckcc.assignment.AirlineProject;

import javax.persistence.*;

@Entity
@Table(name = "tbairline")
public class Airline {	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airlineCode")
	private String code;
	@Column(name = "airlineName")
	private String name;
	@Embedded
	private Aircraft aircraft = new Aircraft();
	
	public Airline() {
		
	}
	public Airline(String code, String name, Aircraft aircraft) {
		this.code = code;
		this.name = name;
		this.aircraft = aircraft;
	}
	public Airline(String name, String code) {
		this.name = name;
		this.code = code;
		this.aircraft = new Aircraft();
	}	
	public void add(Aircraft aircraft) {
		this.aircraft = aircraft;
	}	

	public Aircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}	
	public String getCode() {
		return this.code;
	}	
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
