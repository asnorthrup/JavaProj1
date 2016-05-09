/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import edu.ncsu.csc216.flight.passengers.BusinessClassReservation;
import edu.ncsu.csc216.flight.passengers.EconomyReservation;
import edu.ncsu.csc216.flight.passengers.FirstClassReservation;
import edu.ncsu.csc216.flight.passengers.PassengerList;

/**Class creates a gate agent that interacts with the rest of the methods. Gate
 * agent is what the GUI uses to interact with the backend functions of assigning
 * seats.
 * @author Andrew Northrup
 *
 */
public class GateAgent {
	/** Instance of seating manager*/
	private SeatingManager plane;
	/** Instance of PassengerList for the flight manifest */
	private PassengerList manifest;
	/** Instance variable for adding new reservations to the list*/ 
	//private int index;
	
	/**
	 * Constructor for GateAgent
	 * @param filename the name of the file representing the empty seating chart
	 */
	public GateAgent(String filename) {
		//need to create the plane with this, use seatingmanager
		plane = new Flight(filename);
		manifest = new PassengerList();
	}
	/**
	 * Removes a passenger from the manifest and the flight
	 * @param passenger number for the GUI to use
	 */
	public void removePassenger(int passenger) {
		manifest.removePassenger(passenger);
	}
	/**
	 * This method adds a passenger to the manifest and attempts
	 * to find a seat for this reservation. If name is null or all
	 * whitespace it throws illegal argument exception. When invoked it creates
	 * a new FlightReservation
	 * @param reservtype type of reservation 0=first class, 1= bus class, 2=econ class
	 * @param passengername passenger's name
	 * @param seatpref passenger's seat preference where true is prefers window, false is prefers aisle
	 * 
	 */
	public void addPassenger(int reservtype, String passengername, boolean seatpref) {
		//This is where I need to create a reservation
		//Does this throw illegal argument exception when name is null?
		if(passengername == null || passengername.length() == 0 || passengername.isEmpty()){
			throw new IllegalArgumentException();
		} else if(reservtype == 0){
				FirstClassReservation firclassres = new FirstClassReservation(passengername, (Flight) plane, seatpref); 
				//can use public flightreservation methods/variables 
				//on all of these reservation 'children' because these reservations are children of flight reservation
				//plane.reserveFirstClassSeat(seatpref);//will return reserved seat, null if could not find seat to reserve
				firclassres.findSeat();
				manifest.add(firclassres);
				//index++;		
		} else if(reservtype == 1) {		
			 	BusinessClassReservation busres = new BusinessClassReservation(passengername, (Flight) plane, seatpref);
				//plane.reserveBusinessSeat(seatpref);//will return reserved seat, null if could not find seat to reserve
				busres.findSeat();
				manifest.add(busres);
		} else {
				EconomyReservation econclassres = new EconomyReservation(passengername, (Flight) plane, seatpref);
				//plane.reserveEconomySeat(seatpref);//will return reserved seat, null if could not find seat to reserve
				econclassres.findSeat();
				manifest.add(econclassres);
				//index++;	
		}	
	}
	/**
	 * Gets a seat map of seats with names
	 * @return array of seat names
	 */
	public String[][] getSeatMap() {
		return plane.getSeatMap();
	}
	/**
	 * Gets a seat map of seats and their occupied status
	 * @return array of seat status
	 */
	public boolean[][] getSeatOccupationMap() {
		return plane.getSeatOccupationMap();
	}
	/**
	 * Gets a seat map of seats and their occupied status and prints as string
	 * @return string of reservation report
	 */
	public String printReservations() {
		return manifest.report();
	}
}
