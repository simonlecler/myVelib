package myVelibCore.stationPackage;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.byciclePackage.BycicleMechanical;
import myVelibCore.exceptions.SlotStatusFailException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.utilities.IDGenerator;
import myVelibCore.utilities.Time;
/**
 * <b>Parking slots are linked to stations. They can contain bycicles</b>
 * <p> A parking slot is characterized by :
 * <ul>
 * <li>A status (available or not)</li>
 * <li>A unique ID</li>
 * <li>Possibly a bycicle</li>
 * <li>A station</li>
 * </ul>
 * @author Edouard Benauw
 */
public class ParkingSlot {
	/**
	 * Status of the slot
	 */
	private boolean status; //true if online
	
	private boolean statusBeforeStationOff; //status before the whole station is put offline 
	/**
	 * ID of the slot
	 * <p>It is unique
	 * @see ParkingSlot#getId()
	 * @see IDGenerator
	 */
	private final int id;
	/**
	 * Bycicle potentially occupiying the slot
	 */
	private Bycicle bycicle = null;
	/**
	 * Station of the parking slot
	 * <p> Cannot be modified
	 */
	private final Station stationLinked;
	
	/**
	 * Constructeur ParkingSlot
	 * <p>A unique ID is given, the slot is by default free and works correctly (status true). It notifies the station that there is one more free slot
	 * </p>
	 * @param station
	 * 		The station where we place the parking slot
	 */
	public ParkingSlot(Station station) {
		super();
		this.id = IDGenerator.getInstance().getNextID();
		this.status = station.isOn();
		this.statusBeforeStationOff = station.isOn();
		this.stationLinked=station;
		station.slots.add(this);
		station.getStationBikeCounters().setFreeSlots(station.getStationBikeCounters().getFreeSlots()+ 1);
		
	}

	public boolean isStatus() {
		return status;
	}
	
	public void stationIsTurnedOff() throws SlotStatusFailException {
		boolean changed = (this.status); //If slot was working before the station is put off
		if (changed) {
			try {
				stationLinked.getStationBikeCounters().reduceBikeByType(bycicle);
				stationLinked.getStationBikeCounters().reduceFreeSlots();
				this.stationLinked.getStationStatitics().addParkingSlotStatusChangement(Time.getCurrentTime(), this, false,true);
				this.status = false;
			}
			catch(UnimplementedSubclassWithoutInputException e) {
				throw new SlotStatusFailException(false,e,this);
			}
		}
	}
	
	public void stationIsTurnedOn() throws SlotStatusFailException {
		boolean changed = (statusBeforeStationOff); //If the slot was on before the station was putt off
		if (changed) {
			try {
				stationLinked.getStationBikeCounters().increaseBikeByType(bycicle);
				stationLinked.getStationBikeCounters().increaseFreeSlots();
				this.stationLinked.getStationStatitics().addParkingSlotStatusChangement(Time.getCurrentTime(), this, true,false);
				this.status = true;
			}
			catch(UnimplementedSubclassWithoutInputException e) {
				throw new SlotStatusFailException(true,e,this);
			}
		}
	}
	
	public void setStatus(boolean statusWanted) throws SlotStatusFailException {  
		boolean statusBefore = this.getOccupationStatus();
		boolean changed = (statusBefore!=statusWanted);
		if(changed) {
				if(status) {
					try {
						stationLinked.getStationBikeCounters().increaseBikeByType(bycicle);
						stationLinked.getStationBikeCounters().increaseFreeSlots();
						this.stationLinked.getStationStatitics().addParkingSlotStatusChangement(Time.getCurrentTime(), this, statusWanted,statusBefore);
						this.status = statusWanted;
						this.statusBeforeStationOff = statusWanted;
					}
					catch(UnimplementedSubclassWithoutInputException e) {
						throw new SlotStatusFailException(true,e,this);
					}
				}
				else {
					try {
						stationLinked.getStationBikeCounters().reduceBikeByType(bycicle);
						stationLinked.getStationBikeCounters().reduceFreeSlots();
						this.stationLinked.getStationStatitics().addParkingSlotStatusChangement(Time.getCurrentTime(), this, statusWanted,statusBefore);
						this.status = statusWanted;
						this.statusBeforeStationOff = statusWanted;
					}
					catch(UnimplementedSubclassWithoutInputException e) {
						throw new SlotStatusFailException(false,e,this);
					}
				}	
			}
		}
	
	
	public Bycicle getBycicle() {
		return bycicle;
	}
	
	public void setBycicle(Bycicle bycicle) {
		this.bycicle = bycicle;
	}
	
	public int getId() {
		return id;
	}

	public Station getStationLinked() {
		return stationLinked;
	}
	/**
	 * 
	 * @return true if the slot is occupied by a bycicle or offline, false is it is free
	 */
	public boolean getOccupationStatus() {
		if(bycicle!=null || !status){return true;}
		else {return false;} //there is a bike
	}
	
	public void displayBike() {
		if(this.bycicle==null) {System.out.println("Bike in the slot : currently no bike.");}
		if(this.bycicle instanceof BycicleElectrical) {System.out.println("Bike in the slot : currently an electrical bike.");}
		if(this.bycicle instanceof BycicleMechanical) {System.out.println("Bike in the slot : currently  mechanical bike.");}
		else {System.out.println("Bike in the slot : unknown type of bike !!!");}
	}
	
	public void display() {
		System.out.println("Parking slot id : " +this.id);
		System.out.println("Is online : " + this.status);
		this.displayBike();
	}

}
