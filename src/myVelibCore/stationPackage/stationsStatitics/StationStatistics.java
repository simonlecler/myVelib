package myVelibCore.stationPackage.stationsStatitics;

import java.util.ArrayList;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.StationOperationComparatorByDate;
import myVelibCore.utilities.Time;
/**
 * Class computing statistics on stations
 * @author Edouard Benauw
 *
 */
public class StationStatistics {
	/**
	 * List of all operations performed on a station
	 */
	private ArrayList<StationOperation> allOperations = new ArrayList<StationOperation>();
	/**
	 * List of renting operations performed on a station
	 */
	private ArrayList<StationUserOperationRent> rentOperations = new ArrayList<StationUserOperationRent>();
	/**
	 * List of returning operations performed on a station
	 */
	private ArrayList<StationUserOperationReturn> returnOperations = new ArrayList<StationUserOperationReturn>();
	//private ArrayList<ParkingSlotOperationOccupation> parkingSlotOccupations = new ArrayList<ParkingSlotOperationOccupation>;
	
	
	/**
	 * Total number of operations
	 */
	private int allOperationsNumber; //Renting and returning bike
	/**
	 * Number of renting operations
	 */
	private int rentOperationsNumber;
	/**
	 * Number of returning operations
	 */
	private int returnOperationsNumber;
	
	/**
	 * Constructor of the station statistics
	 */
	public StationStatistics() {
		super();
	}
	
	

	public int getAllOperationsNumber() {
		return allOperationsNumber;
	}



	public int getRentOperationsNumber() {
		return rentOperationsNumber;
	}



	public int getReturnOperationsNumber() {
		return returnOperationsNumber;
	}


	/**
	 * Add a renting operation to the lists of operations and increase the operation numbers
	 * @param userConcerned
	 * 		User concerned by this operations
	 * @param dateOfOperation
	 * 		Time when the operation is performed
	 * @param parkingSlotConcerned
	 * 		Parking slot concerned by the operation
	 */
	public void addRentOperation(User userConcerned, Time dateOfOperation, ParkingSlot parkingSlotConcerned) {
		StationUserOperationRent operation = new StationUserOperationRent(userConcerned,dateOfOperation, parkingSlotConcerned,false,true);
		allOperations.add(operation);
		rentOperations.add(operation);
		this.allOperationsNumber++;
		this.rentOperationsNumber++;
	}
	
	/**
	 * Add a returning operation to the lists of operations and increase the operation numbers
	 * @param userConcerned
	 * 		User concerned by this operations
	 * @param dateOfOperation
	 * 		Time when the operation is performed
	 * @param parkingSlotConcerned
	 * 		Parking slot concerned by the operation
	 */
	public void addReturnOperation(User userConcerned, Time dateOfOperation, ParkingSlot parkingSlotConcerned) {
		StationUserOperationReturn operation = new StationUserOperationReturn(userConcerned,dateOfOperation,parkingSlotConcerned,true,false);
		allOperations.add(operation);
		returnOperations.add(operation);
		this.allOperationsNumber++;
		this.returnOperationsNumber++;
	}
	
	/**
	 * When the status of a slot changed, it is registered as an operation
	 * @param dateOfOperation
	 * 		Time when the operation is performed
	 * @param parkingSlotConcerned
	 * 		Parking slot concerned by the operation
	 * @param isParkingSlotOccupiedAfter
	 * 		true if the slot is occupied after the operation, false either
	 * @param wasParkingSlotOccupiedBefore
	 * 		true if the slot is occupied before the operation, false either
	 */
	public void addParkingSlotStatusChangement(Time dateOfOperation, ParkingSlot parkingSlotConcerned, boolean isParkingSlotOccupiedAfter,boolean wasParkingSlotOccupiedBefore) {
		StationOperationChangingStatusParkingSlot operation = new StationOperationChangingStatusParkingSlot(dateOfOperation,parkingSlotConcerned,isParkingSlotOccupiedAfter,wasParkingSlotOccupiedBefore);
		allOperations.add(operation);
	}
	/**
	 * 
	 * @param slots
	 * 		List of the slots of the station
	 * @param beginningTime
	 * 		Beginning time of the interval when the statistics are computed
	 * @param endingTime
	 * 		Ending time of the interval when the statistics are computed	
	 * @return The average occupation rate of the station
	 */
	public double computeAverageOccupationRate (ArrayList<ParkingSlot> slots, Time beginningTime, Time endingTime){
		int totalOnlineTime = 0;
		int totalNumberOfParkingSlot = 0;
		for(ParkingSlot currentSlot : slots) {
			totalNumberOfParkingSlot++;
			ArrayList<StationOperation> operationsCurrentSlot = new ArrayList<StationOperation>();
			for(StationOperation currentOperation : this.allOperations) {
				if (currentOperation.getParkingSlotConcerned() == currentSlot && currentOperation.getDateOfOperation().isAfter(beginningTime) && currentOperation.getDateOfOperation().isBefore(endingTime)) {
					operationsCurrentSlot.add(currentOperation);
				}
			}
			operationsCurrentSlot.sort(new StationOperationComparatorByDate());
			boolean currentStatus = operationsCurrentSlot.get(0).wasParkingSlotOccupiedBefore();
			Time currentTime = beginningTime;
			for (StationOperation currentOperation : operationsCurrentSlot) {
				if(currentOperation.isParkingSlotOccupiedAfter()!=currentStatus) {
					if(currentStatus) {
						totalOnlineTime = totalOnlineTime + Time.timeDifference(currentTime, currentOperation.getDateOfOperation());
					}
					currentStatus=currentOperation.isParkingSlotOccupiedAfter();
					currentTime=currentOperation.getDateOfOperation();
				}
				if (currentStatus) {
					totalOnlineTime = totalOnlineTime + Time.timeDifference(currentTime, endingTime);
				}
			}
		}
		
		return (float) totalOnlineTime / ((float) totalNumberOfParkingSlot * (float) Time.timeDifference(endingTime, beginningTime));
	}

}
