/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;

import java.util.ArrayList;


/**
 * Passenger list is a class that holds the list of passengers.
 * The passenger list has zero to many flight reservations.
 * @author Andrew Northrup
 *
 */
public class PassengerList {
	/**Instance variable for flight reservations*/
	private ArrayList<FlightReservation> list;
	/**
	 * Constructor of passenger list. 
	 */
	public PassengerList() {
		list = new ArrayList<FlightReservation>();
	}
	/**
	 * Adds a reservation to the passenger list in alphabetical order
	 * @param f for the flight reservation to be added
	 */
	public void add(FlightReservation f){
		if(list.isEmpty()){
			list.add(f);
			return;
			}
		String name = f.getName();
		int counter = 0;
		for (FlightReservation n: list){
			//negative if name is before what it is in list
			if(name.compareToIgnoreCase(n.getName()) > 0){
				counter++;
			}
		}
		list.add(counter, f);
	}
	/**
	 * Removes a passenger from the passenger list
	 * @param p is the int for the passenger to be removed from passenger list.
	 */
	public void removePassenger(int p){
		if(p < 0 || p > list.size() - 1){
			throw new IllegalArgumentException();
		}
		try{
		list.get(p).cancelReservation();
		list.remove(p);
		} catch(IllegalArgumentException e) {
		 	System.out.println("Out of Range");
		}
	}
	/**
	 * Provides a report of the passengers on the passenger list
	 * @return string of newline-separated list of strings representing FlightReservations
	 */
	public String report(){
		String reservations = "";
		for(FlightReservation fr: list){
			reservations = reservations + fr.stringForPrint() + '\n';
		}
		return reservations;
	}
}
