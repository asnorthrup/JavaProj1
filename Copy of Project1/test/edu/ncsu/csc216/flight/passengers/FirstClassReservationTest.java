package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;
/**
 * Tests first class reservation class.
 * @author Andrew Northrup
 */
public class FirstClassReservationTest {
	/**Instance variable for name of a valid test file*/
	private final String validTestFile = "test-files/tiny-plane.txt";
	
	/**
	 * Tests adding a first class reservation.
	 */
	@Test
	public void testFirstClassReservationAddsFirstclass() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[3][5]);
		FirstClassReservation reserOne = new FirstClassReservation("John Doe", f, false);
		reserOne.findSeat();
		FirstClassReservation reserTwo = new FirstClassReservation("John Da", f, true);
		reserTwo.findSeat();
		FirstClassReservation reserThree = new FirstClassReservation("John Db", f, true);
		reserThree.findSeat();
		FirstClassReservation reserFour = new FirstClassReservation("John Dc", f, true);
		reserFour.findSeat();
		FirstClassReservation reserFive = new FirstClassReservation("John De", f, true);
		reserFive.findSeat();
		FirstClassReservation reserSix = new FirstClassReservation("John Df", f, true);
		reserSix.findSeat();
		EconomyReservation reserSeven = new EconomyReservation("John Dg", f, true);
		reserSeven.findSeat();
		EconomyReservation reserEight = new EconomyReservation("John Dh", f, true);
		reserEight.findSeat();
		EconomyReservation reserTwel = new EconomyReservation("John Dl", f, true);
		assertEquals(null, reserTwel.getSeat());
		FirstClassReservation reserThir = new FirstClassReservation("John Dm", f, true);
		System.out.println(reserThir.getSeat());		
	}

	/**
	 * Tests testStartLastAssignedEconomyRow().
	 */
	@Test
	public void testStartLastAssignedEconomyRow() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[3][5]);
		EconomyReservation reserOne = new EconomyReservation("John Doe", f, false);
		reserOne.findSeat();
		assertEquals("6C", reserOne.getSeat());
		EconomyReservation reserTwo = new EconomyReservation("John Da", f, false);
		reserTwo.findSeat();
		assertEquals("10B", reserTwo.getSeat());
		EconomyReservation reserThree = new EconomyReservation("John Db", f, true);
		assertNull(reserThree.getSeat());
		reserThree.findSeat();
		assertEquals("10A", reserThree.getSeat()); 
	}
	
	/**
	 * Tests flight with the same starting row for business and first class.
	 */
	@Test
	public void testAssigningFlightsWithNoBusClass() {
		Flight f = new Flight("test-files/tiny-plane-sameclass.txt");
		BusinessClassReservation reserOne = new BusinessClassReservation("John Dal", f, false);
		reserOne.findSeat();		
		assertFalse(f.getSeatOccupationMap()[3][5]);
		assertEquals("1B", reserOne.getSeat());

	}	
}
