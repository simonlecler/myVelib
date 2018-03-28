package myVelibCore.planningPolicyPackage;

import java.util.ArrayList;
import myVelibCore.stationPackage.Station;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.RecalculatePathFailedException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.GPSLocation;
// Ajouter exceptions
/**
 * <b>Avoid plus stations planning policy</b>
 * <p>This belong to the Strategy Pattern Planning policy of a planning ride
 * <p>With this policy the destination station cannot be a plus station
 * @author Simon Lecler
 */
public class AvoidPlusStation implements PlanningPolicy{
/**
 * @param start 
 * 		GPS location of the user's starting position of type GPSLocation
 * @param destination
 * 		GPS location of the user's destination of type GPSLocation
 * @param bycicleType
 * 		Type of bycicle chosen by the user, of type String
 * @return An array of stations. The first station is a the starting station, the second is the destination station.
 * @author Simon Lecler
 */
	@Override
	public Station[] chooseStations(GPSLocation start, GPSLocation destination,String bycicleType, Network network) throws PlanningPathFailedException  {
	try {	
		ArrayList<Station> availableStartingStations = new ArrayList<Station>();
		for(Station s : network.getAllStations()) {
			
			if (s.getStationBikeCounters().isThereAny(bycicleType) && s.isOn()) {
			
				availableStartingStations.add(s);
				
			}
			
			
		}
		if (availableStartingStations.size()==0) {throw new NoStartingStationAvailableException();}
		Station closestStartingStation = availableStartingStations.get(0);
		for(Station station : availableStartingStations) {
			if(start.getDistance(station.getGpsLocation())<start.getDistance(closestStartingStation.getGpsLocation())) {
				closestStartingStation = station;
			}
		}

		ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
		for(Station s : network.getAListOfStationType("Standard")) {
			
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availableDestinationStations.add(s);
			
			}
		}
	
		
		if (availableDestinationStations.size()==0 || (availableDestinationStations.size()==1 && availableStartingStations.size()==1 && availableDestinationStations.get(0)==availableStartingStations.get(0))) {throw new NoDestinationStationAvailableException();}	
		Station closestDestinationStation = availableDestinationStations.get(0);
		for(Station station : availableDestinationStations) {
			if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestDestinationStation.getGpsLocation())) {
				closestDestinationStation = station;
			}
		}
		Station[] stations = {closestStartingStation,closestDestinationStation};
	
		return stations;
	}
	catch (UnimplementedSubclassWithInputException | NoStartingStationAvailableException | NoDestinationStationAvailableException e) {
		throw new PlanningPathFailedException("Avoid Plus",e);
	}
	}
	/**
	 * @param start 
	 * 		GPS location of the user's starting position of type GPSLocation
	 * @param destination
	 * 		GPS location of the user's destination of type GPSLocation
	 * @param bycicleType
	 * 		Type of bycicle chosen by the user, of type String
	 * @return The new destination, recomputed during the ride
	 * @author Simon Lecler
	 */
	@Override
	public Station recalculateWhenRiding(GPSLocation start, GPSLocation destination, String bycicleType,Network network) throws  RecalculatePathFailedException {
		try {
			ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
			for(Station s : network.getAListOfStationType("Standard")) {
				
				if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
					availableDestinationStations.add(s);
				
				}
			}
			if (availableDestinationStations.size()==0) {throw new NoDestinationStationAvailableException();}	
			Station closestDestinationStation = availableDestinationStations.get(0);
			for(Station station : availableDestinationStations) {
				if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestDestinationStation.getGpsLocation())) {
					closestDestinationStation = station;
				}
			}
			return(closestDestinationStation);
		}
		
		catch (UnimplementedSubclassWithInputException | NoDestinationStationAvailableException e) {
			throw new RecalculatePathFailedException("Avoid Plus",e);
		
		}
}
}
