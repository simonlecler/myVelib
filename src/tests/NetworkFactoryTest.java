package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;

class NetworkFactoryTest {

	@Test
	void testGetNetwork() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory networkFactory = FactoryProducer.getFactory("Network");
		Network network1 =networkFactory.getNetwork("network1");
		assertTrue(network1.getName()=="network1");
		assertTrue(network1.getId()==1);
	}

}
