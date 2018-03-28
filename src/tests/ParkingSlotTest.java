package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.SlotStatusFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;

class ParkingSlotTest {

	@Test
	void testGetOccupationStatus() throws BadInstantiationException, StationFullException, SlotStatusFailException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("Standard", new GPSLocation(0,3),network1);
		Station station2 = stationFactory.getStation("Standard", new GPSLocation(0,0),network1);
		Station station3 = stationFactory.getStation("Standard", new GPSLocation(1,3),network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		ParkingSlot slot2 = new ParkingSlot(station2);
		ParkingSlot slot3 = new ParkingSlot(station3);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		slot1.setStatus(false);
		station2.getStationBikeCounters().addBike(bycicle1, station2.getSlots());
		assertTrue(slot1.getOccupationStatus() == true);
		assertTrue(slot2.getOccupationStatus() == true);
		assertTrue(slot3.getOccupationStatus() == false);	
	}

}
