/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * First class reservation is a child of the flight reservation class. 
 * This class represents a FirstClassReservation.
 * @author Andrew Northrup
 *
 */
public class FirstClassReservation extends FlightReservation {
	/**Instance variable for the flight the reservation is for*/
	private Flight f;
	/**Instance variable for the seat preference of the reservation*/
	private boolean seatpreferredbypassenger;
	/**Instance variable for the name on the reservation*/
	private String passengername;
	//since it extends (is a child of), then this class has access to all public members of FlightReservation
	/**
	 * Constructor for an first class reservation
	 * @param passengernameonreser is the name of the passenger
	 * @param flight is the flight this reservation is for
	 * @param seatpref is the preference in seat (true for window, false for aisle)
	 */
	public FirstClassReservation(String passengernameonreser, Flight flight, boolean seatpref) {
		//call to constructor in super class
		super(passengernameonreser, flight, seatpref);
		this.f = flight;
		this.seatpreferredbypassenger = seatpref;
		this.passengername = passengernameonreser.trim();
		//findSeat();
	}
	/**
	 * Assign a seat to mySeat according to which child class this is.
	 */
	@Override
	public void findSeat() {
		//Find seat should be determining calling other reservations
		//Not done in the flight module
		String seatfound = mySeat;
		if(seatfound == null){
			seatfound = f.reserveFirstClassSeat(seatpreferredbypassenger);
		}
		if(seatfound == null){
			seatfound = f.reserveBusinessSeat(seatpreferredbypassenger);
		}
		if(seatfound == null){
			seatfound = f.reserveEconomySeat(seatpreferredbypassenger);
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
				int seatDelta = 4 - mySeat.length();
				for (int i = 0; i < seatDelta; i++){
					paddedSeat = " " + paddedSeat;
				}
				String sr = "First Class  " + paddedSeat + "  " + passengername;
				return sr;
		}
		paddedSeat = "none";
		String s = "First Class  " + paddedSeat + "  " + passengername;
		return s;
	}
}
