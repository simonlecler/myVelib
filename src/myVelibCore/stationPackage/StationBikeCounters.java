package myVelibCore.stationPackage;

import java.util.ArrayList;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.byciclePackage.BycicleMechanical;
import myVelibCore.exceptions.NoBycicleAvailableException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.RemoveBikeFailException;
/***
 * This class is a counter that handle the number of bicycle in the station, and can add or remove bicycles
 * @author Edouard Benauw
 *
 */
public class StationBikeCounters {
	/**
	 * Number of electrical bicycles
	 */
	private int numberElectrical = 0;
	/**
	 * Number of mechanical bicycles
	 */
	private int numberMechanical = 0;
	/**
	 * Number of free slots
	 */
	private int freeSlots;
	
	
	
	public int getNumberElectrical() {
		return numberElectrical;
	}

	public int getNumberMechanical() {
		return numberMechanical;
	}
/**
 * Default constructor
 */
	public StationBikeCounters() {
		super();
	}
/**
 * Constructor that set the number of free slots
 * @param freeSlots
 * 		Number of free slots
 */
	public StationBikeCounters (int freeSlots) {
		this.freeSlots=freeSlots;
	}
	/**
	 * 
	 * @return true if there are bicycles in the station, false either
	 */
	public boolean isThereAnyBycicle() {
		if (numberElectrical==0 && numberMechanical==0) {return false;}
		else {return true;}
	}
	/**
	 * 
	 * @param bycicleType
	 * 		The type of bicycle
	 * @return if there are bicycle of this type in the station
	 */
	public boolean isThereAny (String bycicleType) {
		if(bycicleType == BycicleElectrical.typeWritten && numberElectrical>0) {return true;}
		if(bycicleType == BycicleMechanical.typeWritten && numberMechanical>0) {return true;}
		else {return false;}
	}
	/**
	 * 
	 * @param bycicleType
	 * 		The type of bicycle
	 * @return The number of bicycles of this type in the station
	 */
	public int howMany (String bycicleType) throws UnimplementedSubclassWithInputException {
		if(bycicleType == BycicleElectrical.typeWritten) {return numberElectrical;}
		else if(bycicleType == BycicleMechanical.typeWritten) {return numberMechanical;}
		else {throw new UnimplementedSubclassWithInputException("Bycicle",bycicleType);}
	}
	
	public void increaseBikeByType(Bycicle bycicle) throws UnimplementedSubclassWithoutInputException {
		if (bycicle instanceof BycicleElectrical) {numberElectrical++;freeSlots--;}
		else if (bycicle instanceof BycicleMechanical) {numberMechanical++;freeSlots--;}
		else if (bycicle == null) {}
		else {throw new UnimplementedSubclassWithoutInputException("Bycicle");}
	}
	
	public void reduceBikeByType(Bycicle bycicle) throws UnimplementedSubclassWithoutInputException {
		if (bycicle instanceof BycicleElectrical) {numberElectrical--;freeSlots++;}
		else if (bycicle instanceof BycicleMechanical) {numberMechanical--;freeSlots++;}
		else if (bycicle == null) {}
		else {throw new UnimplementedSubclassWithoutInputException("Bycicle");}
	}
	
	/**
	 * 
	 * @param bycicle
	 * 		The bicycle to be added
	 * @param slots
	 * 		The list of parking slots of the station
	 * @return the slot where the bike has been added
	 */
	
	public ParkingSlot addBike (Bycicle bycicle,ArrayList<ParkingSlot> slots) throws AddBikeFailException {  
	try {
		if (freeSlots>0){
			for (ParkingSlot slot:slots) { //adding the bike in an empty working slot
				if (slot.isStatus() && slot.getBycicle()==null) {
					try { //Prevent a wrong bycicle to be put in a slot !
						increaseBikeByType(bycicle);
						slot.setBycicle(bycicle);
						return slot;
					}
					catch(UnimplementedSubclassWithoutInputException e) {
						throw e; 
					}
				}
			}
		}	
		throw new StationFullException();
	}
	catch (UnimplementedSubclassWithoutInputException | StationFullException e ) {
		throw new AddBikeFailException(bycicle,e);
	}
	}
	
	public StationRemovingBycicle removeBike (String bycicleType, ArrayList<ParkingSlot> slots) throws RemoveBikeFailException {
	try {
		if (bycicleType!=BycicleElectrical.typeWritten && bycicleType!=BycicleMechanical.typeWritten) {
			throw new UnimplementedSubclassWithInputException("Bycicle",bycicleType);
		}
		if(bycicleType == BycicleElectrical.typeWritten && numberElectrical>0) {
			for (ParkingSlot slot:slots) {
				if (slot.isStatus() && slot.getBycicle() instanceof BycicleElectrical) {
					Bycicle userBike = slot.getBycicle();
					slot.setBycicle(null);
					numberElectrical--;
					freeSlots++;
					return(new StationRemovingBycicle(userBike,slot));
				}
			}
		}
		if(bycicleType == BycicleMechanical.typeWritten && numberMechanical>0) {
			for (ParkingSlot slot:slots) {
				if (slot.isStatus() && slot.getBycicle() instanceof BycicleMechanical) {
					Bycicle userBike = slot.getBycicle();
					slot.setBycicle(null);
					numberMechanical--;
					freeSlots++;
					return(new StationRemovingBycicle(userBike,slot));
				}
			}
		}
		throw new NoBycicleAvailableException(bycicleType);
	}
	catch(NoBycicleAvailableException |UnimplementedSubclassWithInputException e) {
		throw new RemoveBikeFailException(bycicleType,e);
	}
	}

	public int getFreeSlots() {
		return freeSlots;
	}

	public void setFreeSlots(int freeSlots) {
		this.freeSlots = freeSlots;
	}
	
	public void increaseFreeSlots() {this.freeSlots++;}
	
	public void reduceFreeSlots() {this.freeSlots--;}
	
	public boolean isThereFreeSlots() {
		return this.freeSlots > 0;
	}

	

}
