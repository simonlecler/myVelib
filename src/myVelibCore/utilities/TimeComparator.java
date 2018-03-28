package myVelibCore.utilities;

import java.util.Comparator;
/**
 * <b>Creation of a comparator for Time</b>
 * This classed is used to define an order relationship between objects of type Time.
 * @author Edouard Benauw
 *
 */
public class TimeComparator implements Comparator<Time>{
	/**
	 * @param t1
	 * 		First time to be compared
	 * @param t2
	 * 		Second time to be compared
	 * @return 1 if t2 precedes t1, -1 if t1 precedes t2, 0 if t1 and t2 are the same time
	 */
	@Override
	public int compare(Time t1, Time t2) {
		if(t1.getRegisteredTime()>t2.getRegisteredTime()) {return 1;}
		if(t1.getRegisteredTime()<t2.getRegisteredTime()) {return -1;}
		else {return 0;}
	}

}
