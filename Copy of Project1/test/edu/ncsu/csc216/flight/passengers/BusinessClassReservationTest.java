/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Tests the business class reservation
 * @author Andrew Northrup
 *
 */
public class BusinessClassReservationTest {
	/**Instance variable for name of a valid test file*/
	private final String validTestFile = "test-files/tiny-plane.txt";
	/**
	 * Tests creating a business class reservation.
	 */
	@Test
	public void testBusinessReservation() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[1][0]);
		BusinessClassReservation reserOne = new BusinessClassReservation("John Doe", f, true);
		reserOne.findSeat();
		assertEquals("2A", reserOne.getSeat());
		assertTrue(f.getSeatOccupationMap()[1][0]);
		System.out.println(reserOne.stringForPrint());
		
	}
	
	/**
	 * Tests printing the business class reservation.
	 */
	@Test
	public void testBusinessReservationForPrint() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[1][0]);
		BusinessClassReservation reserOne = new BusinessClassReservation("John Doe    ", f, true);
		reserOne.findSeat();
		System.out.print(reserOne.stringForPrint());
		assertEquals("2A", reserOne.getSeat());
		assertTrue(f.getSeatOccupationMap()[1][0]);
	}	
}
