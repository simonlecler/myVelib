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
import myVelibCore.planningPolicyPackage.AvoidPlusStation;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
class AvoidPlusStationTest {

	@Test
	void testChooseStations() throws BadInstantiationException, StationFullException,NoStartingStationAvailableException,NoDestinationStationAvailableException, AddBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		//Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(1,0));
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(0,3),network1);
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(20,20),network1);
		//Station stationStandard2 = stationFactory.getStation("Standard", new GPSLocation(10,20));
		//Station stationStandard3 = stationFactory.getStation("Standard", new GPSLocation(3,5));
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		//Bycicle bycicle3 = bycicleFactory.getBycicle("Electrical");
		//Bycicle bycicle4 = bycicleFactory.getBycicle("Electrical");
		//Bycicle bycicle5 = bycicleFactory.getBycicle("Electrical");
		//ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		//ParkingSlot slot2 = new ParkingSlot(stationPlus1);
		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus1);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		ParkingSlot slot4 = new ParkingSlot(stationStandard1);
		User user = new User("Jean",network1);
		user.setGpsLocation(new GPSLocation(30,30));
		//ParkingSlot slot7 = new ParkingSlot(stationStandard2);
		//ParkingSlot slot8 = new ParkingSlot(stationStandard2);
		//ParkingSlot slot9 = new ParkingSlot(stationStandard3);
		//ParkingSlot slot10 = new ParkingSlot(stationStandard3);
		//stationPlus1.getStationBikeCounters().addBike(bycicle1, stationPlus1.getSlots());
		stationPlus1.getStationBikeCounters().addBike(bycicle1, stationPlus1.getSlots());
		stationStandard1.getStationBikeCounters().addBike(bycicle2, stationStandard1.getSlots());
		//stationStandard2.getStationBikeCounters().addBike(bycicle1, stationPlus1.getSlots());
		//stationStandard3.getStationBikeCounters().addBike(bycicle1, stationPlus1.getSlots());
		
		
		AvoidPlusStation test = new AvoidPlusStation();
		Station stations[] = {stationPlus1,stationStandard1};

		try {
			assertTrue(test.chooseStations(user.getGpsLocation(), new GPSLocation(0,0),"Electrical",network1)[0].equals(stations[0]));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(test.chooseStations(user.getGpsLocation(), new GPSLocation(0,0),"Electrical",network1)[1].equals(stationStandard1));
		} catch (PlanningPathFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
