/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

/**
 * This is seat class represents a seat that someone can sit in
 * on the plane
 * @author Andrew Northrup
 *
 */
public class Seat {
	/**Instance variable for location of seat*/
	private String location;
	/**Instance variable for whether the seat is occupied or not*/
	private boolean occupied;
	/**Instance variable for whether the seat is a window seat*/
	private boolean windowSeat;
	/**Instance variable for whether the seat is a aisle seat*/
	private boolean aisleSeat;
	/**
	 * Constructor for seat
	 * @param location for the name of the seat
	 * @param windowseat boolean for whether seat is an window or not
	 * @param aisleseat boolean for whether seat is an aisle or not
	 *  
	 */

	public Seat(String location, boolean windowseat, boolean aisleseat){
		this.location = location.trim();
		this.windowSeat = windowseat;
		this.aisleSeat = aisleseat;
		this.occupied = false;
	}
	/**
	 * Determines whether seat is occupied or not
	 * @return true if seat is occupied
	 */
	public boolean isOccupied(){
		return occupied;
	}
	/**
	 * Determines whether seat is window seat
	 * @return true if is a window seat
	 */
	public boolean isWindowSeat(){
		return windowSeat;
	}
	/**
	 * Determines whether seat is aisle seat
	 * @return true if seat is an aisle seat
	 */
	public boolean isAisleSeat(){
		return aisleSeat;
	}
	/**
	 * Turns seat into an occupied seat
	 */
	public void occupy(){
		occupied = true;
	}
	/**
	 * Turns seat into an unoccupied seat
	 */
	public void release(){
		occupied = false;
	}
	/**
	 * Gets the location of the seat
	 * @return string of the seat location
	 */
	public String getLocation(){
		return location;
	}	
}
