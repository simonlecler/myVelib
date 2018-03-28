package myVelibCore.sortStationPackage;

import java.util.Comparator;

import myVelibCore.stationPackage.Station;
/**
 * <b>Comparator for stations according the usage</b>
 * <p>This class allows to compare stations according their usage during a time interval
 * @author Edouard Benauw
 *
 */
public class StationComparatorByMostUsed implements Comparator<Station> {
	
	
	/**
	 * @param station1
	 * 		First station to be compared
	 * @param station2
	 * 		Second station to be compared
	 * @return 1 if station1 is more used than station2, -1 if is station1 is less used than station 2, 0 if station1 is as used as station2
	 */
	@Override
	public int compare(Station station1, Station station2) {
		if(station1.getStationStatitics().getAllOperationsNumber()>station2.getStationStatitics().getAllOperationsNumber()) {return 1;}
		if(station1.getStationStatitics().getAllOperationsNumber()<station2.getStationStatitics().getAllOperationsNumber()) {return -1;}
		else {return 0;}
	}

	
}
