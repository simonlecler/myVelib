package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.userAndCardPackage.User;

class UserStatiticsTest {



	@Test
	void testIncreaseNumberOfRidesBy() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory networkFactory = FactoryProducer.getFactory("Network");
		Network network1 = networkFactory.getNetwork("network1");
		
		User user = userFactory.getUser("John",network1);
		user.getUserStatitics().increaseNumberOfRidesBy(2);

		assertTrue(user.getUserStatitics().getNumberOfRides()==2);
	}

	@Test
	void testIncreaseTotalTimeSpentOnABikeBy() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory networkFactory = FactoryProducer.getFactory("Network");
		Network network2 = networkFactory.getNetwork("network2");
		
		User user = userFactory.getUser("John",network2);
		user.getUserStatitics().increaseTotalTimeSpentOnABikeBy(5);
		
		
		assertTrue(user.getUserStatitics().getTotalTimeSpentOnABikeWithoutActualRide()==5);
	}

	@Test
	void testIncreaseTotalAmountOfChargesBy() throws FactoryNullException, NetworkNameAlreadyUsedException, BadInstantiationException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory networkFactory = FactoryProducer.getFactory("Network");
		Network network3 = networkFactory.getNetwork("network3");

		User user = userFactory.getUser("John",network3);
		user.getUserStatitics().increaseTotalAmountOfChargesBy(5);
		assertTrue(user.getUserStatitics().getTotalAmountOfCharges()==5);
	}


}
