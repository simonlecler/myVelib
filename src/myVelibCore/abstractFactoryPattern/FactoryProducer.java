package myVelibCore.abstractFactoryPattern;

import myVelibCore.exceptions.BadInstantiationException;

/**
 * <b>FactoryProducer belongs to the factory pattern</b>
 * <p>It allows to instantiate abstract factories
 * <p> There are four types of factories :
 * <ul>
 * <li>UserFactory</li>
 * <li>CardFactory</li>
 * <li>BycicleFactory</li>
 * <li>StationFactory</li>
 * </ul>
 * @author Simon Lecler
 */
public class FactoryProducer {
	/**
	 * 
	 * @param choice of type String
	 * <p> The type of factory wanted
	 * @return The desired abstract factory
	 */
	public static AbstractFactory getFactory(String choice) throws BadInstantiationException{
		if(choice.equalsIgnoreCase("User")){
			return new UserFactory();
		} else if(choice.equalsIgnoreCase("Bycicle")){
			return new BycicleFactory();
		
		} else if(choice.equalsIgnoreCase("Station")){
			return new StationFactory();
		
		} else if(choice.equalsIgnoreCase("Card")){
			return new CardFactory();
			
		} else if (choice.equalsIgnoreCase("Network")) {
			return new NetworkFactory();
		} else {
			throw new BadInstantiationException(choice,"Factory");
		}
	}
}
