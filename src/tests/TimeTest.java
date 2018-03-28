package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.RunningTime;
import myVelibCore.utilities.Time;

class TimeTest {

	@Test
	void testOperationTime() {
		Time.setCurrentTime(3);
	
		assertTrue(Time.operationTime(3).getRegisteredTime()==6);
		assertTrue(Time.getStaticCurrentTime()==3);
		
	}

	@Test
	void testTimeDifference() {
		Time t1 = new Time(6);
		Time t2 = new Time(3);
		assertTrue(Time.timeDifference(t1,t2)==3);
		assertTrue(Time.timeDifference(t2,t1)==3);
	}

	@Test
	void testTimeDifferenceBetween() {
		Time t1 = new Time(6);
		Time t2 = new Time(3);
		assertTrue(t1.timeDifferenceBetween(t2)==3);
		assertTrue(t2.timeDifferenceBetween(t1)==3);
	}

	@Test
	void testIsAfter() {
		Time t1 = new Time(6);
		Time t2 = new Time(3);
		assertTrue(t1.isAfter(t2));
	}

	@Test
	void testIsBefore() {
		Time t1 = new Time(6);
		Time t2 = new Time(3);
		assertTrue(t2.isBefore(t1));
	}

	@Test
	void testUpdateTime() {
		Thread thread = new Thread(RunningTime.getInstance());
		Time time = new Time();
		thread.start();
		time.updateTime();
		Network.setSimulaton_On(false);
		assertTrue(Time.getStaticCurrentTime()==1);
	}

}
