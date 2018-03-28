package myVelibCore.planningPolicyPackage;
import java.util.*;

import myVelibCore.stationPackage.Station;
import myVelibCore.exceptions.BadSpeedSelectionException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.RecalculatePathFailedException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.GPSLocation;
public class ShortestPath implements PlanningPolicy{
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
	public Station[] chooseStations(GPSLocation start, GPSLocation destination, String bycicleType, Network network) throws PlanningPathFailedException {
	try {
		double shortestDistance = 637800000;
		Station startingStation = null;
		Station destinationStation = null;
		ArrayList<Station> availableStartingStations = new ArrayList<Station>();
		ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
		for(Station s : network.getAllStations()) {
			if (s.getStationBikeCounters().isThereAny(bycicleType) && s.isOn()) {
				availableStartingStations.add(s);
			}
			
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availableDestinationStations.add(s);
			}
		}
		if (availableStartingStations.size()==0) {throw new NoStartingStationAvailableException();}
		if (availableDestinationStations.size()==0 || (availableDestinationStations.size()==1 && availableStartingStations.size()==1 && availableStartingStations.size()==1 && availableDestinationStations.get(0)==availableStartingStations.get(0))) {throw new NoDestinationStationAvailableException();}	
		for(Station stationDepart : availableStartingStations) {
			for(Station stationArrivee : availableDestinationStations) {
				double currentDistance = start.getDistance(stationDepart.getGpsLocation()) + stationDepart.getGpsLocation().getDistance(stationArrivee.getGpsLocation())+ destination.getDistance(stationArrivee.getGpsLocation());
				if (currentDistance < shortestDistance) {
					shortestDistance = currentDistance;
					startingStation = stationDepart;
					destinationStation = stationArrivee;
				}
			}
		}
		Station stations[] = {startingStation, destinationStation};
		return stations;
	}
	catch (NoStartingStationAvailableException | NoDestinationStationAvailableException e) {
			throw new PlanningPathFailedException("Shortest Path",e);
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
		Station destinationStation = null;
		double shortestDistance = 637800000;
		ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
		for(Station s : network.getAllStations()) {		
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availableDestinationStations.add(s);
			}
		}
		if (availableDestinationStations.size()==0) {throw new NoDestinationStationAvailableException();}
		for(Station stationArrivee : availableDestinationStations) {
		double currentDistance = start.getDistance(stationArrivee.getGpsLocation()) + stationArrivee.getGpsLocation().getDistance(destination);
			if (currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				destinationStation = stationArrivee;
			}
		}
		return destinationStation;
	}
	catch (NoDestinationStationAvailableException e) {
		throw new RecalculatePathFailedException("Shortest Path", e);
	}
	}
}
