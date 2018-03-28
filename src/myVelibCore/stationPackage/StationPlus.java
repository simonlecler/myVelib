package myVelibCore.stationPackage;

import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.utilities.GPSLocation;
/**
 * <b>Special type of station</b>
 * When a user returns a bike in such a station, he earns 5 minutes of time-credit. It is a way of keeping uniformity among bicycles repartition.
 * @author Edouard Benauw
 *
 */
public class StationPlus extends Station{
	/**
	 * Time-credit earned by the user. It is declared final as it doesn't change
	 */
	private final int creditGivenWhenReturning=5;
	/**
	 * Constructor with parameters inherited from Station
	 * @param gpsLocation
	 * @param network
	 * @throws UnimplementedSubclassWithoutInputException
	 */
	public StationPlus(GPSLocation gpsLocation, Network network) throws UnimplementedSubclassWithoutInputException {
		super(gpsLocation,network);
	}
	

}
