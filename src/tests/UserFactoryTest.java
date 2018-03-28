package tests;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.userAndCardPackage.User;

class UserFactoryTest {

	@Test
	void testGetUser() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		AbstractFactory UserFactory = FactoryProducer.getFactory("User");
		User user = UserFactory.getUser("John",network1);
		User user2 = UserFactory.getUser("Jean",network1);
		assertTrue(user.getName()== "John");
		assertTrue(user.getId() != user2.getId());
	}

}
