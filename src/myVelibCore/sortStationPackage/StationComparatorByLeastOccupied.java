package myVelibCore.sortStationPackage;

import java.util.Comparator;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.Time;

/**
 * <b>Comparator for stations according the occupation rate</b>
 * <p>This class allows to compare stations according their occupation rate during a time interval
 * @author Edouard Benauw
 *
 */
public class StationComparatorByLeastOccupied implements Comparator<Station> {
	/**
	 * Beginning of the time interval
	 */
	private Time beginningTime; 
	/**
	 * End of the time interval
	 */
	private Time endingTime;

	public StationComparatorByLeastOccupied(Time beginningTime, Time endingTime) {
		super();
		this.beginningTime = beginningTime;
		this.endingTime = endingTime;
	}
	/**
	 * @param station1
	 * 		First station to be compared
	 * @param station2
	 * 		Second station to be compared
	 * @return 1 station1 is less occupied than station2, -1 is station1 is more occupied than station 2, 0 if station1 is as occupied as station2
	 */
	@Override
	public int compare(Station station1, Station station2) {
		if(station1.computeAverageOccupationRate(this.beginningTime, this.endingTime)<station2.computeAverageOccupationRate(this.beginningTime, this.endingTime)) {return 1;}
		if(station1.computeAverageOccupationRate(this.beginningTime, this.endingTime)>station1.computeAverageOccupationRate(this.beginningTime, this.endingTime)) {return -1;}
		else {return 0;}
	}
	
}
