package myVelibCore.utilities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <b>This class allows to have a clock, simulating a time that is running out.</b>
 * <p>There is an actual time that is the same for all users and that is updated regularly
 * @author Edouard Benauw & Simon Lecler
 *
 */
public class Time  {
	/**
	 * Actual time shared by all users
	 * @see Time#getStaticCurrentTime()
	 */
	private static int currentTime;
	/**
	 * Time registered when an operation needing to register the time is performed
	 */
	private int registeredTime;
	
	private static Time originalTime = new Time();
	
	/**
	 * Unique instance of RunnningTime
	 * @see RunningTime
	 * @see RunningTime#getInstance()
	 */
	//private RunningTime timeThread = RunningTime.getInstance();
	//public Thread getClock() {
		//return clock;
	//}

	/**
	 * Thread that plays the role of a clock. Its role is to update the time
	 * @see Time#updateTime()
	 */
	//private Thread clock = new Thread(timeThread);
	
	//public RunningTime getTimeThread() {
		//return timeThread;
	//}
	public static Lock lock = new ReentrantLock();
	public Time() {
		this.registeredTime=Time.getStaticCurrentTime();
	}
	
	public Time(int minutes) {
		this.registeredTime = minutes;
	}
	
	public static int getStaticCurrentTime() {
		return(currentTime);
	}
	
	static public Time getCurrentTime() {
		return (new Time());
	}
	
	static public Time getOriginalTime() {
		return (originalTime);
	}
	
	public static void setCurrentTime(int currentTime) {
		Time.currentTime = currentTime;
	}

	/**
	 * @param minutes
	 * 		Duration of the given operation in minutes (type int)
	 * @return
	 * 		Actual time when the operation is achieved (for example when a user ends up a ride)  (type Time)
	 */
	static public Time operationTime(int minutes) {
		return new Time(Time.currentTime + minutes);
	}

	/**
	 * 
	 * @param t1
	 * 		The first time
	 * @param t2
	 * 		The second time
	 * @return The duration of the interval between the two times entered as parameters. It returns a positive Integer
	 */	
	static public int timeDifference(Time t1, Time t2) { //Always positive
		return Math.max(t1.registeredTime-t2.registeredTime, t2.registeredTime-t1.registeredTime);
	}
	/**
	 * 
	 * @param t1
	 * 		Time
	 * @return The duration of the interval between the calling time and the time passes as parameter. It returns a positive Integer.
	 */
	public int timeDifferenceBetween(Time t1){
		return Math.max(t1.registeredTime-this.registeredTime, this.registeredTime-t1.registeredTime);
	}
	
	
	@Override
	public String toString() {
		return "Time [registeredTime=" + registeredTime + "]";
	}

	public int getRegisteredTime() {
		return registeredTime;
	}
	
	/**
	 * 
	 * @param t
	 * 	Time 
	 * @return true if the time passes as a parameter precedes the calling time, false otherwise.
	 */
	public boolean isAfter(Time t) {
		if(this.getRegisteredTime()>t.getRegisteredTime()) {return true;}
		if(this.getRegisteredTime()<t.getRegisteredTime()) {return false;}
		else {return true;}
	}
	/**
	 * 
	 * @param t
	 * 	Time 
	 * @return true if the calling time precedes the time passes as a parameter, false otherwise
	 */
	public boolean isBefore(Time t) {
		if(this.getRegisteredTime()<t.getRegisteredTime()) {return true;}
		if(this.getRegisteredTime()>t.getRegisteredTime()) {return false;}
		else {return true;}
	}
	
	/**
	 * @return The current time increased by an increment of minute every 10ms.
	 */
	//public synchronized void updateTime() {
		
		//	try {
			//	Thread.sleep(10);
//			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
			//}
		//	Time.setCurrentTime(currentTime+1);
			//notifyAll();
//		}
	public void updateTime() {
		Time.setCurrentTime(currentTime+1);	
		
	}
	
		
		
		
		
}

