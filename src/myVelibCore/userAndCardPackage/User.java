package myVelibCore.userAndCardPackage;


import java.util.Scanner;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleSpeeds;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.BadSpeedSelectionException;
import myVelibCore.exceptions.NoBikeToComputeCostException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.PlanningRideFailException;
import myVelibCore.exceptions.RecalculatePathFailedException;
import myVelibCore.exceptions.RentBikeFailException;
import myVelibCore.exceptions.ReturnBikeFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.exceptions.UpdateRideFailException;
import myVelibCore.exceptions.ComputeCostImpossibleException;
import myVelibCore.planningPolicyPackage.PolicyChoice;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;
import myVelibCore.utilities.IDGenerator;
import myVelibCore.utilities.Time;
/**
 * This class defines users
 * @author Simon Lecler & Edouard Benauw
 *
 */
public class User implements Runnable{
	/**
	 * Name of the user
	 */
	private String name;
	/**
	 * Unique ID of the user
	 */
	private final int id;
	/**
	 * The user belongs to this network
	 */
	private Network network;
	/**
	 * GPS coordinates of the user
	 */
	private GPSLocation gpsLocation;
	/**
	 * Card owned by the user. By default the user doesn't have any card (CardNone)
	 */
	private Card userCard = new CardNone();
	/**
	 * Bicycle used by the user (if any)
	 */
	private Bycicle bycicle;
	/**
	 * Last time the user rented a bike
	 */
	private Time lastRentTime;
	/**
	 * This object stores the statistics regarding the user
	 */
	private UserStatitics userStatitics;
	/**
	 * This object stores the last inputs of the user (bicycle, policy, destination ect...)
	 */
	private UserLastInput userLastInput;
	/**
	 * When a user plans a ride, his destination station is stored there
	 */
	private Station currentDestinationStation;
	/**
	 * When a user plans a ride, his departure station is stored there
	 */
	private Station currentDepartureStation;
	/**
	 * When a user plans a ride, the time when he will rent the bike is stored there
	 * It is computed considering the distance and the mean speed of the user
	 */
	private Time timeWhenRentingBike = null;
	/**
	 * When a user plans a ride, the time when he will rent the bike is stored there
	 * It is computed considering the distance and the mean speed of the user
	 */
	private Time timeWhenReturningBike = null;
	/**
	 * When a user plans a ride, the time when he will reach his destination is stored there
	 * It is computed considering the distance and the mean speed of the user
	 */
	private Time timeToDestination = null;
	/**
	 * It is set to true when the user plans a ride
	 */
	private boolean isPlanningARide=false;
	
	/**
	 * Constructor with parameters
	 * @param name
	 * 		Name of the user
	 * @param network
	 * 		Network of the user
	 */
	public User(String name,Network network) {
		super();
		this.name = name;
		this.id = IDGenerator.getInstance().getNextID();
		this.network=network;
		this.network.addUser(this);
		this.userStatitics= new UserStatitics();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public GPSLocation getGpsLocation() {
		return gpsLocation;
	}
	
	public void setGpsLocation(GPSLocation gpsLocation) {
		this.gpsLocation = gpsLocation;
	}
	
	public Card getUserCard() {
		return userCard;
	}
	
	public void setUserCard(Card userCard) {
		this.userCard = userCard;
	}
	
	public Bycicle getBycicle() {
		return bycicle;
	}

	public void setBycicle(Bycicle bycicle) {
		this.bycicle = bycicle;
	}
	/**
	 * Computes the cost of the ride when a user returns a bicycle
	 * @param minutes
	 * 		Duration of the ride
	 * @return The price that the user has to pay
	 * @throws ComputeCostImpossibleException
	 */
	public int computeMyRideCost (int minutes) throws ComputeCostImpossibleException {
		try {
			CardVisitorConcrete1 visitor = new CardVisitorConcrete1();
			if (this.bycicle==null) {
				throw new NoBikeToComputeCostException();
			}
			else{
				return (userCard.accept(visitor,this.bycicle,minutes));
			}
		}
		catch(NoBikeToComputeCostException | UnimplementedSubclassWithoutInputException e) {
			throw new ComputeCostImpossibleException(e);
		}
	}

	
	
	public Time getLastRentTime() {
		return lastRentTime;
	}

	public void setLastRentTime(Time lastRentTime) {
		this.lastRentTime = lastRentTime;
	}
	
	
	public Time getTimeWhenRentingBike() {
		return timeWhenRentingBike;
	}

	public Time getTimeWhenReturningBike() {
		return timeWhenReturningBike;
	}

	public Station getCurrentDestinationStation() {
		return currentDestinationStation;
	}

	public void setCurrentDestinationStation(Station currentDestinationStation) {
		this.currentDestinationStation = currentDestinationStation;
	}

	public Station getCurrentDepartureStation() {
		return currentDepartureStation;
	}

	public void setCurrentDepartureStation(Station currentDepartureStation) {
		this.currentDepartureStation = currentDepartureStation;
	}
	
	public UserStatitics getUserStatitics() {
		return this.userStatitics;
	}
	
	public Network getNetwork() {
		return this.network;
	}
	/**
	 * This methods sets some user's attributes when a user starts riding
	 * @param rentedBycicle
	 * 		The bicycle rented by the user
	 */
	public void isDeparted(Bycicle rentedBycicle) {
		
		this.currentDepartureStation = null;
		this.bycicle = rentedBycicle;
		this.timeWhenRentingBike = null;
		this.lastRentTime=Time.getCurrentTime();
		this.userStatitics.increaseNumberOfRidesBy(1);
		
	}
	/**
	 * This method sets parameters when the user reached his destination
	 * @param costOfRide
	 */
	public void isArrived(int costOfRide){
		this.currentDestinationStation = null;
		this.bycicle=null;
		this.timeWhenReturningBike = null;
		this.isPlanningARide=false;
		this.userStatitics.increaseTotalTimeSpentOnABikeBy(Time.timeDifference(Time.getCurrentTime(),this.lastRentTime));
		this.userStatitics.increaseTotalAmountOfChargesBy(costOfRide);
	}
	/**
	 * This method allows the user to plan a ride
	 * @param destination
	 * 		GPSCoordinates of the user's destination
	 * @param policy
	 * 		PlanningPolicy chosen by the user
	 * @param bycicleType
	 * 		Type of bicycle chosen by the user
	 * @throws PlanningRideFailException
	 */
	public void planningRide(GPSLocation destination, String policy, String bycicleType) throws PlanningRideFailException {
		try {
			this.userLastInput= new UserLastInput(destination,policy,bycicleType);
			Station[] stations;
			stations = PolicyChoice.getPolicy(policy).chooseStations(this.getGpsLocation(), destination, bycicleType,this.network);
			try {
				this.currentDepartureStation = stations[0];
				stations[0].registerObserverDeparture(this);
				this.currentDestinationStation = stations[1];
				stations[1].registerObserverDestination(this);
				double timeDouble =60*(this.getGpsLocation().getDistance(currentDepartureStation.getGpsLocation()))/(BycicleSpeeds.getSpeed("Foot"));
				double timeDouble1 =60*(this.currentDepartureStation.getGpsLocation().getDistance(currentDestinationStation.getGpsLocation()))/(BycicleSpeeds.getSpeed(bycicleType));
				double timeDouble2 = 60*(this.currentDestinationStation.getGpsLocation().getDistance(destination))/(BycicleSpeeds.getSpeed("foot"));
				this.timeWhenRentingBike = Time.operationTime((int)timeDouble);
				this.timeWhenReturningBike = Time.operationTime((int) timeDouble1);
				this.timeToDestination = Time.operationTime((int) timeDouble2);
			}
			catch(BadSpeedSelectionException e) {System.out.println("NOT SUPPOSED TO HAPPEN" + e.getMessage());}
			
			this.isPlanningARide = true;
		}
		catch(PlanningPathFailedException | BadInstantiationException e) {
			throw new PlanningRideFailException(e);
		}
	}
	
	
	
	

	/**
	 * If the departure station becomes unavailable, the user can find another path. If so, this method computes the new departure station
	 * @param isThereAnyBicycle
	 * 		True is there is bicycles in the station, false either
	 * @param stationStatus
	 *		True if the station is available, false either
	 */		
	public void updateDeparture (boolean isThereAnyBicycle,boolean stationStatus){
		if (!isThereAnyBicycle || !stationStatus) {
			try {
				System.out.println("Departure unavailable, do you want to find another destination ?\r\n");
				Scanner sc = new Scanner(System.in);
				String reponse = sc.nextLine();
				sc.close();
					if (reponse.equalsIgnoreCase("Yes")){
						try {
							planningRide(userLastInput.getLastWantedDestination(), userLastInput.getLastWantedPolicy(), userLastInput.getLastWantedBycicle());
						}
						catch(PlanningRideFailException e) {
							throw new UpdateRideFailException(e);
						}
					}
					else if (reponse.equalsIgnoreCase("No")) {
						System.out.println("\r\nAs you wish !\r\n");
					}
					else {
						System.out.println("\r\nBad input. You must answer Yes or No\r\n");
						this.updateDeparture(isThereAnyBicycle,stationStatus);
					}
			}
			catch(UpdateRideFailException e) {
				System.out.println("\r\nImpossible to Update the path ! Would you like to see the log ?\r\n");
				Scanner sc2 = new Scanner(System.in);
				String reponse2 = sc2.nextLine();
				if (reponse2.equalsIgnoreCase("Yes")){System.out.println(e.getMessage());}
				else {System.out.println("No log printed\r\n");}
				System.out.println("Try to update the ride again ?\r\n");
				reponse2 = sc2.nextLine();
				sc2.close();
				if (reponse2.equalsIgnoreCase("Yes")){
					this.updateDeparture(isThereAnyBicycle,stationStatus);
				}
				else {System.out.println("Ok ! Sorry for the unconvenience ! r\n");
					//Cleanup code
				 		currentDestinationStation=null;
				 		currentDepartureStation=null;
				 		timeWhenRentingBike = null;
				 		timeWhenReturningBike = null;
				 		timeToDestination = null;
				 		isPlanningARide=false;
				}
				
			}
		}
	}
	

	/**
	 * If the departure station becomes unavailable, the user can find another path. If so, this method computes the new destination station
	 * @param isThereAnyBicycle
	 * 		True is there is bicycles in the station, false either
	 * @param stationStatus
	 *		True if the station is available, false either
	 */
	public void updateDestination (boolean isThereFreeSlots,boolean stationStatus) {
		if(!isThereFreeSlots || !stationStatus) {
			try {
				System.out.println("Destination unavailable, do you want to find another destination ?");
				Scanner sc = new Scanner(System.in);
				String reponse = sc.nextLine();
				sc.close();
				if (reponse.equalsIgnoreCase("Yes")) {
					if(this.getCurrentDepartureStation()==null) {
						try {
							Station station = PolicyChoice.getPolicy(this.userLastInput.getLastWantedPolicy()).recalculateWhenRiding(this.getGpsLocation(), this.userLastInput.getLastWantedDestination(), this.userLastInput.getLastWantedBycicle(),this.network);
							double timeDouble1 =60*(this.currentDepartureStation.getGpsLocation().getDistance(currentDestinationStation.getGpsLocation()))/(BycicleSpeeds.getSpeed(this.userLastInput.getLastWantedBycicle()));
							double timeDouble2 = 60*(this.currentDestinationStation.getGpsLocation().getDistance(this.userLastInput.getLastWantedDestination()))/(BycicleSpeeds.getSpeed("Foot"));
							this.currentDestinationStation = station;
							this.currentDestinationStation.registerObserverDeparture(this);
							this.timeWhenReturningBike = Time.operationTime((int) timeDouble1);
							this.timeToDestination = Time.operationTime((int) timeDouble2);
						}
						catch(BadSpeedSelectionException | RecalculatePathFailedException | BadInstantiationException e) {
							throw new UpdateRideFailException(e);
						}
						
					}
					else {
						try {
							planningRide(userLastInput.getLastWantedDestination(), userLastInput.getLastWantedPolicy(), userLastInput.getLastWantedBycicle());
						}
						catch(PlanningRideFailException e) {
							throw new UpdateRideFailException(e);
						}
					}
				}
				else if (reponse.equalsIgnoreCase("No")) {
					System.out.println("As you wish !");
				}
				else {
					System.out.println("Bad input. You must answer Yes or No");
					this.updateDestination(isThereFreeSlots,stationStatus);
				}
			}
			catch(UpdateRideFailException e) {
				System.out.println("\r\nImpossible to Update the path ! Would you like to see the log ?\r\n");
				Scanner sc2 = new Scanner(System.in);
				String reponse2 = sc2.nextLine();
				if (reponse2.equalsIgnoreCase("Yes")){System.out.println(e.getMessage());}
				else {System.out.println("No log printed\r\n");}
				System.out.println("Try to update the ride again ?\r\n");
				reponse2 = sc2.nextLine();
				sc2.close();
				if (reponse2.equalsIgnoreCase("Yes")){
					this.updateDestination(isThereFreeSlots,stationStatus);
				}
				else {System.out.println("Ok ! Sorry for the unconvenience ! r\n");
					//Cleanup code
				 		currentDestinationStation=null;
				 		currentDepartureStation=null;
				 		timeWhenRentingBike = null;
				 		timeWhenReturningBike = null;
				 		timeToDestination = null;
				 		isPlanningARide=false;
				}
			}
		}
	}

	/**
	 * 
	 * @return The total spent on a bicycle by the user
	 */
	public int calculateTotalTimeSpentOnABike() {
		if (this.bycicle==null) {
			return(this.userStatitics.getTotalTimeSpentOnABikeWithoutActualRide());
		}
		else {
			return(this.userStatitics.getTotalTimeSpentOnABikeWithoutActualRide()+Time.timeDifference(Time.getCurrentTime(),this.lastRentTime));
		}
	}
	
	public void display() {
		System.out.println("User name : " + this.name);
		System.out.println("User ID : " + this.id);
		this.displayStatistics();
	}
	/**
	 * Shows the user's statistics
	 */
	
	public void displayStatistics() {
		System.out.println("Total number of rides is :" + this.userStatitics.getNumberOfRides());
		System.out.println("Total time spent on a bike is :" + this.calculateTotalTimeSpentOnABike());
		System.out.println("Total amount of charges for riding bike is :" + this.userStatitics.getTotalAmountOfCharges());
		System.out.println("Total time-credit earned is :" + this.userCard.getCardStatitics().getTotalTimeCreditEarned());
	}
	/**
	 * This method simulates the user' ride
	 * @throws BadInstantiationException
	 */
	public void rides() throws BadInstantiationException{
		while(Time.getCurrentTime().isBefore(this.timeWhenRentingBike)) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			this.setGpsLocation(currentDepartureStation.getGpsLocation());
			currentDepartureStation.rentABike(this, this.userLastInput.getLastWantedBycicle());
			
		} catch (RentBikeFailException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}

		
		
		while(Time.getCurrentTime().isBefore(this.timeWhenReturningBike)) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		try {
			this.setGpsLocation(currentDestinationStation.getGpsLocation());
			currentDestinationStation.returnABike(this);
			
			
		} catch (ReturnBikeFailException | StationFullException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
	
		
		
		while(Time.getCurrentTime().isBefore(this.timeToDestination)) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		this.setGpsLocation(this.userLastInput.getLastWantedDestination());
	
		this.isPlanningARide = false;
	
	}
/*	public synchronized void rides() throws BadInstantiationException {
		while(Time.getCurrentTime().isBefore(this.timeWhenRentingBike)){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("ok");
		try {
			currentDepartureStation.rentABike(this, this.userLastInput.getLastWantedBycicle());
		} catch (RentBikeFailException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
		this.setGpsLocation(currentDepartureStation.getGpsLocation());
		while(Time.getCurrentTime().isBefore(this.timeWhenRentingBike)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			currentDestinationStation.returnABike(this);
		} catch (ReturnBikeFailException | StationFullException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
		this.setGpsLocation(currentDestinationStation.getGpsLocation());
		while(Time.getCurrentTime().isBefore(this.timeToDestination)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
		this.setGpsLocation(this.userLastInput.getLastWantedDestination());
		this.isPlanningARide = false;
		
	}
	*/
	
	public boolean isPlanningARide() {
		return isPlanningARide;
	}
	
	public UserLastInput getUserLastInput() {
		return userLastInput;
	}
	
	public Time getTimeToDestination() {
		return timeToDestination;
	}

	@Override
	/**
	 * Implementation of the run method from interface runnable.
	 * During the simulation, if a user plans a ride, the method rides() is called
	 */
	public void run() {
		while(Network.isSimulation_On()) {
			
			if (this.isPlanningARide == true){
				System.out.println("rides");
				try {
					this.rides();
				} catch (BadInstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
}

