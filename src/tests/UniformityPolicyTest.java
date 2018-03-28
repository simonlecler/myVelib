package tests;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.planningPolicyPackage.UniformityPolicy;
import myVelibCore.stationPackage.Network;

import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;

class UniformityPolicyTest {

	@Test
	void whenStationWithMoreBycicleOrMoreFreeSlotNotSoFarThenChooseIt() throws BadInstantiationException, StationFullException, NoStartingStationAvailableException,NoDestinationStationAvailableException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(21,21), network1);
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(20,20),network1);
		Station stationPlus2 = stationFactory.getStation("Plus", new GPSLocation(100,100),network1);
		Station stationStandard2 = stationFactory.getStation("Standard", new GPSLocation(99,99),network1);


		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus1);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		ParkingSlot slot4 = new ParkingSlot(stationStandard2);
		ParkingSlot slot5 = new ParkingSlot(stationStandard2);
		ParkingSlot slot6 = new ParkingSlot(stationPlus2);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle3 = bycicleFactory.getBycicle("Electrical");
		
		stationPlus2.getStationBikeCounters().addBike(bycicle1, stationPlus2.getSlots());
		stationStandard2.getStationBikeCounters().addBike(bycicle2, stationStandard2.getSlots());
		stationStandard2.getStationBikeCounters().addBike(bycicle3, stationStandard2.getSlots());
		
		UniformityPolicy test = new UniformityPolicy();
		try {
			assertTrue(test.chooseStations(new GPSLocation(30,30), new GPSLocation(0,0),"Electrical", network1)[0].equals(stationStandard2));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(test.chooseStations(new GPSLocation(130,130), new GPSLocation(0,0),"Electrical", network1)[1].equals(stationPlus1));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
