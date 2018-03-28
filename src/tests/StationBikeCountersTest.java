package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NoBycicleAvailableException;
import myVelibCore.exceptions.RemoveBikeFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;

class StationBikeCountersTest {

	@Test
	void testIsThereAnyBycicle() throws BadInstantiationException, StationFullException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		Station station2 = stationFactory.getStation("Standard", new GPSLocation(3,1),network1);
		Bycicle bycicle = bycicleFactory.getBycicle("Electrical");
		ParkingSlot slot1 = new ParkingSlot(station1);
		ParkingSlot slot2 = new ParkingSlot(station2);
		station2.getStationBikeCounters().addBike(bycicle, station2.getSlots());

		assertTrue(station1.getStationBikeCounters().isThereAnyBycicle()==false);
		assertTrue(station2.getStationBikeCounters().isThereAnyBycicle() == true);
	}

	@Test
	void testIsThereAny() throws BadInstantiationException, StationFullException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		Bycicle bycicle = bycicleFactory.getBycicle("Mechanical");
		station1.getStationBikeCounters().addBike(bycicle, station1.getSlots());
		assertTrue(station1.getStationBikeCounters().isThereAny("Mechanical"));
		assertTrue(station1.getStationBikeCounters().isThereAny("Electrical")== false);
	}

	@Test
	void testHowMany()throws BadInstantiationException, StationFullException, AddBikeFailException, UnimplementedSubclassWithInputException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		Bycicle bycicle = bycicleFactory.getBycicle("Mechanical");
		station1.getStationBikeCounters().addBike(bycicle, station1.getSlots());
		assertTrue(station1.getStationBikeCounters().howMany("Mechanical") == 1 );
		assertTrue(station1.getStationBikeCounters().howMany("Electrical")==0);
	}

	@Test
	void testAddBike() throws BadInstantiationException, StationFullException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		Bycicle bycicle = bycicleFactory.getBycicle("Electrical");
		ArrayList<ParkingSlot> slots = station1.getSlots();
		station1.getStationBikeCounters().addBike(bycicle, slots);
		assertTrue(station1.getStationBikeCounters().getNumberElectrical()==1);
		assertTrue(slot1.getBycicle().equals(bycicle));
	}

	@Test
	void testRemoveBike()throws BadInstantiationException, NoBycicleAvailableException, RemoveBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		Bycicle bycicle = new BycicleElectrical();
		ArrayList<ParkingSlot> slots = station1.getSlots();
		slot1.setBycicle(bycicleFactory.getBycicle("Mechanical"));
		station1.getStationBikeCounters().removeBike("Mechanical", slots);
		assertTrue(station1.getStationBikeCounters().getNumberMechanical()==0);
	}

}
