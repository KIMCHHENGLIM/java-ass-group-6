package ckcc.assignment.AirlineProject;

import java.util.ArrayList;
import java.util.HashMap;

public class FlightSchedule {
	
	private ArrayList<Airline> airlines;
	private HashMap<String, Flight> flightMap;

	public FlightSchedule() {
		airlines = new ArrayList<Airline>();
		flightMap = new HashMap<String, Flight>();
	}
	
	public void deleteAllRecords() {
		this.airlines.clear();
		this.flightMap.clear();
	}
	
	public void addAirline(Airline airline) {
		this.airlines.add(airline);
	}
	
	public Airline getAirlineByCode(String code) {
		for(Airline al : this.airlines) {
			if(al.getCode().equals(code)) {
				return al;
			}
		}
		return null;
	}
	
	public void addFlight(String flightCode, Flight flight) {
		this.flightMap.put(flightCode, flight);
	}
	
	public void updateFlightStatus(Flight flight, char status) {
		flight.setStatus(status);
	}
	
	public Flight getFlight(String flightCode) {
		return this.flightMap.get(flightCode);
	}
	
	public ArrayList<Flight> getSortedDepartures(){
		ArrayList<Flight> flights = new ArrayList<Flight>(this.flightMap.values());
		flights.sort(Flight.DepartureComparator);
		return flights;
	}
	
	public ArrayList<Flight> getSortedArrivals(){
		ArrayList<Flight> flights = new ArrayList<Flight>(this.flightMap.values());
		flights.sort(Flight.ArrivalComparator);
		return flights;
	}
	
}
