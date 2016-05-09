/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Business class reservation is a child of the flight reservation class. 
 * This class represents a BusinessClassReservation.
 * @author Andrew Northrup
 *
 */
public class BusinessClassReservation extends FlightReservation {
	/**Instance variable for the flight the reservation is for*/
	private Flight f;
	/**Instance variable for the seat preference of the reservation*/
	private boolean seatpreference;
	/**Instance variable for the name on the reservation*/
	private String passengername;
	//since it extends (is a child of), then this class has access to all public members of FlightReservation
	/**
	 * Constructor for an first class reservation
	 * @param nameofpassenger is the name of the passenger
	 * @param flight is the flight this reservation is for
	 * @param seatrequested is the preference in seat (true for window, false for aisle)
	 */
	public BusinessClassReservation(String nameofpassenger, Flight flight, boolean seatrequested) {
		//call to constructor in super class
		super(nameofpassenger, flight, seatrequested);
		this.f = flight;
		this.seatpreference = seatrequested;
		this.passengername = nameofpassenger.trim();
		//findSeat();
	}
	/**
	 * Assign a seat to mySeat according to which child class this is.
	 */
	@Override
	public void findSeat() {
		String seatfound = mySeat;
		if(seatfound == null){
			seatfound = f.reserveBusinessSeat(seatpreference);
		}
		if(seatfound == null){
			seatfound = f.reserveEconomySeat(seatpreference);
		}
		mySeat = seatfound;
	}
	/**
	 * The string printed on the reservation area
	 * @return string for what shows up on reservation screen 
	 */
	
	@Override
	public String stringForPrint() {
		String paddedSeat = mySeat;
		if(mySeat != null){
			//if(mySeat.length()<4){
				int seatDelta = 4 - mySeat.length();
				for (int i = 0; i < seatDelta; i++){
					paddedSeat = " " + paddedSeat;
				}
				String sr = "Business     " + paddedSeat + "  " + passengername;
				return sr;
			//}
		}
		paddedSeat = "none";
		String s = "Business     " + paddedSeat + "  " + passengername;
		return s;
	}
}
