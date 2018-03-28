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
import myVelibCore.exceptions.RentBikeFailException;
import myVelibCore.exceptions.ReturnBikeFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.sortStationPackage.StationComparatorByMostUsed;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;

class StationComparatorByMostUsedTest {

	@Test
	void testCompare() throws BadInstantiationException, FactoryNullException, AddBikeFailException, NetworkNameAlreadyUsedException, ReturnBikeFailException, RentBikeFailException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		Station station1 = stationFactory.getStation("standard", new GPSLocation(1,1), network1);
		Station station2 = stationFactory.getStation("standard", new GPSLocation(30,30), network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		ParkingSlot slot2 = new ParkingSlot(station2);
		ParkingSlot slot3 = new ParkingSlot(station1);
		ParkingSlot slot4 = new ParkingSlot(station2);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle3 = bycicleFactory.getBycicle("Mechanical");
		User user1= userFactory.getUser("Jean", network1);
		station1.getStationBikeCounters().addBike(bycicle1, station1.getSlots());
		station2.getStationBikeCounters().addBike(bycicle2, station2.getSlots());
		StationComparatorByMostUsed comparator = new StationComparatorByMostUsed();
		System.out.println(comparator.compare(station1, station2));
		station1.rentABike(user1, "Electrical");
	
		System.out.println(comparator.compare(station1, station2));
	
		try {
			station2.returnABike(user1);
		} catch (StationFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		user1.setBycicle(bycicle3);
		try {
			station1.returnABike(user1);
		} catch (StationFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println(comparator.compare(station1, station2));
		assertTrue(comparator.compare(station1, station2)==1);
	}

}
