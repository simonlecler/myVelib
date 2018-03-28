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
/**
 * <b>Uniformity planning policy</b>
 * <p>This belong to the Strategy Pattern Planning policy of a planning ride
 * <p>Preservation of uniformity of bicycles distribution amongst stations: with
 * this policy the choice of the source and destination station is affected by the number
 * of available bikes (at source station) and free slots (at destination).
 * @author Simon Lecler
 */
public class UniformityPolicy implements PlanningPolicy{
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
	public Station[] chooseStations(GPSLocation start, GPSLocation destination, String bycicleType,Network network) throws PlanningPathFailedException {
	try {
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
		Station startingStation = availableStartingStations.get(0);
		Station destinationStation = availableDestinationStations.get(0);
		for(Station stationDepart : availableStartingStations) {
			if(start.getDistance(stationDepart.getGpsLocation())<start.getDistance(startingStation.getGpsLocation())) {
				startingStation = stationDepart;
			}
		}
		for(Station stationDepart : availableStartingStations) {
			if(start.getDistance(stationDepart.getGpsLocation())<1.05 * start.getDistance(startingStation.getGpsLocation())&& stationDepart.getStationBikeCounters().howMany(bycicleType) > startingStation.getStationBikeCounters().howMany(bycicleType)) {
				startingStation = stationDepart;
			}
			
		}
		for(Station stationArrivee : availableDestinationStations) {
			if(destination.getDistance(stationArrivee.getGpsLocation())<destination.getDistance(destinationStation.getGpsLocation())) {
				destinationStation = stationArrivee;
			}
		}
		for(Station stationArrivee : availableDestinationStations) {
			if(destination.getDistance(stationArrivee.getGpsLocation())<1.05 * destination.getDistance(destinationStation.getGpsLocation())&& stationArrivee.getStationBikeCounters().getFreeSlots() > destinationStation.getStationBikeCounters().getFreeSlots()) {
				destinationStation = stationArrivee;
			}
					
		}
		Station stations[] = {startingStation, destinationStation};
		return stations;
	}
	catch (UnimplementedSubclassWithInputException | NoStartingStationAvailableException | NoDestinationStationAvailableException e) {
			throw new PlanningPathFailedException("Uniformity Policy",e);
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
	public Station recalculateWhenRiding(GPSLocation start, GPSLocation destination, String bycicleType, Network network) throws  RecalculatePathFailedException {
		try {
			ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
			
			for(Station s : network.getAllStations()) {
				if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
					availableDestinationStations.add(s);
				}
			}
			if (availableDestinationStations.size()==0) {throw new NoDestinationStationAvailableException();}	
			Station destinationStation = availableDestinationStations.get(0);
			for(Station stationArrivee : availableDestinationStations) {
				if(destination.getDistance(stationArrivee.getGpsLocation())<destination.getDistance(destinationStation.getGpsLocation())) {
					destinationStation = stationArrivee;
				}
			}
			for(Station stationArrivee : availableDestinationStations) {
				if(destination.getDistance(stationArrivee.getGpsLocation())<1.05 * destination.getDistance(destinationStation.getGpsLocation())&& stationArrivee.getStationBikeCounters().getFreeSlots() > destinationStation.getStationBikeCounters().getFreeSlots()) {
					destinationStation = stationArrivee;
				}
						
			}
			return destinationStation;
		}
		catch (NoDestinationStationAvailableException e) {
			throw new RecalculatePathFailedException("Uniformity Policy",e);
		}
	}
}
