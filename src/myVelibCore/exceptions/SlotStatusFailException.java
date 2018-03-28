package myVelibCore.exceptions;

import myVelibCore.stationPackage.ParkingSlot;

public class SlotStatusFailException extends Exception {
	private boolean stateTriedToBeAchieved; //The status the slot should have acquired but has not, because of this exception
	private ParkingSlot slotConcerned;
	private Exception reason;
	
	public SlotStatusFailException(boolean stateTriedToBeAchieved, Exception reason, ParkingSlot slotConcerned) {
		super();
		this.stateTriedToBeAchieved = stateTriedToBeAchieved;
		this.reason=reason;
		super.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		if (stateTriedToBeAchieved) {
			return "Impossible to put the Parking Slot Online because : \r\n" + reason.getMessage();
		}
		else {
			return "Impossible to put the Parking Slot Offline because : \r\n" + reason.getMessage();
		}
	}
	
}
