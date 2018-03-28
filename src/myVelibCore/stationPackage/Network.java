package myVelibCore.stationPackage;

import java.util.ArrayList;
import java.util.Comparator;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.BycicleFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.abstractFactoryPattern.StationFactory;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NotEnoughSlotsException;
import myVelibCore.exceptions.UnexistingNetworkNameException;
import myVelibCore.exceptions.UnexistingStationIDException;
import myVelibCore.exceptions.UnexistingUserIDException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.sortStationPackage.StationComparatorByLeastOccupied;
import myVelibCore.sortStationPackage.StationComparatorByMostUsed;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
import myVelibCore.utilities.IDGenerator;
import myVelibCore.utilities.Time;

public class Network {
	private static ArrayList<Network> allNetworks = new ArrayList<Network>();
	private String name;
	private final int id;
	private ArrayList<Station> allStations = new ArrayList<Station>();
	private ArrayList<Station> allStandardStations = new ArrayList<Station>();
	private ArrayList<Station> allPlusStations = new ArrayList<Station>();
	private ArrayList<User> allUsers = new ArrayList<User>();
	private static boolean simulationOn = true;
	

	
	public int getId() {
		return id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Network(String name) throws NetworkNameAlreadyUsedException {
		boolean isNameFree = true;
		for (Network n : allNetworks) {
			if (n.getName().equalsIgnoreCase(name)) {
				isNameFree = false;
			}
		}
		if (isNameFree) {
			this.name=name;
			this.id = IDGenerator.getInstance().getNextID();
			allNetworks.add(this);
		}
		else {throw new NetworkNameAlreadyUsedException(name);}
	}
	
	public static void setupNetwork(String name, int nStations, int nSlots, double sideArea, int nBikes) throws NetworkNameAlreadyUsedException, NotEnoughSlotsException {
		if (nBikes>nSlots) {throw new NotEnoughSlotsException(nBikes,nSlots);}
		Network network = new Network(name);
		double maxLat = GPSLocation.getMaxLatitude(sideArea);
		double maxLong = GPSLocation.getMaxLongitude(sideArea);
		AbstractFactory stationFactory = null;
		//Creating stations
		try {stationFactory = FactoryProducer.getFactory("Station");} 
		catch (BadInstantiationException e) {System.out.println("This is not supposed to happen " + e.getMessage());}
		for (int i=1; i<=nStations; i++) {
			try {stationFactory.getStation(StationFactory.getRandomStationType(), new GPSLocation(Math.random()*maxLat,Math.random()*maxLong), network);}
			catch (BadInstantiationException | FactoryNullException e) {System.out.println("This is not supposed to happen ! "+e.getMessage());}
		//Adding slots
			for (Station s : network.getAllStations()) {
				for (i=1; i<=nSlots; i++) {
					s.addParkingSlot();
				}
			}
		}
		//Adding Bikes
		network.addNBikeRandom(network, nBikes);
	}
	
	
	public String getName() {
		return name;
	}

	public void addUser(User user) {
		this.allUsers.add(user);
	}
	

	
	public void addStation(Station station) throws UnimplementedSubclassWithoutInputException {
		if (station instanceof StationStandard) {this.allStandardStations.add(station);this.allStations.add(station);}
		else if (station instanceof StationPlus) {this.allPlusStations.add(station);this.allStations.add(station);}
		else {throw new UnimplementedSubclassWithoutInputException("Station");}
	}
	
	public ArrayList<Station> getAListOfStationType(String stationType) throws UnimplementedSubclassWithInputException {
		if (stationType.equalsIgnoreCase("All")) {return allStations;}
		else if (stationType.equalsIgnoreCase("Standard")) {return allStandardStations;}
		else if (stationType.equalsIgnoreCase("Plus")) {return allPlusStations;}
		else {throw new UnimplementedSubclassWithInputException("Station",stationType);}
	}
	
	public ArrayList<Station> getAllStations(){return allStations;}
	
	public void addNBikeRandom(Network network,int nBikes) throws NotEnoughSlotsException {
		int totalFreeSlots = 0;
		for (Station s : network.getAllStations()) {
			totalFreeSlots = totalFreeSlots + s.getStationBikeCounters().getFreeSlots();
		}
		if (nBikes>totalFreeSlots) {
			throw new NotEnoughSlotsException(nBikes,totalFreeSlots);
		}
		else {
			int totalBikeAdded = 0;
			AbstractFactory bycicleFactory = null;
			try {bycicleFactory = FactoryProducer.getFactory("Bycicle");}
			catch(Exception e) {System.out.println("This is not supposed to happen ! " + e.getMessage());}
			while(totalBikeAdded<nBikes) {
				for (Station s : network.getAllStations()) {
					if(totalBikeAdded<nBikes && Math.random()>0.5) {
						try {s.addBike(bycicleFactory.getBycicle(BycicleFactory.getRandomBycicleType()));}
						catch(Exception e) {System.out.println("This is not supposed to happen !" + e.getMessage());}
						totalBikeAdded++;
					}
				}
			}
		}
	}
	
	public static Network searchNetworkByName(String name) throws UnexistingNetworkNameException {
		for (Network n : allNetworks) {
			if (n.getName().equalsIgnoreCase(name)) {
				return n;
			}
		}
		throw new UnexistingNetworkNameException(name);
	}
	
	public Station searchStationByID(int id) throws UnexistingStationIDException {
		for (Station s : this.allStations) {
			if (s.getId()== id) {
				return s;
			}
		}
		throw new UnexistingStationIDException(id);
	}
	
	public static Station searchStationByIDAllNetworks(int id) throws UnexistingStationIDException {
		for (Network n : allNetworks) {
			for (Station s : n.allStations) {
				if (s.getId()== id) {
					return s;
				}
			}
		}
		throw new UnexistingStationIDException(id);
	}
	
	public User searchUserByID(int id) throws UnexistingUserIDException {
		for (User u : this.allUsers) {
			if (u.getId()== id) {
				return u;
			}
		}
		throw new UnexistingUserIDException(id);
	}
	
	public static User searchUserByIDAllNetworks(int id) throws UnexistingUserIDException {
		for (Network n : allNetworks) {
			for (User u : n.allUsers) {
				if (u.getId()== id) {
					return u;
				}	
			}
		}
		throw new UnexistingUserIDException(id);
	}
	
	public void sortStationByLeastOccupied(Time beginningTime, Time endingTime){
		allStations.sort(new StationComparatorByLeastOccupied(beginningTime,endingTime));
	}
	
	public void sortStationByMostUsed() {
		allStations.sort(new StationComparatorByMostUsed());
	}

	public static boolean isSimulation_On() {
		return simulationOn;
	}

	public static void setSimulaton_On(boolean thread_laucher) {
		Network.simulationOn = thread_laucher;
	}


	public static ArrayList<Network> getAllNetworks() {
		return allNetworks;
	}


	public static void setAllNetworks(ArrayList<Network> allNetworks) {
		Network.allNetworks = allNetworks;
	}
	
	public void display() {
		System.out.println(this.name);
		System.out.println(this.id);
		for(User u : this.allUsers) {u.display();}
		for(Station s : this.allStations) {s.display();}
	}
	
	public void sortStationBy(String choice) throws BadInstantiationException {
		Comparator<Station> Comparator = null;
		if(choice.equalsIgnoreCase("Least Occupied")) {Comparator = new StationComparatorByLeastOccupied(Time.getOriginalTime(),Time.getCurrentTime());}
		if(choice.equalsIgnoreCase("Most Used")) {Comparator = new StationComparatorByMostUsed();}
		else{throw new BadInstantiationException(choice, "Station Comparator");}
		this.allStations.sort(Comparator);
		System.out.println("Station from the Network " + this.name + "sorted by " + choice);
		for (Station s : this.allStations) {
			s.displayOnlyName();
		}
	}

}
