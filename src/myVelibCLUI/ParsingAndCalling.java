package myVelibCLUI;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NotEnoughSlotsException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;

public class ParsingAndCalling {
	
	private static final String TYPE_ERROR_MSG = "An argument is of a wrong type. Please retry or type help for assistance.";
	
	public static void setupWith1Param(String[] args) {
		String velibNetworkName;
		velibNetworkName = args[1];
		try {
			Network.setupNetwork(velibNetworkName, 10, 10, 4, 75);
		}
		
	}
	
	public static void setupWith5Param(String[] args) {
		String name; int nstations=0; int nslots=0; float sidearea=0; int nbikes=0;
		boolean argsParsable = true;
		name = args[1];
		try{nstations = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : nstations must be an integer"); argsParsable = false;}
		try{nslots = Integer.parseInt(args[3]);}
		catch(Exception e) {System.out.println("Error : nslots must be an integer"); argsParsable = false;}
		try{sidearea = Float.parseFloat(args[4]);}
		catch(Exception e) {System.out.println("Error : sidearea must be a float"); argsParsable = false;}
		try{nbikes = Integer.parseInt(args[5]);}
		catch(Exception e) {System.out.println("Error : nbikes must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network.setupNetwork(name, nstations, nslots, sidearea, nbikes);
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void addUserWith3Param(String[] args) {
		String userName; String cardType; String velibNetworkName;
		userName = args[1];
		cardType = args[2];
		velibNetworkName = args[3];
		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			AbstractFactory UserFactory = FactoryProducer.getFactory("User");
			User user = UserFactory.getUser(userName,network);
			AbstractFactory cardFactory = FactoryProducer.getFactory("Card");
			Card userCard = cardFactory.getCard(cardType);
		}
		
	}
	
	public static void offlineWith2Param(String[] args) {
		String velibNetworkName; int stationID;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		try{stationID = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : stationID must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				Station station = network.searchStationByID(stationID);
				station.turnOff();
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void onlineWith2Param(String[] args) {
		String velibNetworkName; int stationID;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		try{stationID = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : stationID must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			Station station = network.searchStationByID(stationID);
			station.turnOn();
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void rentBikeWith2Param(String[] args) {
		int userID; int stationID; String bycicleType;
		boolean argsParsable = true;
		try{userID = Integer.parseInt(args[1]);}
		catch(Exception e) {System.out.println("Error : userID must be an integer"); argsParsable = false;}
		try{stationID = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : stationID must be an integer"); argsParsable = false;}
		bycicleType = args[3];
		if(argsParsable) {
			try {
				User user = Network.searchUserByIDAllNetworks(userID);
				Station station = Network.searchStationByIDAllNetworks(stationID);
				station.rentABike(user, bycicleType);
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void returnBikeWith3Param(String[] args) {
		int userID; int stationID; int time;
		boolean argsParsable = true;
		try{userID = Integer.parseInt(args[1]);}
		catch(Exception e) {System.out.println("Error : userID must be an integer"); argsParsable = false;}
		try{stationID = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : stationID must be an integer"); argsParsable = false;}
		try{time = Integer.parseInt(args[3]);}
		catch(Exception e) {System.out.println("Error : time must be an integer"); argsParsable = false;}
		if(argsParsable) {
			
			///A IMPLEMENTER
			
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void displayStationWith2Param(String[] args) {
		String velibNetworkName =""; int stationID=0;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		try{stationID = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : stationID must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				Station station = network.searchStationByID(stationID);
				station.display();
			}
			
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void displayUserWith2Param(String[] args) {
		String velibNetworkName; int userID;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		try{ userID = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : userID must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				User user = network.searchUserByID(userID);
				user.displayStatistics();
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void sortStationWith2Param(String[] args) {
		String velibNetworkName; String policy;
		velibNetworkName = args[1];
		policy = args[2];
		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			network.sortStationBy(policy);
		}
		
		
	}
	
	public static void displayWith1Param(String[] args) {
		String velibNetworkName;

		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			network.display();
		}
		
	}
}
