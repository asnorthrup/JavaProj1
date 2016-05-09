/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Tests the flight class
 * @author Andrew Northrup
 *
 */
public class FlightTest {
	/**Private instance variable for invalid plane file */
	private final String validTestFile = "test-files/tiny-plane.txt";
	/**Private instance variable for invalid plane file*/
	private final String invalidTestFile = "test-files/tiny-plane-invalid.txt";
	
	/**
	 * Tests read valid air plane file and construct airplane.
	 */
	@Test
	public void testReadValidFile() {
		Flight f = new Flight(validTestFile);
		assertEquals("2B", f.getSeatMap()[1][1]);
	}
	/**
	 * Tests inValidPlaneFile().
	 */
	@Test
	public void testInvalidPlaneFile() {
		try{
		Flight f = new Flight(invalidTestFile);
		System.out.println(f.getSeatMap()[1][1]);
		fail();
		} catch(Exception e) {
		System.out.println("Invalid File");	
		}
		
	}
	/**
	 * Tests getSeatMap().
	 */
	
	@Test
	public void testGetSeatMap(){
		Flight f = new Flight(validTestFile);
		assertEquals("2B", f.getSeatMap()[1][1]);
	}

	/**
	 * Tests reserving seat and getting occupation map.
	 */
	@Test
	public void testGetOccupationMap() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[1][0]);
		f.reserveBusinessSeat(true);
		assertTrue(f.getSeatOccupationMap()[1][0]);
		//assertFalse(f.getSeatOccupationMap()[0][0]);
		assertFalse(f.getSeatOccupationMap()[1][5]);
		f.reserveBusinessSeat(true);
		assertTrue(f.getSeatOccupationMap()[1][5]);
	}
	
	/**
	 * Tests reserving first class seat and viewing that the occupation map was updated.
	 */
	@Test
	public void testReserveFirstClassSeat() {
		 Flight f = new Flight(validTestFile);
			f.reserveFirstClassSeat(true);
			assertTrue(f.getSeatOccupationMap()[0][0]);
	}
	/**
	 * Tests Reserving Economy seat and getting occupation map().
	 */
	@Test
	public void testReserveEconomySeat() {
		Flight f = new Flight(validTestFile);
		f.reserveEconomySeat(true);
		assertTrue(f.getSeatOccupationMap()[3][5]);
	}

	/**
	 * Tests Releasing a Seat.
	 */
	@Test
	public void testReleaseSeat() {
		Flight f = new Flight(validTestFile);
		f.reserveEconomySeat(true);
		assertTrue(f.getSeatOccupationMap()[3][5]);
		String s = "6E";
		f.releaseSeat(s);
		assertFalse(f.getSeatOccupationMap()[3][5]);
	}
	/**
	 * Tests get coach at capacity.
	 */
	@Test
	public void testCoachAtCapacity() {
		Flight f = new Flight(validTestFile);
		f.reserveEconomySeat(true); //1
		f.reserveEconomySeat(true); //2
		f.reserveEconomySeat(true); //3
		f.reserveEconomySeat(true); //4
		f.reserveEconomySeat(true); //5
		f.reserveEconomySeat(true); //6
		f.reserveEconomySeat(true); //7
		f.reserveEconomySeat(true); //8
		f.reserveEconomySeat(true); //9
		f.reserveEconomySeat(true); //10
		assertFalse(f.coachAtCap());
		f.reserveEconomySeat(true); //11
		assertTrue(f.coachAtCap());
	}	
	
	
}
