package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.RecalculatePathFailedException;
import myVelibCore.planningPolicyPackage.ShortestPath;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;

class ShortestPathTest {
	
	@Test
	void testChooseStations() throws BadInstantiationException, NoStartingStationAvailableException,NoDestinationStationAvailableException{
		try {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(0,3),network1);
		Station stationPlus2 = stationFactory.getStation("Plus", new GPSLocation(20,20),network1);
		Station stationStandard2 = stationFactory.getStation("Standard", new GPSLocation(10,20),network1);
		Station stationStandard3 = stationFactory.getStation("Standard", new GPSLocation(3,5),network1);
		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus2);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		ParkingSlot slot4 = new ParkingSlot(stationStandard2);
		ParkingSlot slot5 = new ParkingSlot(stationStandard3);
		ParkingSlot slot6 = new ParkingSlot(stationPlus1);
		ParkingSlot slot7 = new ParkingSlot(stationPlus2);
		ParkingSlot slot8 = new ParkingSlot(stationStandard1);
		ParkingSlot slot9 = new ParkingSlot(stationStandard2);
		ParkingSlot slot10 = new ParkingSlot(stationStandard3);
		stationPlus1.addBike(bycicleFactory.getBycicle(BycicleElectrical.typeWritten));
		stationPlus2.addBike(bycicleFactory.getBycicle(BycicleElectrical.typeWritten));
		stationStandard1.addBike(bycicleFactory.getBycicle(BycicleElectrical.typeWritten));
		stationStandard2.addBike(bycicleFactory.getBycicle(BycicleElectrical.typeWritten));
		stationStandard3.addBike(bycicleFactory.getBycicle(BycicleElectrical.typeWritten));
		
		Station stations[] = {stationPlus2,stationPlus1};
		ShortestPath test = new ShortestPath();
	
		
		assertTrue(test.chooseStations(new GPSLocation(30,30), new GPSLocation(0,0),BycicleElectrical.typeWritten,network1)== stations);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@Test
	void testRecalculateWhenRiding()throws BadInstantiationException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network2 = NetworkFactory.getNetwork("testNetwork2");
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network2);
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(3,3),network2);
		Station stationPlus2 = stationFactory.getStation("Plus", new GPSLocation(20,20),network2);
		Station stationStandard2 = stationFactory.getStation("Standard", new GPSLocation(10,20),network2);
		Station stationStandard3 = stationFactory.getStation("Standard", new GPSLocation(3,5),network2);
		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus2);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		ParkingSlot slot4 = new ParkingSlot(stationStandard2);
		ParkingSlot slot5 = new ParkingSlot(stationStandard3);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		
		stationPlus1.getStationBikeCounters().addBike(bycicle1, stationPlus1.getSlots());
	
		ShortestPath test = new ShortestPath();
		Station destination = stationStandard1;
		try {
			
			assertTrue(test.recalculateWhenRiding(new GPSLocation(5,5), new GPSLocation(0,0), "Electrical",network2)== destination);
		} catch (RecalculatePathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
