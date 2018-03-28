package myVelibCore.planningPolicyPackage;

import java.util.ArrayList;

import myVelibCore.byciclePackage.BycicleSpeeds;
import myVelibCore.exceptions.BadSpeedSelectionException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.RecalculatePathFailedException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.stationPackage.Station;
import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.GPSLocation;
/**
 * <b>Fastest path planning policy</b>
 * <p>This belong to the Strategy Pattern Planning policy of a planning ride
 * <p>Source and destination stations are chosen so that the total time of
the trip, including the walking part of the trip is minimal, assuming that the average
walking speed is 4 Km/h while the average bicycle-riding speed is 15 Km/h, for
mechanical bikes, and 20 Km/h for electrical bikes.
 * @author Simon Lecler
 */
public class FastestPath implements PlanningPolicy {
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
	 public Station[] chooseStations(GPSLocation start, GPSLocation destination, String bycicleType,Network network) throws PlanningPathFailedException{
	 try {
		double shortestTime = 100000000;
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
		if (availableDestinationStations.size()==0 || (availableDestinationStations.size()==1 && availableStartingStations.size()==1 && availableDestinationStations.get(0)==availableStartingStations.get(0))) {throw new NoDestinationStationAvailableException();}	
		for(Station stationDepart : availableStartingStations) {
			for(Station stationArrivee : availableDestinationStations) {
					double currentTime = ((start.getDistance(stationDepart.getGpsLocation()))/BycicleSpeeds.getSpeed("Foot")) + ((stationDepart.getGpsLocation().getDistance(stationArrivee.getGpsLocation()))/BycicleSpeeds.getSpeed(bycicleType))+ ((destination.getDistance(stationArrivee.getGpsLocation()))/BycicleSpeeds.getSpeed("Foot"));
					if (currentTime < shortestTime) {
						shortestTime = currentTime;
						startingStation = stationDepart;
						destinationStation = stationArrivee;
					}
				

				
			}
		}
		Station stations[] = {startingStation, destinationStation};
		return stations;
	}
	catch (BadSpeedSelectionException | NoStartingStationAvailableException | NoDestinationStationAvailableException e) {
		throw new PlanningPathFailedException("Fastest Path",e);
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
	public Station recalculateWhenRiding(GPSLocation start, GPSLocation destination, String bycicleType, Network network) throws RecalculatePathFailedException {
	try	{
		Station destinationStation = null;
		double shortestTime = 637800000;
		ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
		for(Station s : network.getAllStations()) {		
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availableDestinationStations.add(s);
			}
		}
		if (availableDestinationStations.size()==0) {throw new NoDestinationStationAvailableException();}
		for(Station stationArrivee : availableDestinationStations) {
		double currentTime = ((start.getDistance(stationArrivee.getGpsLocation()))/BycicleSpeeds.getSpeed(bycicleType)) + ((stationArrivee.getGpsLocation().getDistance(destination))/BycicleSpeeds.getSpeed("Foot"));
			if (currentTime < shortestTime) {
				shortestTime = currentTime;
				destinationStation = stationArrivee;
			}
		}
		return destinationStation;
	}
	catch (BadSpeedSelectionException | NoDestinationStationAvailableException e) {
		throw new RecalculatePathFailedException("Fatest Path", e);
	}
	}
}
