package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
/**
 * <b>FactoryProducer belongs to the factory pattern</b>
 * <p>It contains methods overriden in the Factory classes that instantiate objects
 *
 * @author Simon Lecler
 */
public abstract class AbstractFactory {
	public abstract User getUser(String name, Network network)throws FactoryNullException;
	public abstract Station getStation(String stationType, GPSLocation gpsLocation, Network network)throws BadInstantiationException,FactoryNullException;
	public abstract Card getCard(String CardType) throws BadInstantiationException,FactoryNullException; 
	public abstract Card getCard(String CardType, int balance)throws BadInstantiationException,FactoryNullException;
	public abstract Bycicle getBycicle(String BycicleType) throws BadInstantiationException,FactoryNullException;
	public abstract Network getNetwork(String name) throws FactoryNullException, NetworkNameAlreadyUsedException;
}
