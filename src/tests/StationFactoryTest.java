package tests;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.stationPackage.StationPlus;
import myVelibCore.stationPackage.StationStandard;
import myVelibCore.utilities.GPSLocation;

class StationFactoryTest {

	@Test
	void testGetStation() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory StationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station stationPlus = StationFactory.getStation("Plus", new GPSLocation(10,20),network1);
		Station stationStandard = StationFactory.getStation("Standard", new GPSLocation(10,20),network1);
		assertTrue(stationPlus instanceof StationPlus);
		assertTrue(stationStandard instanceof StationStandard);
		assertTrue(stationPlus.getGpsLocation().getLatitude() == 10);
		assertTrue(stationPlus.getGpsLocation().getLongitude()==20);
		assertTrue(stationStandard.getGpsLocation().getLatitude() == 10);
		assertTrue(stationStandard.getGpsLocation().getLongitude()==20);
		assertTrue(stationPlus.getId() != stationStandard.getId());
	}

}
