/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Flight is the class that represents the airplanes and controls
 * the seats.
 * @author Andrew Northrup
 *
 */
public class Flight implements SeatingManager {
	/**Final static variable for capacity of coach class reservations that allows coach to still be added to*/
	private final static int COACH_CAP = 80;
	/**Instance variable for seat label*/
	private String seatLabels;
	/**Instance variable for number of rows on airplane*/
	private int numRows;
	/**Instance variable for number of columns of airplane seats*/
	private int numColumns;
	/**Instance variable for start row of first class seating*/
	private int startFirstClass;
	/**Instance variable for start row of business class seating*/
	private int startBusiness;
	/**Instance variable for start row of economy class seating*/
	private int startEconomy;
	/**Instance variable for most recent economy row seated*/
	private int mostRecentEconomyRow;
	/**Instance variable for the number of economy seats reserved*/
	private int economySeatsReserved; //start at zero and increment each time one is found
	/**Instance variable for the number of economy seats total capacity*/
	private int economyCapacity;
	/**Instance variable for the seats on a plane*/
	private Seat[][] map;
	/**Instance variable for the row the seat is on in the seat map array*/
	//private int mapArrRow;
	//**Instance variable for the array row 1st class starts*/
	private int firstStartArrRow;
	//**Instance variable for the array row business class starts*/
	private int busStartArrRow;
	//**Instance variable for the array row economy class starts*/
	private int econStartArrRow;
	/**Constructor for flight, that reads in an airplane file and sets up the flight
	 * according to those specifications. If airplane file is not valid an exception is caught.
	 * @param filename name of the file that the flight is
	 * 
	 */
	public Flight(String filename) {
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new File(filename));
		
		for(int i = 0; i < 6; i++){
			try{
				//Sets up the flight variables
				if(fileScanner.hasNext()){
					switch(i) {
					case 0: numRows = Integer.parseInt(fileScanner.nextLine()); break;
					case 1: seatLabels = fileScanner.nextLine(); break;
					case 2: numColumns = Integer.parseInt(fileScanner.nextLine()) + seatLabels.length(); break; 
					case 3: startFirstClass = Integer.parseInt(fileScanner.nextLine()); break;
					case 4: startBusiness = Integer.parseInt(fileScanner.nextLine()); break;
					case 5: startEconomy = Integer.parseInt(fileScanner.nextLine()); break;
					default: break;
					}					
				}				
			} catch (IllegalArgumentException e) {
				fileScanner.close();
				//System.out.println("Error reading in the first 6 rows");
			}
		}
		//Set up seatmap to add seats to when created, don't subtract one becuse this isn't
		//index it is a size of the array
		map = new Seat[numRows][numColumns];
		//start to process the seats, do until complete
		
			for (int i = 0; i < map.length; i++){
				try {	
					String row = fileScanner.nextLine();
					row = row.trim();
					Scanner lineScanner = new Scanner(row);
					try {
						for(int k = 0; k < map[0].length; k++){			
							map[i][k] = createSeat(lineScanner.next(), row);
						} 
					} catch (NoSuchElementException e) {
						lineScanner.close();
						throw new IllegalArgumentException();
					}
					lineScanner.close();
				} catch (IllegalArgumentException e) {
				fileScanner.close();
				//System.out.println("Error while reading in seat lines");
				}
			}	
		fileScanner.close();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		firstStartArrRow = findFirstClassMapRow();
		busStartArrRow = findBusClassMapRow();
		econStartArrRow = findEconClassMapRow();
	}
	/**
	 * Reserves business class seat by cycling through seats starting
	 * with seat preference, then class, then any seats in a lesser
	 * class
	 * @param for which seat is preferred
	 */
	@Override
	public String reserveBusinessSeat(boolean prefersWindow) {
		//Need this in case bus start row is equal to econ start row
		if(busStartArrRow == econStartArrRow){
			return reserveEconomySeat(prefersWindow);
		}
		for (int i = busStartArrRow; i < econStartArrRow; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null){
					if(!map[i][k].isOccupied() && map[i][k].isAisleSeat() && !prefersWindow) {//finds an aisle seat if passenger wants not window
						map[i][k].occupy();
						if(i >= econStartArrRow){economySeatsReserved++; }
						return map[i][k].getLocation();	
					}	
					else if(!map[i][k].isOccupied() && map[i][k].isWindowSeat() && prefersWindow) {						
							map[i][k].occupy();
							if(i >= econStartArrRow){economySeatsReserved++; }
							return map[i][k].getLocation();	
					}
				}
			}
		}
		//find any other seat in business class and moving back
		for (int i = busStartArrRow; i < econStartArrRow; i++){
			for(int k = 0; k < map[0].length; k++) {
				if(map[i][k] != null && !map[i][k].isOccupied()){
						map[i][k].occupy();
						return map[i][k].getLocation();	
				}
			}
		}
		return null;

		
	}
	/**
	 * Reserves first class seat by cycling through seats starting
	 * with seat preference, then class, then any seats in a lesser
	 * class
	 * @param for which seat is preferred true is window, false is aisle
	 */
	@Override
	public String reserveFirstClassSeat(boolean prefersWindow) {
		if(busStartArrRow == firstStartArrRow){
			return reserveBusinessSeat(prefersWindow);
		}
		for (int i = firstStartArrRow; i < busStartArrRow; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null){
					if(!map[i][k].isOccupied() && map[i][k].isAisleSeat() && !prefersWindow) {//finds an aisle seat if passenger wants not window
						map[i][k].occupy();
						return map[i][k].getLocation();				
					}
					else if(!map[i][k].isOccupied() && map[i][k].isWindowSeat() && prefersWindow){
						map[i][k].occupy();
						return map[i][k].getLocation();	
					}
				}
			}	
		}
		//reserve any unoccupied first class seat starting in first class and moving back
		for (int i = firstStartArrRow; i < busStartArrRow; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null && !map[i][k].isOccupied()){
					
						map[i][k].occupy();
						return map[i][k].getLocation();	
					
				}
			}
		}
		return null; //couldn't find any first class seats
	}
	/**
	 * Reserves economy class seat by cycling through seats starting
	 * with seat preference, then moving back from last assigned economy row
	 * then any seats in economy
	 * @param for which seat is preferred, true is window false is aisle
	 */
	@Override
	public String reserveEconomySeat(boolean prefersWindow) {
		//NOT USED SINCE CALLED IN FINDSEAT
		/*		if(coachAtCap()==true){
			return null;
		}*/
		//FIRST START AT LAST USED ECONOMY SEAT
		if(mostRecentEconomyRow >= econStartArrRow){
			for (int i = mostRecentEconomyRow; i < map.length; i++){
				for(int k = 0; k < map[0].length; k++){
					if(map[i][k] != null){
						if(!map[i][k].isOccupied() && map[i][k].isAisleSeat() && !prefersWindow) {//finds an aisle seat if passenger wants not window
							map[i][k].occupy();
							economySeatsReserved++;
							//update most recent economy row to i
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						}
						else if(!map[i][k].isOccupied() && map[i][k].isWindowSeat() && prefersWindow){
							map[i][k].occupy();
							economySeatsReserved++;
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						}
					}
				}
			}
			//NEED TO START AT BEGINNING OF SECTION AND START LOOKING, NOT JUST FIND ANY OPEN SEAT
			for (int i = econStartArrRow; i < map.length; i++){
				for(int k = 0; k < map[0].length; k++){
					if(map[i][k] != null){
						if(!map[i][k].isOccupied() && map[i][k].isAisleSeat() && !prefersWindow) {//finds an aisle seat if passenger wants not window
							map[i][k].occupy();
							economySeatsReserved++;
							//update most recent economy row to i
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						}
						else if(!map[i][k].isOccupied() && map[i][k].isWindowSeat() && prefersWindow) {
							map[i][k].occupy();
							economySeatsReserved++;
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						}
					}
				}
			}
			
			//Find any open seat 
			for (int i = mostRecentEconomyRow; i < map.length; i++){
				for(int k = 0; k < map[0].length; k++){
					if(map[i][k] != null && !map[i][k].isOccupied()){
						
							map[i][k].occupy();
							economySeatsReserved++;
							return map[i][k].getLocation();	
						
					}
				}
			}
		}
		else{
			for (int i = econStartArrRow; i < map.length; i++){
				for(int k = 0; k < map[0].length; k++) {
					if(map[i][k] != null){
						if(!map[i][k].isOccupied() && map[i][k].isAisleSeat() && !prefersWindow) {//finds an aisle seat if passenger wants not window
							map[i][k].occupy();
							economySeatsReserved++;
							//update most recent economy row to i
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						}
						else if(!map[i][k].isOccupied() && map[i][k].isWindowSeat() && prefersWindow) {
							map[i][k].occupy();
							economySeatsReserved++;
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						}
					}
				}
			}
		
		//find any other seat starting in business class and moving back
			for (int i = econStartArrRow; i < map.length; i++){
				for(int k = 0; k < map[0].length; k++){
					if(map[i][k] != null && !map[i][k].isOccupied()){
						
							map[i][k].occupy();
							economySeatsReserved++;
							mostRecentEconomyRow = i;
							return map[i][k].getLocation();	
						
					}
				}
			}
		}
		//loop goes back to beginning and starts searching to find anything in econ class
		for (int i = econStartArrRow; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null && !map[i][k].isOccupied()){
						map[i][k].occupy();
						economySeatsReserved++;
						mostRecentEconomyRow = i;
						return map[i][k].getLocation();		
				}
			}
		}
	return null;
	}
	/**
	 * This method gets all of the seats and the seat names
	 * @return two dimensional array of labels for seats
	 */
	@Override
	public String[][] getSeatMap() {
		String[][] locArr = new String[map.length][map[0].length];
		for (int i = 0; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null){
					locArr[i][k] = map[i][k].getLocation();
				}
				else{
					locArr[i][k] = null;
				}
			}
		}
		return locArr;
	}
		
	/**
	 * This method gets all of the seats occupied status
	 * @return array of seats whether they are occupied or not
	 */
	@Override
	public boolean[][] getSeatOccupationMap() {
		boolean[][] occArr = new boolean[map.length][map[0].length];
		for (int i = 0; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null){
					occArr[i][k] = map[i][k].isOccupied();
				}
				else{
					occArr[i][k] = false;
				}
			}
		}
		return occArr;
	}
	/**
	 * Determines if coach seating is at capacity
	 * @return true if coach is at capacity
	 */
	//Since this is public you can call it in the find seat method
	public boolean coachAtCap() {
		numOfCoachSeats();
		double pctfull = ((double)economySeatsReserved / (double)economyCapacity) * 100;
		if(pctfull >= COACH_CAP){
			return true;
		}
		return false;
	}
	/**
	 * Releases a seat and make its available.
	 * @param seat to be released
	 */
	public void releaseSeat(String seat) {
		for (int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null && map[i][k].getLocation().equals(seat)){
						map[i][k].release();
						if(i >= econStartArrRow){
							economySeatsReserved--; //If a economy seat is released, allow more econoy sitters
						}
				}
			}
		}
	}
	//method creates a seat if it fits the variables for a seat
	private Seat createSeat(String seat, String seatline){
		if(seat.equals("XXX")){
			return null; //seat is null if it is an aisle or non sittable		
		}
		else{
			//Creates a new seat, probably need to do something with this
			//like add it to the map of seats
			boolean detWinSeat = determineWinSeat(seat);
			//System.out.println(detWinSeat);
			boolean detAisSeat = determineAisSeat(seat, seatline);
			//System.out.println(detAisSeat);
			Seat s = new Seat(seat, detWinSeat, detAisSeat);
			//s.release();
			return s;
		}
	}
	//limit of method is 24 rows across
	//method should determine if it is a window seat or not
	private boolean determineWinSeat(String seat){
		//System.out.println(seat.charAt(seat.length()-1));
		//System.out.println(seatLabels.charAt(0));
		//System.out.println(seat.charAt(seat.length()-1));
		//System.out.println(seatLabels.charAt(seatLabels.length()-1));
		if(seat.charAt(seat.length() - 1) == seatLabels.charAt(0) || seat.charAt(seat.length() - 1) == seatLabels.charAt(seatLabels.length() - 1)){
			return true;
		}
		return false;
	}
	//determines if aisle seat by searching the entire string
	private boolean determineAisSeat(String seat, String seatline){
		int seatStart = seatline.indexOf(seat);
		int seatEnd = seatline.indexOf(seat) + seat.length();
		boolean lookleft = false;
		boolean lookright = false;
		//look left to see if 'XXX' is next to it
		//System.out.println(seatStart);
		//System.out.println(seatEnd);
		if(seatStart != 0){
			for(int i = 0; i < 10; i++){
				if (seatline.charAt(seatStart - i - 1) == ' '){
					System.out.println("Found a Blank");
				} else if(seatline.charAt(seatStart - i - 1) != 'X'){
					lookleft = false;
					break;
				} else if(seatline.charAt(seatStart - i - 2) != 'X'){
					lookleft = false;
					break;
				} else{
					lookleft = true;
					break;
				}
			}	
		}
		//System.out.println(seatline.length());
		//look to the right to see if XXX is next to it	
		if(seatEnd != seatline.length()){
			for(int i = 0; i < 10; i++){
				if (seatline.charAt(seatEnd + i) == ' '){
					System.out.println("Found a Blank");
				} else if(seatline.charAt(seatEnd + i) != 'X'){
					lookright = false;
					break;
				} else if(seatline.charAt(seatEnd + i + 1) != 'X'){
					lookright = false;
					break;
				} else{
					lookright = true;
					break;
				}
			}				
		}
		if(lookleft || lookright ){
			return true;
		}
		return false;

		
	}
	//private method for determining where first class starts in map array
	private int findFirstClassMapRow(){
		for (int i = 0; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null && Integer.parseInt(map[i][k].getLocation().substring(0, map[i][k].getLocation().length() - 1)) == startFirstClass)	{
					
						return i;
					
				}	
			}
		}
	return 0;
	}
	//private method for determining where bus class starts in map array
	private int findBusClassMapRow(){
		for (int i = 0; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null && Integer.parseInt(map[i][k].getLocation().substring(0, map[i][k].getLocation().length() - 1)) == startBusiness){
					
						return i;
					
				}
			}
		}
	return 0;
	}
	//private method for determining where economy class starts in map array
	private int findEconClassMapRow(){
		for (int i = 0; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++){
				if(map[i][k] != null && Integer.parseInt(map[i][k].getLocation().substring(0, map[i][k].getLocation().length() - 1)) == startEconomy){
					
						return i;
					
				}	
			}
		}
	return 0;
	}
	//private method to determine number of coach seats
	private void numOfCoachSeats(){
		int coachSeats = 0;
		for (int i = econStartArrRow; i < map.length; i++){
			for(int k = 0; k < map[0].length; k++) {
				if(map[i][k] != null){
					coachSeats++;
				}
			}
		}
		economyCapacity = coachSeats;
	}
}
