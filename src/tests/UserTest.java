package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.abstractFactoryPattern.NetworkFactory;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleSpeeds;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.ComputeCostImpossibleException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.PlanningRideFailException;
import myVelibCore.exceptions.RentBikeFailException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
import myVelibCore.utilities.RunningTime;
import myVelibCore.utilities.Time;

class UserTest {

	
	@Test
	void testComputeMyRideCost() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, ComputeCostImpossibleException {
	
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory cardFactory = FactoryProducer.getFactory("Card");
		Card cardVlibre0 = cardFactory.getCard("Vlibre");
		Card cardVmax0 = cardFactory.getCard("Vmax");
		Card cardVlibre1 = cardFactory.getCard("Vlibre",20);
		Card cardVmax1 = cardFactory.getCard("Vmax",20);
		
		Bycicle bycicle1= bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2= bycicleFactory.getBycicle("Mechanical");
		Network network4 = NetworkFactory.getNetwork("testNetwork4");
		User user1 = userFactory.getUser("Simon", network4);
		User user2 = userFactory.getUser("Edouard", network4);
		user2.setBycicle(bycicle2);
		user1.setBycicle(bycicle1);
		assertTrue(user1.computeMyRideCost(10)==2);
		assertTrue(user1.computeMyRideCost(70)==4);
		assertTrue(user2.computeMyRideCost(10)==1);
		assertTrue(user2.computeMyRideCost(70)==2);
		user1.setUserCard(cardVmax0);
		user2.setUserCard(cardVlibre0);
		assertTrue(user1.computeMyRideCost(10)==0);
		assertTrue(user1.computeMyRideCost(70)==1);
		assertTrue(user2.computeMyRideCost(10)==0);
		assertTrue(user2.computeMyRideCost(70)==1);
		user1.setUserCard(cardVmax1);
		user2.setUserCard(cardVlibre1);
		assertTrue(user1.computeMyRideCost(70)==0);
		assertTrue(user2.computeMyRideCost(125)==1);
		
		
		
		
		
		
	}

	@Test
	void testPlanningRide() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, AddBikeFailException, PlanningRideFailException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		User user1 = userFactory.getUser("Simon", network1);
		
		user1.setGpsLocation(new GPSLocation(31,31));

		try {
			Station arrivee = stationFactory.getStation("standard", new GPSLocation(1,1), network1);
			Station depart = stationFactory.getStation("standard", new GPSLocation(30,30), network1);
			ParkingSlot slot1 = new ParkingSlot(depart);
			ParkingSlot slot2 = new ParkingSlot(arrivee);
			ParkingSlot slot3 = new ParkingSlot(depart);
			ParkingSlot slot4 = new ParkingSlot(arrivee);
			Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
			Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
			depart.getStationBikeCounters().addBike(bycicle1, depart.getSlots());
			depart.getStationBikeCounters().addBike(bycicle2, depart.getSlots());
			user1.planningRide(new GPSLocation(0,0), "Avoid Plus Stations", "Electrical");

			assertTrue(user1.getCurrentDepartureStation()==depart);
			assertTrue(user1.getCurrentDestinationStation()==arrivee);
			assertTrue(user1.isPlanningARide() == true);
		
	
			
		} catch (BadInstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdateDeparture() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateDestination() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateTotalTimeSpentOnABike() {
		fail("Not yet implemented");
	}
	
	@Test
	void testIsDeparted() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory networkFactory = FactoryProducer.getFactory("Network");
		Network network1 = networkFactory.getNetwork("network1");

		User user = userFactory.getUser("John",network1);
		
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");

		user.isDeparted(bycicle1);
		
		assertTrue(user.getCurrentDepartureStation()==null);
		assertTrue(user.getBycicle()==bycicle1);
		assertTrue(user.getTimeWhenReturningBike()==null);
		assertTrue(user.isPlanningARide()==false);
		
		assertTrue(user.getUserStatitics().getNumberOfRides()==1);
		

		
	}
	
	
	@Test
	void testRides() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, AddBikeFailException, PlanningRideFailException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network2 = NetworkFactory.getNetwork("testNetwork2");
		User user1 = userFactory.getUser("Simon", network2);
		user1.setGpsLocation(new GPSLocation(30.3195050,30.319505));
		Station arrivee = stationFactory.getStation("standard", new GPSLocation(30.3193,30.3193), network2);
		Station depart = stationFactory.getStation("standard", new GPSLocation(30.3195,30.3195), network2);
		ParkingSlot slot1 = new ParkingSlot(depart);
		ParkingSlot slot2 = new ParkingSlot(arrivee);
		ParkingSlot slot3 = new ParkingSlot(depart);
		ParkingSlot slot4 = new ParkingSlot(arrivee);
		
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		depart.getStationBikeCounters().addBike(bycicle1, depart.getSlots());
		depart.getStationBikeCounters().addBike(bycicle2, depart.getSlots());


		user1.planningRide(new GPSLocation(30.31925,30.31925), "Avoid Plus Stations", "Electrical");
		
		
		user1.rides();
		
		Network.setSimulaton_On(false);
		
		assertTrue(user1.getGpsLocation().getLatitude()==30.31925);
		assertTrue(user1.getGpsLocation().getLongitude()==30.31925);
		assertTrue(user1.isPlanningARide()==false);
		
	}

	@Test
	void testRun() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, AddBikeFailException, PlanningRideFailException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network3 = NetworkFactory.getNetwork("testNetwork3");
		User user1 = userFactory.getUser("Simon", network3);
		User user2 = userFactory.getUser("Edouard", network3);
		user1.setGpsLocation(new GPSLocation(30.319505,30.319505));
		Thread user1Thread = new Thread(user1);
		Thread user2Thread = new Thread(user2);
		user2.setGpsLocation(new GPSLocation(30.3195059,30.3195059));
		Station arrivee = stationFactory.getStation("standard", new GPSLocation(30.3193,30.3193), network3);
		Station depart = stationFactory.getStation("standard", new GPSLocation(30.3195,30.3195), network3);
		ParkingSlot slot1 = new ParkingSlot(depart);
		ParkingSlot slot2 = new ParkingSlot(arrivee);
		ParkingSlot slot3 = new ParkingSlot(depart);
		ParkingSlot slot4 = new ParkingSlot(arrivee);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		depart.getStationBikeCounters().addBike(bycicle1, depart.getSlots());
		depart.getStationBikeCounters().addBike(bycicle2, depart.getSlots());
	
		Thread time = new Thread(RunningTime.getInstance());
		time.start();
		user1Thread.start();
		user2Thread.start();
		user1.planningRide(new GPSLocation(30.31925,30.31925), "Avoid Plus Stations", "Electrical");
		user2.planningRide(new GPSLocation(30.31925,30.31925), "Avoid Plus Stations", "Electrical");
		//user1.rides();
		//user2.rides();
		
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(user1.getGpsLocation());
		System.out.println(user2.getGpsLocation());
		assertTrue(user1.getGpsLocation().getLatitude()==30.31925 && user1.getGpsLocation().getLongitude()==30.31925);
		assertTrue(user2.getGpsLocation().getLatitude()==30.31925 && user1.getGpsLocation().getLongitude()==30.31925);
	
	}
	
	

}
