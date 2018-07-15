package ckcc.assignment.AirlineProject;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		FlightSchedule fSchedule = new FlightSchedule();
		
		System.out.println("Welcome to the flight scheduler!\n\n");
		System.out.println("Please remember to always use U, M, T, W, R, F, S, for entering the day of the week,");
		System.out.println("and to always use military time for entering the time.");
		System.out.println("Please make your choice");
		System.out.println("by entering the corresponding menu number:\n");

		String menuNumber = "";
		do {
			//Main menus of program
			System.out.println("------------- Program Menus -------------");
			System.out.println("| 1. Add Airline                      	|");
			System.out.println("| 2. Add Flight                       	|");
			System.out.println("| 3. Update Flight Status           	|");
			System.out.println("| 4. Show Flight Info                	|");
			System.out.println("| 5. Show Departures                  	|");
			System.out.println("| 6. Show Arrivals                     	|");
			System.out.println("| 7. Clear Schedule                  	|");
			System.out.println("-----------------------------------------");
			
			int mainSelectedMenu = sc.nextInt();
			sc.nextLine();
			switch(mainSelectedMenu) {
				//Add Airline
				case 1:
					String answerAddAirline = "";
					do {
						System.out.println("----------------- Add Airline --------------------\n");
						System.out.println("Airline name: ");
						String airlineName = sc.nextLine();
						System.out.println("Airline code: ");
						String airlineCode = sc.nextLine();
						System.out.println("Aircraft model: ");
						String aircraftModel = sc.nextLine();
						System.out.println("First Class Capacity: ");
						int fClsCap = sc.nextInt();
						sc.nextLine();
						System.out.println("Business Class Capacity: ");
						int bClsCap = sc.nextInt();
						sc.nextLine();
						System.out.println("Economy Class Capacity: ");
						int eClsCap = sc.nextInt();
						sc.nextLine();
						// Create Aircraft Object
						Aircraft aircraftObj = new Aircraft(aircraftModel, fClsCap, bClsCap, eClsCap);
						// Create Airline Object
						Airline airlineObj = new Airline(airlineName, airlineCode);
						airlineObj.add(aircraftObj);
						// Add to flight schedule object
						fSchedule.addAirline(airlineObj);
						
						System.out.println("---------------------------------------------------");
						System.out.println(airlineName + " was successfully added.");
						System.out.println("---------------------------------------------------");
						System.out.println("Do you want to add more airline? (Y/N)");
						answerAddAirline = sc.nextLine();
					}while(answerAddAirline.equalsIgnoreCase("Y"));
					break;
				//Add flight
				case 2:
					String answerAddFlight = "";
					do {
						System.out.println("----------------- Add Flight --------------------\n");
						String answerAL = "";
						String alCode = "";
						Airline selectedAirline;
						do {
							System.out.println("Airline code: ");
							alCode = sc.nextLine();
							//Check airline code before proceed
							selectedAirline = fSchedule.getAirlineByCode(alCode);
							if(selectedAirline.equals(null)) {
								System.out.println("System cannot found this airline code! Try again (Y/N) : ");
								answerAL = sc.nextLine();
							}
						}while(answerAL.equalsIgnoreCase("Y"));
						
						if(!selectedAirline.equals(null)) {
							System.out.println("Flight number: ");
							int flightNum = sc.nextInt();
							sc.nextLine();
							System.out.println("Type (D for domestic or I for international): ");
							char flightType = sc.nextLine().charAt(0);
							//Departure information
							System.out.println("Departure airport code: ");
							String dAirportCode = sc.nextLine();
							System.out.println("Departure gate: ");
							String dGate = sc.nextLine();
							System.out.println("Departure day: ");
							char dDay = sc.nextLine().charAt(0);
							System.out.println("Departure time: ");
							int dTime = sc.nextInt();
							sc.nextLine();
							//Crate departure object
							DepartureArrivalInfo departureObj = new DepartureArrivalInfo(dDay, dTime, 'D', dAirportCode, dGate);
							//Arrival information
							System.out.println("Arrival airport code: ");
							String aAirportCode = sc.nextLine();
							System.out.println("Arrival gate: ");
							String aGate = sc.nextLine();
							System.out.println("Arrival day: ");
							char aDay = sc.nextLine().charAt(0);
							System.out.println("Arrival time: ");
							int aTime = sc.nextInt();
							sc.nextLine();
							//Create arrival object
							DepartureArrivalInfo arrivalObj = new DepartureArrivalInfo(aDay, aTime, 'A', aAirportCode, aGate);
							//Create flight object
							Flight flightObj = new Flight(selectedAirline, flightNum, 'S', flightType, departureObj, arrivalObj);
							// Add to flight schedule object
							fSchedule.addFlight(selectedAirline.getCode() + flightNum, flightObj);
							System.out.println("---------------------------------------------------");
							System.out.println("Flight " + selectedAirline.getCode() + flightNum +  " was successfully scheduled.");
							System.out.println("-----------------------------------------------------");
						}
						System.out.println("Do you want to add more flight? (Y/N)");
						answerAddFlight = sc.nextLine();
					}while(answerAddFlight.equalsIgnoreCase("Y"));
					break;
				//Update flight
				case 3:
					System.out.println("----------------- Update flight status --------------------\n");
					System.out.println("Enter Airline Code: ");
					String updatedAirlineCode = sc.nextLine();
					System.out.println("Enter Flight Number: ");
					int updatedFlightNum = sc.nextInt();
					sc.nextLine();
					// Start update flight
					String updatedFlightCode = updatedAirlineCode + updatedFlightNum;
					Flight updatedFlight = fSchedule.getFlight(updatedFlightCode);
					if(!updatedFlight.equals(null)) {
						System.out.println("Enter flight status (S,D,A,C): ");
						char updatedStatus = sc.nextLine().charAt(0);
						System.out.println("-----------------------------------------------------------");
						fSchedule.updateFlightStatus(updatedFlight, updatedStatus);
						System.out.println("You have successfully updated status of flight " + updatedFlightCode + "\n");
					}else {
						System.out.println("Flight " + updatedFlightCode + " is not found!");
					}
					System.out.println("-----------------------------------------------------------");
					break;
				//Show flight info
				case 4:
					System.out.println("----------------- Search flight info --------------------\n");
					System.out.println("Enter Airline Code: ");
					String searchAirlineCode = sc.nextLine();
					System.out.println("Enter Flight Number: ");
					int searchFlightNum = sc.nextInt();
					sc.nextLine();
					System.out.println("-----------------------------------------------------------");
					// Start searching flight
					String flightCode = searchAirlineCode + searchFlightNum;
					Flight searchFlight = fSchedule.getFlight(flightCode);
					if(!searchFlight.equals(null)) {
						System.out.println(searchFlight.toString());
					}else {
						System.out.println("Flight " + flightCode + " is not found!");
					}
					System.out.println("-----------------------------------------------------------");
					break;
				//Show departures
				case 5:
					System.out.println("----------------- Show all departures --------------------\n");
					System.out.println("Enter Airport Code: ");
					String airportCode = sc.nextLine();
					System.out.println("Enter Day: ");
					char enterDay = sc.nextLine().charAt(0);
					System.out.println("");
					System.out.println("Flights departure from airport " + airportCode + " : \n");
					
					System.out.println("-----------------------------------------------------------");
					System.out.println("Flight	Status	Time	Destination	Gate	");
					System.out.println("-----------------------------------------------------------");
					
					for(Flight myF: fSchedule.getSortedDepartures()) {
						// Compare airport code
						if(myF.getDepartureInfo().getAirportCode().equals(airportCode)) {
							if((myF.getStatus() == 'S' || myF.getStatus() == 'C' || myF.getStatus() == 'D') 
									&& myF.getDepartureInfo().getDayOfWeek() == enterDay) {
								System.out.println(myF.getAirline().getCode() + myF.getFlightNumber() + "		" +
									myF.getStatus() + "	" + myF.getDepartureInfo().getTime() + "	" +
									myF.getDepartureInfo().getAirportCode() + "	" + myF.getDepartureInfo().getAirportGate() + "\n");
							}
						}
					}
					
					System.out.println("-----------------------------------------------------------");
					break;
				//Show arrivals
				case 6:
					System.out.println("----------------- Show all arrivals --------------------\n");
					System.out.println("Enter Airport Code: ");
					String apCode = sc.nextLine();
					System.out.println("Enter Day: ");
					char enDay = sc.nextLine().charAt(0);
					System.out.println("");
					System.out.println("Flights arrival in airport " + apCode + " : \n");
					
					System.out.println("-----------------------------------------------------------");
					System.out.println("Flight	Status	Time	Destination	Gate	");
					System.out.println("-----------------------------------------------------------");
					
					for(Flight myF: fSchedule.getSortedArrivals()) {
						// Compare airport code
						if(myF.getArrivalInfo().getAirportCode().equals(apCode)) {
							if((myF.getStatus() == 'S' || myF.getStatus() == 'C' || myF.getStatus() == 'A') 
									&& myF.getArrivalInfo().getDayOfWeek() == enDay) {
								System.out.println(myF.getAirline().getCode() + myF.getFlightNumber() + "		" +
									myF.getStatus() + "	" + myF.getArrivalInfo().getTime() + "	" +
									myF.getArrivalInfo().getAirportCode() + "	" + myF.getArrivalInfo().getAirportGate() + "\n");
							}
						}
					}
					
					System.out.println("-----------------------------------------------------------");
					break;
				//Clear schedule	
				case 7:
					System.out.println("---------------------------------------------------\n");
					System.out.println("Are you sure you want to clear the schedule and start over? (Y?N) : ");
					String clearAnswer = sc.nextLine();
					if(clearAnswer.equalsIgnoreCase("Y")) {
						fSchedule.deleteAllRecords();
						System.out.println("");
						System.out.println("The flight schedule is cleared.");
						System.out.println("");
					}
					System.out.println("---------------------------------------------------");
					break;
			}
			
			System.out.println("Do you want to continue to Main Menu? (Y/N)");
			menuNumber = sc.next();
		}while(menuNumber.equalsIgnoreCase("Y"));
	}

}
