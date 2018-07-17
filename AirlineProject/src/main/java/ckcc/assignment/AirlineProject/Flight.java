package ckcc.assignment.AirlineProject;

import java.util.Comparator;

public class Flight {
	
	private Airline airline;
	private int flightNumber;
	private char status; // - S/C/D/A for Scheduled/Cancelled/Departed/Arrived
	private char type;
	private DepartureArrivalInfo departureInfo;
	private DepartureArrivalInfo arrivalInfo;
	
	public Flight(Airline airline, int flightNumber, char status, char type, DepartureArrivalInfo departureInfo, DepartureArrivalInfo arrivalInfo) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.status = status;
		this.type = type;
		this.departureInfo = departureInfo;
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
	
	public DepartureArrivalInfo getDepartureInfo() {
		return this.departureInfo;
	}
	
	public DepartureArrivalInfo getArrivalInfo() {
		return this.arrivalInfo;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
	
	public char getStatus() {
		return this.status;
	}
	
	public Airline getAirline() {
		return this.airline;
	}
	
	public int getFlightNumber() {
		return this.flightNumber;
	}
	
	public String toString() {
		String str = "";
		str += "Flight : " + this.airline.getCode() + this.flightNumber + "\n";
		str += "Type : " + this.type + "\n";
		str += "Status : " + this.status + "\n";
		str += this.airline.getAircraft().toString() + "\n";
		str += this.getDepartureInfo().toString() + "\n";
		str += this.getArrivalInfo().toString() + "\n";
		return str;
	}

}
