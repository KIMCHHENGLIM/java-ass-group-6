package ckcc.assignment.AirlineProject;

public class DepartureArrivalInfo {
	
	private char dayOfWeek; // U, M, T, W, R, F, S
	private int time; //- Military Time : 0000=Midnight  0100=1:00am  1200=Noon  2350=11:50pm 
	private char type; //D: Departure A: Arrival
	private String airportCode; // - http://www.airportcodes.org/
	private String airportGate;
	
	public DepartureArrivalInfo(char dayOfWeek, int time, char type, String airportCode, String airportGate) {
		this.dayOfWeek = dayOfWeek;
		this.time = time;
		this.type = type;
		this.airportCode = airportCode;
		this.airportGate = airportGate;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String getAirportCode() {
		return this.airportCode;
	}
	
	public char getDayOfWeek() {
		return this.dayOfWeek;
	}
	
	public String getAirportGate() {
		return this.airportGate;
	}
	
	public String toString() {
		String str = "";
		if(this.type == 'D') {
			str += "Departure day : " + this.dayOfWeek + "\n";
			str += "Departure time : " + this.time + "\n";
			str += "Departure airport : " + this.airportCode + "\n";
			str += "Departure gate : " + this.airportGate + "\n";
					
		}else if(this.type == 'A') {
			str += "Arrival day : " + this.dayOfWeek + "\n";
			str += "Arrival time : " + this.time + "\n";
			str += "Arrival airport : " + this.airportCode + "\n";
			str += "Arrival gate : " + this.airportGate + "\n";
		}
		return str;
	}

}
