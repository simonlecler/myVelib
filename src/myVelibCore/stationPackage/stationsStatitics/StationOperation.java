package myVelibCore.stationPackage.stationsStatitics;

import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.utilities.Time;
/**
 * Operation performed on a given station
 * @author Edouard Benauw
 *
 */
public abstract class StationOperation {
	/**
	 * Time registered when the operation is performed
	 */
	private Time dateOfOperation;
	/**
	 * Parking slot concerned by this operation
	 */
	private ParkingSlot parkingSlotConcerned;
	/**
	 * true if the slot is occupied after the operation, false either
	 */
	private boolean isParkingSlotOccupiedAfter;
	/**
	 * true if the slot was occupied before the operation, false either
	 */
	private boolean wasParkingSlotOccupiedBefore;
	/**
	 * Constructor of the StationOperation
	 * 
	 */
	public StationOperation( Time dateOfOperation, ParkingSlot parkingSlotConcerned,boolean isParkingSlotOccupiedAfter, boolean wasParkingSlotOccupiedBefore) {
		super();
		this.dateOfOperation = dateOfOperation;
		this.parkingSlotConcerned = parkingSlotConcerned;
		this.isParkingSlotOccupiedAfter = isParkingSlotOccupiedAfter;
		this.wasParkingSlotOccupiedBefore=wasParkingSlotOccupiedBefore;
	}

	public Time getDateOfOperation() {
		return dateOfOperation;
	}

	public void setDateOfOperation(Time dateOfOperation) {
		this.dateOfOperation = dateOfOperation;
	}

	public ParkingSlot getParkingSlotConcerned() {
		return parkingSlotConcerned;
	}

	public void setParkingSlotConcerned(ParkingSlot parkingSlotConcerned) {
		this.parkingSlotConcerned = parkingSlotConcerned;
	}

	public boolean isParkingSlotOccupiedAfter() {
		return isParkingSlotOccupiedAfter;
	}

	public void setParkingSlotOccupiedAfter(boolean isParkingSlotOccupiedAfter) {
		this.isParkingSlotOccupiedAfter = isParkingSlotOccupiedAfter;
	}

	public boolean wasParkingSlotOccupiedBefore() {
		return wasParkingSlotOccupiedBefore;
	}

	public void setWasParkingSlotOccupiedBefore(boolean wasParkingSlotOccupiedBefore) {
		this.wasParkingSlotOccupiedBefore = wasParkingSlotOccupiedBefore;
	}
	
	
	

	
}
