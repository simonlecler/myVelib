package myVelibCore.stationPackage;

import java.util.ArrayList;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.ComputeCostImpossibleException;
import myVelibCore.exceptions.NoBikeToReturnException;
import myVelibCore.exceptions.NoBycicleAvailableException;
import myVelibCore.exceptions.RemoveBikeFailException;
import myVelibCore.exceptions.RentBikeFailException;
import myVelibCore.exceptions.RentTwoBikeException;
import myVelibCore.exceptions.ReturnBikeFailException;
import myVelibCore.exceptions.SlotStatusFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.exceptions.StationOfflineException;
import myVelibCore.exceptions.UncompatibleNetworkException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.stationPackage.stationsStatitics.StationStatistics;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.*;
import myVelibCore.utilities.Time;
/**
 * This class defines the type Station
 * @author Simon Lecler & Edouard Benauw
 *
 */
public abstract class Station {
	/**
	 * The unique instance of StationSortedByType
	 * @see Network
	 */
	private Network network;
	/**
	 * The list of all the parking slots of the station
	 */
	protected ArrayList<ParkingSlot> slots = new ArrayList<ParkingSlot>();
	/**
	 * ID of the station
	 */
	private final int id;
	/**
	 * Counter of bikes for this station
	 */
	private StationBikeCounters stationBikeCounters;
	/**
	 * GPS coordinates of the station
	 */
	private GPSLocation gpsLocation;
	
	private boolean status =true; //true if the station is online
	/**
	 * Credit given to the user card when returining a bike
	 */
	private int	creditGivenWhenReturning;
	/**
	 * Instance of the class computing statistics for this station
	 */
	private StationStatistics stationStatitics = new StationStatistics();
	/**
	 * List of the users planning to rent a bike of this station
	 */
	private ArrayList<User> observersDeparture = new ArrayList<User>();
	/**
	 * List of the users planning to return a bike in this station
	 */
	private ArrayList<User> observersDestination = new ArrayList<User>();
	/**
	 * Parameter of the observer pattern for notifying users
	 */
	private boolean changed;
	
	
	/**
	 * Constructor of Station
	 * 
	 * @param gpsLocation
	 * 		The GPS coordinates of the station
	 *
	 *<p> It instantiates a StationBikeCounter and add this station to the StationSortedByType object. It instantiates also a list of parking slots.
	 *</p>
	 * @throws UnimplementedSubclassWithoutInputException 
	 */
	
	public Station(GPSLocation gpsLocation, Network network) throws UnimplementedSubclassWithoutInputException {
		super();
		this.id = IDGenerator.getInstance().getNextID();
		this.gpsLocation=gpsLocation;
		this.network=network;
		network.addStation(this);
		this.stationBikeCounters = new StationBikeCounters();
		this.slots = new ArrayList<ParkingSlot>();
	}
	
	public boolean isOn() {
		return this.status;
	}
	
	public void turnOff() throws SlotStatusFailException {
		this.status=false;
		for (ParkingSlot s : slots) {
			s.stationIsTurnedOff();
		}
		this.changed=true;
		this.notifyObserversDeparture();
		this.notifyObserversDestination();
	}
	
	public void turnOn() throws SlotStatusFailException {
		this.status=true;
		for (ParkingSlot s : slots) {
			s.stationIsTurnedOn();
		}
		this.changed=true;
		this.notifyObserversDeparture();
		this.notifyObserversDestination();
	}
	
	/**
	 * Add a parking slot to the station
	 */
	public void addParkingSlot() {
		ParkingSlot newPS = new ParkingSlot(this);
		this.slots.add(newPS);
	}
	
	
	public Network getNetwork() {
		return this.network;
	}
	
	public ArrayList<ParkingSlot> getSlots() {
		return slots;
	}

	public int getId() {
		return id;
	}
	
	public StationBikeCounters getStationBikeCounters() {
		return stationBikeCounters;
	}
	
	public GPSLocation getGpsLocation() {
		return gpsLocation;
	}
	
	

	public StationStatistics getStationStatitics() {
		return stationStatitics;
	}


	public void setStationStatitics(StationStatistics stationStatitics) {
		this.stationStatitics = stationStatitics;
	}
	
	public void addBike(Bycicle bycicle) throws AddBikeFailException {
		this.stationBikeCounters.addBike(bycicle, getSlots());
	}

/**
 * This class  performs the renting operation. A user choose a type of bicycle. If a bicycle is available it is removed from the slot that become free. Else an error message is displayed. The users planning to use this station are notified if after renting there is no more bicycle
 * @param user
 * 		The user renting a bike
 * @param bycicleType
 * 		The type of bicycle wanted by the user
 * @throws RentBikeFailException 
 * @throws NoBycicleAvailableException 
 * 
 */
	public void rentABike(User user, String bycicleType) throws RentBikeFailException {
	
		try {
			if (!status) {
				throw new StationOfflineException();
				
			}
			else if (this.getStationBikeCounters().isThereAny(bycicleType) == false) {
				throw new NoBycicleAvailableException(bycicleType);
				
			}
			else if (user.getNetwork()!=this.network) {
				throw new UncompatibleNetworkException("User","Network");
		
			}
			else if (user.getBycicle()!=null) {
				throw new RentTwoBikeException();
		
			}
			
			else{
				
				Time.lock.lock();
				
				StationRemovingBycicle rentingBycicle;
				rentingBycicle = this.stationBikeCounters.removeBike(bycicleType,this.slots);
				this.unregisterObserverDeparture(user);
				user.isDeparted(rentingBycicle.getBycicle());
				
				this.stationStatitics.addRentOperation(user, Time.getCurrentTime(), rentingBycicle.getSlot());
				
				if(this.stationBikeCounters.isThereAnyBycicle()==false) {
					this.changed=true;
					this.notifyObserversDeparture();
				
				}
			
				Time.lock.unlock();
				
			}
		}
		catch(RemoveBikeFailException | RentTwoBikeException | UncompatibleNetworkException | StationOfflineException | NoBycicleAvailableException e) {
			throw new RentBikeFailException(e);
		}
		
	}
	/**
	 * This class  performs the returning operation. If a slot is available the bike is put on it and the slot's status changes. Else an error message is displayed. This method displays the cost of the ride to the user. The users planning to use this station are notified if after returning there is no more available slots.
	 * @param user
	 * 		The user who returns the bicycle
	 * @throws ReturnBikeFailException 
	 * @throws StationFullException 
	 */
	public void returnABike(User user) throws ReturnBikeFailException, StationFullException {
		
		try {
			if (!status) {
				throw new StationOfflineException();
			}
			else if(this.getStationBikeCounters().isThereFreeSlots() == false) {
				throw new StationFullException();
			}
			else if (user.getNetwork()!=this.network) {
				throw new UncompatibleNetworkException("User","Network");
			}
			else if (user.getBycicle()==null) {
				throw new NoBikeToReturnException();
			}
			else {
				Time.lock.lock();
				ParkingSlot slot = this.stationBikeCounters.addBike(user.getBycicle(), slots);
				int cost = user.computeMyRideCost(user.getLastRentTime().timeDifferenceBetween(Time.getCurrentTime()));
				try{user.getUserCard().increaseBalance(creditGivenWhenReturning);}
				catch(CardNoneNoBalanceException e) {}//Nothing to do ?
				System.out.println("You pay :" + cost + "euros");
				this.stationStatitics.addReturnOperation(user, Time.getCurrentTime(), slot);
				this.unregisterObserverDestination(user);
				user.isArrived(cost);
				if(this.stationBikeCounters.isThereFreeSlots()==false) {
					this.changed=true;
					this.notifyObserversDestination();
				}
				Time.lock.unlock();
		
			}
		}
		catch(NoBikeToReturnException | AddBikeFailException | ComputeCostImpossibleException | UncompatibleNetworkException | StationOfflineException | StationFullException e) {
			throw new ReturnBikeFailException(e);
		}
		
	}
	/**
	 * This method register the users planning to rent a bike in this station
	 * @param observer
	 * 		The user that is riding
	 */
	public void registerObserverDeparture(User observer) {
		observersDeparture.add(observer);
	}
	/**
	 * This method register the users who have rented their bike or changed their plan
	 * @param observer
	 * 		The user that is riding
	 */
	public void unregisterObserverDeparture(User observer) {
		observersDeparture.remove(observer);
	}
	/**
	 * This method register the users planning to return a bike in this station
	 * @param observer
	 * 		The user that is riding
	 */
	public void registerObserverDestination(User observer) {
		observersDestination.add(observer);
	}
	/**
	 * This method register the users who have returned their bike or changed their plan
	 * @param observer
	 * 		The user that is riding
	 */
	public void unregisterObserverDestination(User observer) {
		observersDestination.remove(observer);
	}
	/**
	 * Notifies the users that want to rent a bike in this station, if the station is no more available
	 * @throws BadInstantiationException 
	 */
	public void notifyObserversDeparture()  {
		if (this.changed) {
			for (User ob : this.observersDeparture) {
				this.getStationBikeCounters().isThereAnyBycicle();
				ob.updateDeparture(this.getStationBikeCounters().isThereAnyBycicle(),this.status);
				this.changed = false;
			}
		}
	}
	/**
	 * Notifies the users that want to return a bike in this station, if the station is no more available
	 * @throws BadInstantiationException 
	 */
	public void notifyObserversDestination() {
		if (this.changed) {
			for (User ob : observersDestination) {
				ob.updateDestination(this.getStationBikeCounters().isThereFreeSlots(),this.status);
				this.changed = false;
			}
		}
	}
	/**
	 * 
	 * @param beginningTime
	 * 		Beginning time of the interval during which we want to compute statistics
	 * @param endingTime
	 *		Ending time of the interval during which we want to compute statistics
	 * @return The average occupation rate of this station
	 */
	public double computeAverageOccupationRate(Time beginningTime, Time endingTime) {
		return this.stationStatitics.computeAverageOccupationRate(this.slots, beginningTime, endingTime);
	}

	@Override
	public String toString() {
		return "Station " + ", id=" + id + ", gpsLocation=" + gpsLocation + "]";
	}
	
	public void displayStatistics() {
		System.out.println("Total number of operations on the station : " +this.stationStatitics.getAllOperationsNumber());
		System.out.println("Total number of renting operations on the station : " +this.stationStatitics.getRentOperationsNumber());
		System.out.println("Total number of returning operations on the station : " +this.stationStatitics.getReturnOperationsNumber());
		System.out.println("Average Occupation Rate on this station : " +this.computeAverageOccupationRate(Time.getOriginalTime(), Time.getCurrentTime()));
	}
	
	public void displayType() {
		if (this instanceof StationStandard) {System.out.println("Type of station : Standard");}
		else if (this instanceof StationPlus) {System.out.println("Type of station : Plus");}
		else {System.out.println("Type of station : not implemented !!!");}
	}

	public void display() {
		System.out.println("Station ID : " + this.id);
		System.out.println("Is online" + this.status);
		this.displayType();
		this.displayStatistics();
		for (ParkingSlot s : slots) {
			s.display();
		}
	}
	
	public void displayOnlyName() {
		System.out.println("Station ID : " + this.id);
		System.out.println("Is online" + this.status);
		this.displayType();
	}

}
	

