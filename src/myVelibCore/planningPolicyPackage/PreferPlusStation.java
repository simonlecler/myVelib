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
 * <b>Prefer plus stations planning policy</b>
 * <p>This belong to the Strategy Pattern Planning policy of a planning ride
 * <p>With this policy the destination station should be a \plus"
 * station (given a plus station no further away than 10% of the distance of the closest
 * station to the destination location exists). If no such a \plus" station exists then this
 * policy behaves as in the avoid plus station policy 
 * @see AvoidPlusStation
 * @author Simon Lecler
 */
public class PreferPlusStation implements PlanningPolicy{
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
	public Station[] chooseStations(GPSLocation start, GPSLocation destination,String bycicleType, Network network) throws PlanningPathFailedException {
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
		Station closestDestinationStation = null;
		for(Station s : network.getAllStations()) {
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availableDestinationStations.add(s);
			}
		}
		if (availableDestinationStations.size()==0 || (availableDestinationStations.size()==1 && availableStartingStations.size()==1 && availableDestinationStations.get(0)==availableStartingStations.get(0))) {throw new NoDestinationStationAvailableException();}
		ArrayList<Station> availablePlusDestinationStations = new ArrayList<Station>();
		ArrayList<Station> availableStandardDestinationStations = new ArrayList<Station>();
	
		for(Station s : network.getAListOfStationType("Plus")) {
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availablePlusDestinationStations.add(s);
			}
		}
		
		for(Station s : network.getAListOfStationType("Standard")) {
			if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
				availableStandardDestinationStations.add(s);
			}
		}
		
		if(availablePlusDestinationStations.isEmpty() && (availableStandardDestinationStations.isEmpty() == false)) {
			closestDestinationStation = availableStandardDestinationStations.get(0);
			for(Station station : availableStandardDestinationStations) {
				if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestDestinationStation.getGpsLocation())) {
					closestDestinationStation = station;
				}
			}
		}
		else if(availablePlusDestinationStations.isEmpty() == false && availableStandardDestinationStations.isEmpty()) {
			closestDestinationStation = availablePlusDestinationStations.get(0);
			for(Station station : availablePlusDestinationStations) {
				if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestDestinationStation.getGpsLocation())) {
					closestDestinationStation = station;
				}
			}
		}
		else if(availablePlusDestinationStations.isEmpty()==false && availableStandardDestinationStations.isEmpty()==false) {
			Station closestPlusStation = availablePlusDestinationStations.get(0);
			for(Station station : availablePlusDestinationStations) {
				if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestPlusStation.getGpsLocation())) {
					closestPlusStation = station;
				}
			}
			
			Station closestStandardStation = availableStandardDestinationStations.get(0);
			for(Station station : availableStandardDestinationStations) {
				if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestStandardStation.getGpsLocation())) {
					closestStandardStation = station;
				}
			}
			if (destination.getDistance(closestPlusStation.getGpsLocation())> 1.1 * destination.getDistance(closestStandardStation.getGpsLocation())) {
				closestDestinationStation = closestStandardStation;
				
			}
			else {
				closestDestinationStation =closestPlusStation;
			}
		}
		
		Station[] stations = {closestStartingStation,closestDestinationStation};
		return stations;
	}
	catch (UnimplementedSubclassWithInputException | NoStartingStationAvailableException | NoDestinationStationAvailableException e) {
			throw new PlanningPathFailedException("Prefer Plus",e);
	}
}

	@Override
	public Station recalculateWhenRiding(GPSLocation start, GPSLocation destination, String bycicleType, Network network) throws  RecalculatePathFailedException{
		
		try {
			
			ArrayList<Station> availableDestinationStations = new ArrayList<Station>();
			ArrayList<Station> availablePlusDestinationStations = new ArrayList<Station>();
			ArrayList<Station> availableStandardDestinationStations = new ArrayList<Station>();
			Station closestDestinationStation = null;
			for(Station s : network.getAllStations()) {
				if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
					availableDestinationStations.add(s);
				}
			}
			if (availableDestinationStations.size()==0) {throw new NoDestinationStationAvailableException();}
			
			for(Station s : network.getAListOfStationType("Plus")) {
				if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
					availablePlusDestinationStations.add(s);
				}
			}
			
			for(Station s : network.getAListOfStationType("Standard")) {
				if (s.getStationBikeCounters().getFreeSlots()>0 && s.isOn()) {
					availableStandardDestinationStations.add(s);
				}
			}
			
			if(availablePlusDestinationStations.isEmpty() && (availableStandardDestinationStations.isEmpty() == false)) {
				closestDestinationStation = availableStandardDestinationStations.get(0);
				for(Station station : availableStandardDestinationStations) {
					if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestDestinationStation.getGpsLocation())) {
						closestDestinationStation = station;
					}
				}
			}
			else if(availablePlusDestinationStations.isEmpty() == false && availableStandardDestinationStations.isEmpty()) {
				closestDestinationStation = availablePlusDestinationStations.get(0);
				for(Station station : availablePlusDestinationStations) {
					if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestDestinationStation.getGpsLocation())) {
						closestDestinationStation = station;
					}
				}
			}
			else if(availablePlusDestinationStations.isEmpty()==false && availableStandardDestinationStations.isEmpty()==false) {
				Station closestPlusStation = availablePlusDestinationStations.get(0);
				for(Station station : availablePlusDestinationStations) {
					if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestPlusStation.getGpsLocation())) {
						closestPlusStation = station;
					}
				}
				
				Station closestStandardStation = availableStandardDestinationStations.get(0);
				for(Station station : availableStandardDestinationStations) {
					if(destination.getDistance(station.getGpsLocation())<destination.getDistance(closestStandardStation.getGpsLocation())) {
						closestStandardStation = station;
					}
				}
				if (destination.getDistance(closestPlusStation.getGpsLocation())> 1.1 * destination.getDistance(closestStandardStation.getGpsLocation())) {
					closestDestinationStation = closestStandardStation;
					
				}
				else {
					closestDestinationStation =closestPlusStation;
				}
			}
			
			return closestDestinationStation;
		}
		catch (UnimplementedSubclassWithInputException | NoDestinationStationAvailableException e) {
			throw new RecalculatePathFailedException("Prefer Plus",e);
	}
	}
	
}

