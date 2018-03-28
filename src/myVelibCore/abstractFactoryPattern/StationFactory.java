package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.stationPackage.StationPlus;
import myVelibCore.stationPackage.StationStandard;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;

/**
 * <b> This is the StationFactory, it belongs to the factory pattern</b> 
 * <p> It allows to instantiate Station objects
 * @author Simon Lecler
 */
public class StationFactory extends AbstractFactory{
	
	public static String getRandomStationType() {
		if (Math.random()>0.5) {return "Standard";}
		else {return "Plus";}
	}
	
	
	@Override
	public User getUser(String name, Network network) throws FactoryNullException {
		throw new FactoryNullException("Station","User");
	}
	@Override
	public Bycicle getBycicle(String BycicleType) throws FactoryNullException {
		throw new FactoryNullException("Station","Bycicle");
		
	}
	/**
	 * @param choice of type String
	 * <p>The type of station wanted 
	 * @return Instance of the desired type of station
	 */
	@Override
	public Station getStation(String stationType, GPSLocation gpsLocation, Network network) throws BadInstantiationException{
		if (stationType.equalsIgnoreCase("Standard")){
			try {return new StationStandard(gpsLocation,network);} 
			catch (UnimplementedSubclassWithoutInputException e) {System.out.println("NOT SUPPOSED TO HAPPEN" + e.getMessage());}
		}
		if (stationType.equalsIgnoreCase("Plus")){
			try{return new StationPlus(gpsLocation,network);}
			catch (UnimplementedSubclassWithoutInputException e) {System.out.println("NOT SUPPOSED TO HAPPEN" + e.getMessage());}
		}
		throw new BadInstantiationException(stationType,"Station");
	}

	@Override
	public Card getCard(String CardType) throws FactoryNullException {
		throw new FactoryNullException("Station","Card");
	}
	@Override
	public Card getCard(String CardType, int balance) throws FactoryNullException {
		throw new FactoryNullException("Station","Card");
	}
	@Override
	public Network getNetwork(String name) throws FactoryNullException {
		throw new FactoryNullException("Station","Network");
	}

}
