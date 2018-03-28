package myVelibCore.stationPackage;

import myVelibCore.byciclePackage.Bycicle;

public class StationRemovingBycicle {
	private Bycicle bycicle;
	private ParkingSlot slot;
	
	public StationRemovingBycicle(Bycicle bycicle, ParkingSlot slot) {
		super();
		this.bycicle = bycicle;
		this.slot = slot;
	}
	
	public Bycicle getBycicle() {
		return bycicle;
	}
	public void setBycicle(Bycicle bycicle) {
		this.bycicle = bycicle;
	}
	public ParkingSlot getSlot() {
		return slot;
	}
	public void setSlot(ParkingSlot slot) {
		this.slot = slot;
	}
	
	
}
