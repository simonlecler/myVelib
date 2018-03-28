package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.byciclePackage.BycicleSpeeds;
import myVelibCore.exceptions.BadSpeedSelectionException;

class BycicleSpeedsTest {

	@Test
	void testGetSpeed() throws BadSpeedSelectionException {
		int speed1 = BycicleSpeeds.getSpeed("Foot");
		int speed2 = BycicleSpeeds.getSpeed("Electrical");
		int speed3 = BycicleSpeeds.getSpeed("Mechanical");
		int speed4 = BycicleSpeeds.getSpeed("n'importe quoi");
		assertTrue(speed1 == 4);
		assertTrue(speed2 == 20);
		assertTrue(speed3 == 15);
		assertTrue(speed4 == 0);
	}

}
