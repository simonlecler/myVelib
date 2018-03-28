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
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.planningPolicyPackage.PreferPlusStation;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.GPSLocation;

class PreferPlusStationTest {

	@Test
	void whenClosestDestinationStationIsPlusThenChooseIt() throws BadInstantiationException, StationFullException, NoStartingStationAvailableException,NoDestinationStationAvailableException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1);
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(0,3),network1);
		Station stationPlus2 = stationFactory.getStation("Plus", new GPSLocation(20,20),network1);


		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus2);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		ParkingSlot slot4 = new ParkingSlot(stationPlus1);
		ParkingSlot slot5 = new ParkingSlot(stationPlus2);
		ParkingSlot slot6 = new ParkingSlot(stationStandard1);
		
		Bycicle bycicle = bycicleFactory.getBycicle("Electrical");
		stationPlus2.getStationBikeCounters().addBike(bycicle,stationPlus2.getSlots());
		PreferPlusStation test = new PreferPlusStation();
		Station stations[] = {stationPlus2,stationPlus1};
		try {
			assertTrue(test.chooseStations(new GPSLocation(30,30), new GPSLocation(0,0),"Electrical",network1)[0].equals(stationPlus2));
		} catch (PlanningPathFailedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			assertTrue(test.chooseStations(new GPSLocation(30,30), new GPSLocation(0,0),"Electrical",network1)[1].equals(stationPlus1));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void whenClosestDestinationStationIsNotPlusAndPlusIsTooFarThenChooseStandard() throws BadInstantiationException, StationFullException, NoStartingStationAvailableException,NoDestinationStationAvailableException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station stationPlus3 = stationFactory.getStation("Plus", new GPSLocation(106,106),network1);
		Station stationStandard3 = stationFactory.getStation("Standard", new GPSLocation(100,103),network1);
		Station stationPlus4 = stationFactory.getStation("Plus", new GPSLocation(120,120),network1);


		ParkingSlot slot1 = new ParkingSlot(stationPlus3);
		ParkingSlot slot2 = new ParkingSlot(stationPlus4);
		ParkingSlot slot3 = new ParkingSlot(stationStandard3);
		ParkingSlot slot4 = new ParkingSlot(stationPlus3);
		ParkingSlot slot5 = new ParkingSlot(stationPlus4);
		ParkingSlot slot6 = new ParkingSlot(stationStandard3);
		
		Bycicle bycicle = bycicleFactory.getBycicle("Electrical");
		stationPlus4.getStationBikeCounters().addBike(bycicle,stationPlus4.getSlots());
		PreferPlusStation test = new PreferPlusStation();
		
		try {
			assertTrue(test.chooseStations(new GPSLocation(130,130), new GPSLocation(100,100),"Electrical",network1)[0].equals(stationPlus4));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(test.chooseStations(new GPSLocation(130,130), new GPSLocation(100,100),"Electrical",network1)[1].equals(stationStandard3));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	
	void whenClosestDestinationStationIsNotPlusThenChooseAPlusIfNotTooFar()throws BadInstantiationException, StationFullException, NoStartingStationAvailableException,NoDestinationStationAvailableException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station stationPlus5 = stationFactory.getStation("Plus", new GPSLocation(202.1,202),network1);
		Station stationStandard4 = stationFactory.getStation("Standard", new GPSLocation(202,202),network1);
		Station stationPlus6 = stationFactory.getStation("Plus", new GPSLocation(220,220),network1);
		Station stationStandard5 = stationFactory.getStation("Standard", new GPSLocation(210,220),network1);
		ParkingSlot slot1 = new ParkingSlot(stationPlus5);
		ParkingSlot slot2 = new ParkingSlot(stationPlus6);
		ParkingSlot slot3 = new ParkingSlot(stationStandard4);
		ParkingSlot slot4 = new ParkingSlot(stationStandard5);
		ParkingSlot slot6 = new ParkingSlot(stationPlus5);
		ParkingSlot slot7 = new ParkingSlot(stationPlus6);
		ParkingSlot slot8 = new ParkingSlot(stationStandard4);
		ParkingSlot slot9 = new ParkingSlot(stationStandard5);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		stationPlus6.getStationBikeCounters().addBike(bycicle1, stationPlus6.getSlots());
		stationPlus6.getStationBikeCounters().addBike(bycicle2, stationPlus6.getSlots());
		PreferPlusStation test = new PreferPlusStation();

		try {
			assertTrue(test.chooseStations(new GPSLocation(230,230), new GPSLocation(200,200),"Electrical",network1)[0].equals(stationPlus6));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(test.chooseStations(new GPSLocation(230,230), new GPSLocation(200,200),"Electrical",network1)[1].equals(stationPlus5));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
