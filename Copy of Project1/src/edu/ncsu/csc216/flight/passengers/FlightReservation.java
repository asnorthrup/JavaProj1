/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;



import edu.ncsu.csc216.flight.plane.Flight;

/**This is the abstract class for flight reservations.
 * @author Andrew Northrup
 *
 */
abstract public class FlightReservation {
	/**Instance variable for passenger's name*/
	private String name;
	/**Instance variable for seat preference*/
	private boolean prefersWindow;
/*	*//**Instance variable for name of seat*//*
	private String seatLabel;*/
	/**Instance variable for airplane of the reservation*/
	private Flight myAirplane;
	/**Instance variable for location of seat*/
	protected String mySeat;

	/**
	 * Constructor that requires passenger name, plane and seat preference
	 * 
	 * @param passengerName for name of passenger
	 * @param flight for flight passenger is being added to
	 * @param seatPref seat preference of passenger
	 */
	public FlightReservation(String passengerName, Flight flight, boolean seatPref) {
		if(passengerName.length() == 0 || passengerName.isEmpty()){ //removed check if passengerName==null
			throw new IllegalArgumentException();
		}
		this.myAirplane = flight;
		this.prefersWindow = seatPref;
		this.name = passengerName.trim();
	}
	/**Gets passenger's name
	 * @return the passenger_name
	 */
	public String getName() {
		return name;
	}
	/**Gets seat label
	 * @return the seat assigned to this reservation
	 */
	public String getSeat() {
		return mySeat;
	}
	/**Gets the airplane
	 * @return the airplane
	 */
	public Flight getVehicle() {
		return myAirplane;
	}
	/**
	 * True if the seating preference; doesn't set it though.
	 * @return true if seating preference is window, false if aisle
	 */
	public boolean wantsWindowSeat(){	
		return prefersWindow;
	}
	/**
	 * Abstract method that assigns a particular seat to this reservation, if possible
	 */
	abstract public void findSeat();
	/**
	 * Part of string printed in the reservation area in the GUI
	 * minus its classification. The string has leading blanks
	 * to pad the seat to a field of width 4.
	 * @return string for reservation displayed on the screen
	 * 
	 */
	public String stringForPrint(){
		String paddedSeat = mySeat;
		if(mySeat.length() < 4){
			int seatDelta = 4 - mySeat.length();
			for (int i = 0; i < seatDelta; i++){
				paddedSeat = " " + paddedSeat;
			}
			return paddedSeat;
		}
		String s = mySeat + name;
		return s;
	}
	/**
	 * Makes a case insensitive comparison of names
	 * @param flightres for name on reservation to compare against
	 * @return if flight reservation name is same, returns 1, 0 if not
	 */
	public int compareTo(FlightReservation flightres){
		if(flightres.getName().equalsIgnoreCase(name)){
		return 1;
		}
		return 0;
	}
	/**
	 * Makes the seat for this reservation unoccupied
	 */
	public void cancelReservation(){
		myAirplane.releaseSeat(mySeat);
	}
}
