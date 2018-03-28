package myVelibCore.utilities;

import myVelibCore.stationPackage.Network;

/**
 * <b>This class allows to have a unique thread for the time management</b>
 * <p>It is a singleton pattern. This class implements runnable interface.
 * @author Simon Lecler
 *
 */
public class RunningTime implements Runnable{
	private static RunningTime instance;

	private RunningTime() {
		
	}
	/**
	 * 
	 * @return the unique instance of the RunningTime class
	 */
	public static RunningTime getInstance() {
		if (instance==null) {
			instance = new RunningTime();
			}
			return instance;
	}
	/**
	 * Overridden method run from interface runnable
	 * During the simulation, the time is incremented of 1 min at each loop
	 */
	@Override
	public void run() {
		Time clock = new Time();
		while(Network.isSimulation_On()) {
			clock.updateTime();	
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}
		}		
	}
	
	



