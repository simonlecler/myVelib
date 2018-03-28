package myVelibCore.stationPackage;

import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.utilities.GPSLocation;

/**
 * The standard station type
 * @author Simon Lecler
 *
 */
public class StationStandard extends Station{
	/**
	 * Credit given to the user card when he returns a bike
	 */
	private final int creditGivenWhenReturning=0;
	/**
	 * Constructor of the standard station
	 * @param gpsLocation
	 * 		The GPS Coordinates of the station
	 * @throws UnimplementedSubclassWithoutInputException 
	 */
	public StationStandard(GPSLocation gpsLocation, Network network) throws UnimplementedSubclassWithoutInputException {
		super(gpsLocation,network);
	}

	

}
