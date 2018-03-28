package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.byciclePackage.BycicleMechanical;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
/**
 * <b> This is the BycicleFactory. It belongs to the factory pattern.</b>
 * <p>It allows to instantiate Bycicle objects.
 * @author Simon Lecler
 */
public class BycicleFactory extends AbstractFactory{
	
	public static String getRandomBycicleType() {
		if (Math.random()>0.5) {return "Electrical";}
		else {return "Mecanical";}
	}
	
	@Override
	public User getUser(String name, Network network) throws FactoryNullException {
		throw new FactoryNullException("Bycicle","User");
	}
	/**
	 * @param choice of type String
	 * <p> The type of bicycle wanted 
	 * @return Instance of the desired type of bycicle
	 * @throws BadBicycleInstantiationException 
	 */
	@Override
	public Bycicle getBycicle(String bycicleType) throws BadInstantiationException{
		
			if (bycicleType.equalsIgnoreCase(BycicleMechanical.typeWritten)){
				return new BycicleMechanical();
			}
			else if (bycicleType.equalsIgnoreCase(BycicleElectrical.typeWritten)){
				return new BycicleElectrical();
			}
			else {throw new BadInstantiationException(bycicleType,"bycicle");}
		}
		

		
	
	@Override
	public Station getStation(String stationType, GPSLocation gpsLocation, Network network) throws FactoryNullException {
		throw new FactoryNullException("Bycicle","Station");
	}

	@Override
	public Card getCard(String CardType) throws FactoryNullException {
		throw new FactoryNullException("Bycicle","Card");
	}
	@Override
	public Card getCard(String CardType, int balance) throws FactoryNullException {
		throw new FactoryNullException("Bycicle","Card");
	}
	@Override
	public Network getNetwork(String name) throws FactoryNullException {
		throw new FactoryNullException("Bycicle","Network");
	}

}
