

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningRideFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.planningPolicyPackage.PolicyChoice;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
import myVelibCore.utilities.RunningTime;

public class ClientApplication {
	
	
	public static void main(String[] args) throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, AddBikeFailException, PlanningRideFailException, StationFullException {
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork");
		User user1 = userFactory.getUser("Simon", network1);
		
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle3 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle4 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle5 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle6 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle7 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle8 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle9 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle10 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle11= bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle12= bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle13 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle14 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle15 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle16 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle17 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle18 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle19 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle20 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle21 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle22 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle23 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle24 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle25 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle26 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle27 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle28= bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle29 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle30 = bycicleFactory.getBycicle("Electrical");
		
		Bycicle bycicle31 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle32 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle33 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle34 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle35 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle36 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle37 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle38 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle39 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle40 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle41 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle42 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle43 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle44 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle45 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle46 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle47 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle48 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle49 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle50 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle51 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle52 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle53 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle54 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle55 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle56 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle57 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle58 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle59 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle60 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle61 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle62 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle63 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle64 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle65 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle66 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle67 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle68 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle69 = bycicleFactory.getBycicle("Mechanical");
		Bycicle bycicle70 = bycicleFactory.getBycicle("Mechanical");
	
		
		
		
		
		
		Station station1 = stationFactory.getStation("standard", new GPSLocation(48.5125,2.2030), network1);
		ParkingSlot slot1 = new ParkingSlot(station1);
		ParkingSlot slot2 = new ParkingSlot(station1);
		ParkingSlot slot3 = new ParkingSlot(station1);
		ParkingSlot slot4 = new ParkingSlot(station1);
		ParkingSlot slot5 = new ParkingSlot(station1);
		

		Station station2 = stationFactory.getStation("standard", new GPSLocation(48.5136,2.2039), network1);
		ParkingSlot slot6 = new ParkingSlot(station2);
		ParkingSlot slot7 = new ParkingSlot(station2);
		ParkingSlot slot8 = new ParkingSlot(station2);
		ParkingSlot slot9 = new ParkingSlot(station2);
		ParkingSlot slot10 = new ParkingSlot(station2);
		Station station3 = stationFactory.getStation("standard", new GPSLocation(48.525,2.1950), network1);
		ParkingSlot slot11 = new ParkingSlot(station3);
		ParkingSlot slot12 = new ParkingSlot(station3);
		ParkingSlot slot13 = new ParkingSlot(station3);
		ParkingSlot slot14 = new ParkingSlot(station3);
		ParkingSlot slot15 = new ParkingSlot(station3);
		Station station4 = stationFactory.getStation("standard", new GPSLocation(48.5135,2.2018), network1);
		ParkingSlot slot16 = new ParkingSlot(station4);
		ParkingSlot slot17 = new ParkingSlot(station4);
		ParkingSlot slot18 = new ParkingSlot(station4);
		ParkingSlot slot19 = new ParkingSlot(station4);
		ParkingSlot slot20 = new ParkingSlot(station4);
		
		Station station5 = stationFactory.getStation("standard", new GPSLocation(48.5149,2.203), network1);
		ParkingSlot slot21 = new ParkingSlot(station5);
		ParkingSlot slot22 = new ParkingSlot(station5);
		ParkingSlot slot23 = new ParkingSlot(station5);
		ParkingSlot slot24 = new ParkingSlot(station5);
		ParkingSlot slot25 = new ParkingSlot(station5);
		Station station6 = stationFactory.getStation("standard", new GPSLocation(48.5137,2.2032), network1);
		ParkingSlot slot26 = new ParkingSlot(station6);
		ParkingSlot slot27 = new ParkingSlot(station6);
		ParkingSlot slot28 = new ParkingSlot(station6);
		ParkingSlot slot29 = new ParkingSlot(station6);
		ParkingSlot slot30 = new ParkingSlot(station6);
		
		Station station7 = stationFactory.getStation("standard", new GPSLocation(48.5158,2.1931), network1);
		ParkingSlot slot31 = new ParkingSlot(station7);
		ParkingSlot slot32 = new ParkingSlot(station7);
		ParkingSlot slot33 = new ParkingSlot(station7);
		ParkingSlot slot34 = new ParkingSlot(station7);
		ParkingSlot slot35 = new ParkingSlot(station7);
		
		Station station8 = stationFactory.getStation("standard", new GPSLocation(48.5149,2.2033), network1);
		ParkingSlot slot36 = new ParkingSlot(station8);
		ParkingSlot slot37 = new ParkingSlot(station8);
		ParkingSlot slot38 = new ParkingSlot(station8);
		ParkingSlot slot39 = new ParkingSlot(station8);
		ParkingSlot slot40 = new ParkingSlot(station8);
		Station station9 = stationFactory.getStation("standard", new GPSLocation(48.5160,2.203), network1);
		ParkingSlot slot41 = new ParkingSlot(station9);
		ParkingSlot slot42 = new ParkingSlot(station9);
		ParkingSlot slot43 = new ParkingSlot(station9);
		ParkingSlot slot44 = new ParkingSlot(station9);
		ParkingSlot slot45 = new ParkingSlot(station9);
		Station station10 = stationFactory.getStation("standard", new GPSLocation(48.5160,2.1942), network1);
		ParkingSlot slot46 = new ParkingSlot(station10);
		ParkingSlot slot47 = new ParkingSlot(station10);
		ParkingSlot slot48 = new ParkingSlot(station10);
		ParkingSlot slot49 = new ParkingSlot(station10);
		ParkingSlot slot50 = new ParkingSlot(station10);
		Station station11= stationFactory.getStation("plus", new GPSLocation(48.5146,2.2029), network1);
		ParkingSlot slot51 = new ParkingSlot(station11);
		ParkingSlot slot52 = new ParkingSlot(station11);
		ParkingSlot slot53 = new ParkingSlot(station11);
		ParkingSlot slot54 = new ParkingSlot(station11);
		ParkingSlot slot55 = new ParkingSlot(station11);
		
		Station station12= stationFactory.getStation("plus", new GPSLocation(48.5156,2.1950), network1);
		ParkingSlot slot56 = new ParkingSlot(station12);
		ParkingSlot slot57 = new ParkingSlot(station12);
		ParkingSlot slot58 = new ParkingSlot(station12);
		ParkingSlot slot59 = new ParkingSlot(station12);
		ParkingSlot slot60 = new ParkingSlot(station12);
		
		Station station13= stationFactory.getStation("plus", new GPSLocation(48.5158,2.1931), network1);
		ParkingSlot slot61 = new ParkingSlot(station13);
		ParkingSlot slot62 = new ParkingSlot(station13);
		ParkingSlot slot63 = new ParkingSlot(station13);
		ParkingSlot slot64 = new ParkingSlot(station13);
		ParkingSlot slot65 = new ParkingSlot(station13);
		Station station14= stationFactory.getStation("plus", new GPSLocation(48.5128,2.2050), network1);
		ParkingSlot slot66 = new ParkingSlot(station14);
		ParkingSlot slot67 = new ParkingSlot(station14);
		ParkingSlot slot68 = new ParkingSlot(station14);
		ParkingSlot slot69 = new ParkingSlot(station14);
		ParkingSlot slot70 = new ParkingSlot(station14);
		Station station15= stationFactory.getStation("plus", new GPSLocation(48.5148,2.2024), network1);
		ParkingSlot slot71 = new ParkingSlot(station15);
		ParkingSlot slot72 = new ParkingSlot(station15);
		ParkingSlot slot73 = new ParkingSlot(station15);
		ParkingSlot slot74 = new ParkingSlot(station15);
		ParkingSlot slot75 = new ParkingSlot(station15);
		
		Station station16= stationFactory.getStation("plus", new GPSLocation(48.5142,2.2041), network1);
		ParkingSlot slot76 = new ParkingSlot(station16);
		ParkingSlot slot77 = new ParkingSlot(station16);
		ParkingSlot slot78 = new ParkingSlot(station16);
		ParkingSlot slot79 = new ParkingSlot(station16);
		ParkingSlot slot80 = new ParkingSlot(station16);
		Station station17= stationFactory.getStation("plus", new GPSLocation(48.5131,2.2030), network1);
		
		ParkingSlot slot81 = new ParkingSlot(station17);
		ParkingSlot slot82 = new ParkingSlot(station17);
		ParkingSlot slot83 = new ParkingSlot(station17);
		ParkingSlot slot84 = new ParkingSlot(station17);
		ParkingSlot slot85 = new ParkingSlot(station17);
		Station station18= stationFactory.getStation("plus", new GPSLocation(48.5160,2.203), network1);
		
		ParkingSlot slot86 = new ParkingSlot(station18);
		ParkingSlot slot87 = new ParkingSlot(station18);
		ParkingSlot slot88 = new ParkingSlot(station18);
		ParkingSlot slot89 = new ParkingSlot(station18);
		ParkingSlot slot90 = new ParkingSlot(station18);
		Station station19= stationFactory.getStation("plus", new GPSLocation(48.5150,2.2051), network1);
		ParkingSlot slot91 = new ParkingSlot(station19);
		ParkingSlot slot92 = new ParkingSlot(station19);
		ParkingSlot slot93 = new ParkingSlot(station19);
		ParkingSlot slot94 = new ParkingSlot(station19);
		ParkingSlot slot95 = new ParkingSlot(station19);
		
		Station station20= stationFactory.getStation("plus", new GPSLocation(48.5144,2.2053), network1);
		ParkingSlot slot96 = new ParkingSlot(station20);
		ParkingSlot slot97 = new ParkingSlot(station20);
		ParkingSlot slot98 = new ParkingSlot(station20);
		ParkingSlot slot99 = new ParkingSlot(station20);
		ParkingSlot slot100 = new ParkingSlot(station20);
		
		
		
		
		Thread user1Thread = new Thread(user1);
		
		user1.setGpsLocation(new GPSLocation(31,31));
		//User user2 = userFactory.getUser("Edouard", network1);
		//user2.setGpsLocation(new GPSLocation(32,32)); // L'erreur était là
		//Thread user2Thread = new Thread(user2);
		Thread time = new Thread(RunningTime.getInstance());
		time.start();
		user1Thread.start();
		//user2Thread.start();
		try {

			
			Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
			Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
			depart.getStationBikeCounters().addBike(bycicle1, depart.getSlots());
			depart.getStationBikeCounters().addBike(bycicle2, depart.getSlots());
			user1.planningRide(new GPSLocation(0,0), "Avoid Plus Stations", "Electrical");
			
			//user2.planningRide(new GPSLocation(0,0), "Avoid Plus Stations", "Electrical");
			
			
		} catch (BadInstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user1.getGpsLocation());
		System.out.println(user1.isPlanningARide());
		System.out.println(user1.getCurrentDepartureStation());
		System.out.println(user1.getCurrentDestinationStation());
		//System.out.println(user2.getGpsLocation());
		Network.setSimulaton_On(false);
		
	
		
		
	}
	
	
}
