package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.RunningTime;
import myVelibCore.utilities.Time;

class RunningTimeTest {

	@Test
	void testGetInstance() {
		RunningTime timeThread = RunningTime.getInstance();
		assertTrue(RunningTime.getInstance()!=null);
	}

	@Test
	void testRun() {
		
		Thread thread = new Thread(RunningTime.getInstance());
		
		try {
			thread.start();
			Thread.sleep(100);
			Network.setSimulaton_On(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(Time.getStaticCurrentTime() == 8 || Time.getStaticCurrentTime()==9);
	}

}
