package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;

public class NetworkFactory extends AbstractFactory{

	@Override
	public User getUser(String name, Network network) throws FactoryNullException {
		throw new FactoryNullException("Network","User");
	}
	@Override
	public Bycicle getBycicle(String BycicleType) throws FactoryNullException {
		throw new FactoryNullException("Network","Bycicle");
	}
	@Override
	public Station getStation(String stationType, GPSLocation gpsLocation, Network network) throws FactoryNullException {
		throw new FactoryNullException("Network","Station");
	}

	@Override
	public Card getCard(String CardType) throws FactoryNullException {
		throw new FactoryNullException("Network","Card");
	}
	@Override
	public Card getCard(String CardType, int balance) throws FactoryNullException {
		throw new FactoryNullException("Network","Card");
	}
	@Override
	public Network getNetwork(String name) throws NetworkNameAlreadyUsedException {
		return new Network(name);
	}
}
