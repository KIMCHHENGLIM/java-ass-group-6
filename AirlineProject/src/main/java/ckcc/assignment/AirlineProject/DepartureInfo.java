package ckcc.assignment.AirlineProject;

import javax.persistence.*;

@Embeddable
public class DepartureInfo {
	@Column(name = "departureDayOfWeek")
	private char dayOfWeek; // U, M, T, W, R, F, S
	@Column(name = "departureTime")
	private int time; //- Military Time : 0000=Midnight  0100=1:00am  1200=Noon  2350=11:50pm 
	@Column(name = "departureAirportCode")
	private String airportCode; // - http://www.airportcodes.org/
	@Column(name = "departureAirportGate")
	private String airportGate;
	
	public DepartureInfo() {}
	public DepartureInfo(char dayOfWeek, int time, String airportCode, String airportGate) {
		this.dayOfWeek = dayOfWeek;
		this.time = time;
		this.airportCode = airportCode;
		this.airportGate = airportGate;
	}
	public char getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(char dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getAirportGate() {
		return airportGate;
	}
	public void setAirportGate(String airportGate) {
		this.airportGate = airportGate;
	}
	@Override
	public String toString() {
		return "DepartureInfo [dayOfWeek=" + dayOfWeek + ", time=" + time  + ", airportCode="
				+ airportCode + ", airportGate=" + airportGate + "]";
	}
	
	
}
