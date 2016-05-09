/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the gate agent class.
 * @author Andrew Northrup
 *
 */
public class GateAgentTest {
	private final String validTestFile = "test-files/tiny-plane.txt";
	
	
	/**
	 * Tests constructor of gate agent
	 */
	@Test
	public void testReadValidFile() {
		GateAgent f = new GateAgent(validTestFile);
		assertFalse(f.getSeatOccupationMap()[0][0]);
		f.addPassenger(0, "john doe", true);
		assertTrue(f.getSeatOccupationMap()[0][0]);
		f.addPassenger(1, "jane doe", true);
		f.addPassenger(4, "steve doe", true);
		f.addPassenger(1, "xavier doe", true);
		f.addPassenger(0, "alan doe", true);
		f.removePassenger(1);
	}
	
	
}
