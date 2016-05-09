/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;

import edu.ncsu.csc216.flight.plane.Flight;

/** First class reservation is a child of the flight reservation class. 
 * This class represents a FirstClassReservation.
 * @author Andrew Northrup
 *
 */
public class EconomyReservation extends FlightReservation {
	/**Instance variable for the flight the reservation is for*/
	private Flight f;
	/**Instance variable for the seat preference of the reservation*/
	private boolean seatpreferredbypassenger;
	/**Instance variable for the name on the reservation*/
	private String passengername;
	
	//since it extends (is a child of), then this class has access to all public members of FlightReservation
	/**
	 * Constructor for an economy reservation
	 * @param passengernameonreservation is the name of the passenger
	 * @param flight is the flight this reservation is for
	 * @param seatpref is the preference in seat (true for window, false for aisle)
	 */
	public EconomyReservation(String passengernameonreservation, Flight flight, boolean seatpref) {
		//call to parent constructor since null constructor was removed
		super(passengernameonreservation, flight, seatpref);
		this.f = flight;
		this.seatpreferredbypassenger = seatpref;
		this.passengername = passengernameonreservation.trim();
		//findSeat();
	}
	/**
	 * Assign a seat to mySeat according to which child class this is.
	 */
	@Override
	public void findSeat() {
		String seatfound = mySeat;
		//Determines if coach is at capacity for coach reservations
		if(!f.coachAtCap() && seatfound == null){
			seatfound = f.reserveEconomySeat(seatpreferredbypassenger);
		}
		mySeat = seatfound;
	}
	/**
	 * The string printed on the reservation area
	 *  For the economy class version, this method first checks to see if the Coach section is at capacity, 
	 *  returning immediately if so. If no appropriate seat is available, the mySeat data member remains null
	 *	@return string for what shows up on reservation screen
	 */
	@Override
	public String stringForPrint() {
		String paddedSeat = mySeat;
		if(mySeat != null){
				int seatDelta = 4 - mySeat.length();
				for (int  i = 0; i < seatDelta; i++){
					paddedSeat = " " + paddedSeat;
				}
				String sr = "Coach        " + paddedSeat + "  " + passengername;
				return sr;
		}
		paddedSeat = "none";
		String s = "Coach        " + paddedSeat + "  " + passengername;
		return s;
	}
}
