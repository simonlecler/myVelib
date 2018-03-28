package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.CardNone;
import myVelibCore.userAndCardPackage.CardVLibre;
import myVelibCore.userAndCardPackage.CardVMax;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
/**
 * <b> This is the CardFactory, it belongs to the factory pattern</b> 
 * <p> It allows to instantiate Card objects
 * @author Simon Lecler
 */
public class CardFactory extends AbstractFactory{
	@Override
	public User getUser(String name, Network network) throws FactoryNullException {
		throw new FactoryNullException("Card","User");
	}
	@Override
	public Bycicle getBycicle(String BycicleType) throws FactoryNullException {
		throw new FactoryNullException("Card","Bycicle");
		
	}
	@Override
	public Station getStation(String stationType, GPSLocation gpsLocation, Network network) throws FactoryNullException {
		throw new FactoryNullException("Card","Station");
	}
	/**
	 * @param CardType 
	 * 		Type of card chosen by the user. Type String
	 *
	 * @return Instance of the desired type of card
	 */
	@Override
	public Card getCard(String CardType) throws BadInstantiationException{
		if (CardType.equalsIgnoreCase("Vlibre")){
			return new CardVLibre();
		}
		else if (CardType.equalsIgnoreCase("Vmax")){
			return new CardVMax();
		}
		else if(CardType.equalsIgnoreCase("None")){
			return new CardNone();
		}
		else {throw new BadInstantiationException(CardType,"Card");}
		
	}
	
	public Card getCard(String CardType, int balance) throws BadInstantiationException {
		if (CardType.equalsIgnoreCase("Vlibre")){
			return new CardVLibre(balance);
		}
		else if (CardType.equalsIgnoreCase("Vmax")){
			return new CardVMax(balance);
		}
		else if(CardType.equalsIgnoreCase("None")){
			return new CardNone();
		}
		else {throw new BadInstantiationException(CardType,"Card");}
	}
	
	@Override
	public Network getNetwork(String name) throws FactoryNullException {
		throw new FactoryNullException("Card","Network");
	}
	

}
