/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import static org.junit.Assert.*;

import org.junit.Test;

/**Tests the seat class
 * @author Andrew Northrup
 *
 */
public class SeatTest {
	/**
	 * Tests create seat.
	 */
	@Test
	public void testAlphaOrderOfAdd() {
		Seat myseat = new Seat("2A", true, false);
		assertEquals("2A", myseat.getLocation());
		assertFalse(myseat.isOccupied());
	}
	/**
	 * Tests create occupy and release seat.
	 */
	@Test
	public void testOccupyAndRelease() {
		Seat myseat = new Seat("2A", true, false);
		assertFalse(myseat.isOccupied());
		myseat.occupy();
		assertTrue(myseat.isOccupied());
		myseat.release();
		assertFalse(myseat.isOccupied());
	}
}
