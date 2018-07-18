package ckcc.assignment.AirlineProject;

import java.util.Comparator;

import javax.persistence.*;
@Entity
@Table(name = "tbflight")
public class Flight {
	@Id
	@Column(name = "flightCode")
	private String flightCode;
	@Column(name = "flightNumber")
	private int flightNumber;
	@Column(name = "flightStatus")
	private char status; // - S/C/D/A for Scheduled/Cancelled/Departed/Arrived
	@Column(name = "flightType")
	private char type;
	@Column(name = "airlineCode")
	private String airlineCode;
	@Embedded
	private DepartureInfo departureInfo;
	@Embedded
	private ArrivalInfo arrivalInfo;
	public Flight() {	
	}		
	public Flight(String flightCode, String airlineCode, int flightNumber, char status, char type,
			DepartureInfo departureInfo, ArrivalInfo arrivalInfo) {
		this.flightCode = flightCode;
		this.airlineCode = airlineCode;
		this.flightNumber = flightNumber;
		this.status = status;
		this.type = type;
		this.departureInfo = departureInfo;
		this.arrivalInfo = arrivalInfo;
	}	
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getAirlineCode() {
		return airlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public DepartureInfo getDepartureInfo() {
		return departureInfo;
	}
	public void setDepartureInfo(DepartureInfo departureInfo) {
		this.departureInfo = departureInfo;
	}
	public ArrivalInfo getArrivalInfo() {
		return arrivalInfo;
	}
	public void setArrivalInfo(ArrivalInfo arrivalInfo) {
		this.arrivalInfo = arrivalInfo;
	}
	//Comparator for sorting by departure time
	public static Comparator<Flight> DepartureComparator = new Comparator<Flight>() {
		public int compare(Flight flightA, Flight flightB) {
			int departureFlightA = flightA.getDepartureInfo().getTime();
			int departureFlightB = flightB.getDepartureInfo().getTime();
			return departureFlightA - departureFlightB;
		}
	};	
	//Comparator for sorting by departure time
	public static Comparator<Flight> ArrivalComparator = new Comparator<Flight>() {
		public int compare(Flight flightA, Flight flightB) {
			int arrivialFlightA = flightA.getArrivalInfo().getTime();
			int arrivalFlightB = flightB.getArrivalInfo().getTime();
			return arrivialFlightA - arrivalFlightB;
		}
	};
	
}
