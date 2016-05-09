package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;
/**
 * Tests Economy reservation
 * @author Andrew Northrup
 */
public class EconomyReservationTest {
	/**Instance variable for name of a valid test file*/
	private final String validTestFile = "test-files/tiny-plane.txt";
	
	
	/** Tests readValidCourseRecords().
	 * @author Andrew Northrup
	 */
	@Test
	public void testEconomyReservation() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[3][5]);
		EconomyReservation reserOne = new EconomyReservation("John Doe", f, true);
		reserOne.findSeat();
		assertEquals("6E", reserOne.getSeat());
		assertTrue(f.getSeatOccupationMap()[3][5]);
	}
	/**
	 * Tests to see if current row will then look for matches before assigning any seat.
	 */
	@Test
	public void testAddAllAisleThenWindow() {
		Flight f = new Flight(validTestFile);
		EconomyReservation reserOne = new EconomyReservation("John Doe", f, false);
		reserOne.findSeat();
		EconomyReservation reserTwo = new EconomyReservation("John Da", f, false);
		reserTwo.findSeat();
		EconomyReservation reserThree = new EconomyReservation("John Db", f, false);
		reserThree.findSeat();
		EconomyReservation reserFour = new EconomyReservation("John Dc", f, false);
		reserFour.findSeat();
		EconomyReservation reserFive = new EconomyReservation("John De", f, false);
		reserFive.findSeat();
		EconomyReservation reserSix = new EconomyReservation("John Df", f, true); 
		reserSix.findSeat(); 
		EconomyReservation reserSeven = new EconomyReservation("John Dg", f, true);
		reserSeven.findSeat();
		EconomyReservation reserEight = new EconomyReservation("John Dh", f, true);
		reserEight.findSeat(); 
		assertEquals(reserEight.getSeat(), "6E") ;
		
	}
	/**
	 * Tests to the max capacity of coach. Also tests releasing a seat and allowing a coach
	 * reservation to find it.
	 */
	@Test
	public void testEconomyReservationMaxCap() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[3][5]);
		EconomyReservation reserOne = new EconomyReservation("John Doe", f, false);
		reserOne.findSeat();
		EconomyReservation reserTwo = new EconomyReservation("John Da", f, true);
		reserTwo.findSeat();
		EconomyReservation reserThree = new EconomyReservation("John Db", f, true);
		reserThree.findSeat();
		EconomyReservation reserFour = new EconomyReservation("John Dc", f, true);
		reserFour.findSeat();
		EconomyReservation reserFive = new EconomyReservation("John De", f, true);
		reserFive.findSeat();
		EconomyReservation reserSix = new EconomyReservation("John Df", f, true); //didn't reserve economyseat
		reserSix.findSeat();
		EconomyReservation reserSeven = new EconomyReservation("John Dg", f, true);
		reserSeven.findSeat();
		EconomyReservation reserEight = new EconomyReservation("John Dh", f, true);
		reserEight.findSeat();
		EconomyReservation reserNine = new EconomyReservation("John Di", f, true);
		reserNine.findSeat();
		EconomyReservation reserTen = new EconomyReservation("John Dj", f, true);
		reserTen.findSeat();
		EconomyReservation reserEle = new EconomyReservation("John Dk", f, true);
		reserEle.findSeat();
		EconomyReservation reserTwel = new EconomyReservation("John Dl", f, true);
		reserTwel.findSeat();
		assertEquals(null, reserTwel.getSeat());
		f.releaseSeat("11A");
		reserTwel.findSeat();
		assertEquals(reserTwel.getSeat(), "11A");

	}
	/**
	 * Tests readValidCourseRecords().
	 */
	@Test
	public void testEconomyReservationsRecentRow() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[3][5]);
		EconomyReservation reserOne = new EconomyReservation("John Doe", f, false);
		reserOne.findSeat();
		assertEquals("6C", reserOne.getSeat());
		EconomyReservation reserTwo = new EconomyReservation("John Da", f, true);
		reserTwo.findSeat();
		assertEquals("6E", reserTwo.getSeat());
		EconomyReservation reserThree = new EconomyReservation("John Db", f, true);
		assertNull(reserThree.getSeat());
		reserThree.findSeat();
		assertEquals("10A", reserThree.getSeat());
		assertEquals("Coach", reserThree.stringForPrint().substring(0, 5));
	}
	/**
	 * Tests to the max capacity of coach. Also tests releasing a seat and allowing a coach
	 * reservation to find it.
	 */
	@Test
	public void testEconomyReservationAddBusReserToFullcoach() {
		Flight f = new Flight(validTestFile);
		assertFalse(f.getSeatOccupationMap()[3][5]);
		EconomyReservation reserOne = new EconomyReservation("John Doe", f, false);
		reserOne.findSeat();
		assertFalse(reserOne.wantsWindowSeat());
		assertEquals(reserOne.getVehicle().getSeatMap()[0][0], f.getSeatMap()[0][0]);
		EconomyReservation reserTwo = new EconomyReservation("John Da", f, true);
		reserTwo.findSeat();
		EconomyReservation reserThree = new EconomyReservation("John Db", f, true);
		reserThree.findSeat();
		EconomyReservation reserFour = new EconomyReservation("John Dc", f, true);
		reserFour.findSeat();
		EconomyReservation reserFive = new EconomyReservation("John De", f, true);
		reserFive.findSeat();
		EconomyReservation reserSix = new EconomyReservation("John Df", f, true); //didn't reserve economyseat
		reserSix.findSeat();
		EconomyReservation reserSeven = new EconomyReservation("John Dg", f, true);
		reserSeven.findSeat();
		EconomyReservation reserEight = new EconomyReservation("John Dh", f, true);
		reserEight.findSeat();
		EconomyReservation reserNine = new EconomyReservation("John Di", f, true);
		reserNine.findSeat();
		EconomyReservation reserTen = new EconomyReservation("John Dj", f, true);
		reserTen.findSeat();
		EconomyReservation reserEle = new EconomyReservation("John Dk", f, true);
		reserEle.findSeat();
		EconomyReservation reserTwel = new EconomyReservation("John Dl", f, true);
		reserTwel.findSeat();
		assertEquals(null, reserTwel.getSeat());
		BusinessClassReservation reserFif = new BusinessClassReservation("John Dn", f, true);
		reserFif.findSeat();
		BusinessClassReservation reserSixt = new BusinessClassReservation("John Do", f, true);
		reserSixt.findSeat();
		BusinessClassReservation reserSevt = new BusinessClassReservation("John Dp", f, true);
		reserSevt.findSeat();
		BusinessClassReservation reserEighte = new BusinessClassReservation("John Dq", f, true);
		reserEighte.findSeat();
		BusinessClassReservation reserNinete = new BusinessClassReservation("John Dr", f, true);
		reserNinete.findSeat();
		BusinessClassReservation reserTwen = new BusinessClassReservation("John Ds", f, true);
		reserTwen.findSeat();
		BusinessClassReservation reserTweno = new BusinessClassReservation("John Dt", f, true);
		reserTweno.findSeat();
		BusinessClassReservation reserTwentw = new BusinessClassReservation("John Du", f, true);
		reserTwentw.findSeat();
		BusinessClassReservation reserTwenth = new BusinessClassReservation("John Dv", f, true);
		reserTwenth.findSeat();
		BusinessClassReservation reserTwenfo = new BusinessClassReservation("John Dl", f, true);
		reserTwenfo.findSeat();
		BusinessClassReservation reserTwenfi = new BusinessClassReservation("John Dlx", f, true);
		reserTwenfi.findSeat();
		System.out.println(reserTwenfi.getSeat());
		assertEquals("10C", reserTwenfi.getSeat());
		FlightReservation reserTwenSi = new BusinessClassReservation("John Dly", f, true);
		reserTwenSi.findSeat();
		assertEquals(reserTwenSi.stringForPrint().length(), 27);
		
	}
	
	
	
}
