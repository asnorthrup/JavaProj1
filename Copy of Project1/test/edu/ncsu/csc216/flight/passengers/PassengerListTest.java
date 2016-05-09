package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;
/**
 * Tests passenger list class.
 * @author Andrew Northrup
 */
public class PassengerListTest {
	private final String validTestFile = "test-files/tiny-plane.txt";
	
	/**
	 * Tests adds to list alphabetically().
	 */
	@Test
	public void testAlphaOrderOfAdd() {
		Flight f = new Flight(validTestFile);
		PassengerList l = new PassengerList();
		FirstClassReservation a = new FirstClassReservation("john", f, true);
		a.findSeat();
		l.add(a);
		FirstClassReservation b = new FirstClassReservation("jane", f, true);
		b.findSeat();
		l.add(b);
		System.out.println(l.report());
		FirstClassReservation c = new FirstClassReservation("cher", f, true);
		c.findSeat();
		l.add(c);
		l.report();
		System.out.println(l.report());
		FirstClassReservation d = new FirstClassReservation("steve", f, true);
		d.findSeat();
		l.add(d);
		l.report();
		System.out.println(l.report());
		FirstClassReservation e = new FirstClassReservation("adam", f, true);
		e.findSeat();
		l.add(e);
		l.report();
		System.out.println(l.report());
		l.report();
		assertNotNull(l.report());
	}
}
