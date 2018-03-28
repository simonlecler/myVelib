package myVelibCore.userAndCardPackage;
/**
 * <b>This class stores statistics for each user</b>
 * <p>The data stored are :
 * <ul>
 * <li>Number of user's rides</li>
 * <li>The total time the user spent on a bike</li>
 * <li>The total amount of charges</li>
 * <li>The total time-credit earned by the user</li>
 * </ul>
 * @author Edouard Benauw
 *
 */
public class UserStatitics {
	/**
	 * Number of user's rides
	 */
	private int numberOfRides = 0;
	/**
	 * The total time the user spent on a bike
	 */
	private int totalTimeSpentOnABikeWithoutActualRide = 0;
	/**
	 * The total amount of charges
	 */
	private int totalAmountOfCharges = 0;
	/**
	 * The time-credit earned by the user
	 */
	private int totalTimeCredit;
	
	public UserStatitics() {
		super();
	}

	/**
	 * Constructor with parameters
	 * @param numberOfRides
	 * @param totalTimeSpentOnABike
	 * @param totalAmountOfCharges
	 */
	public UserStatitics(int numberOfRides, int totalTimeSpentOnABike, int totalAmountOfCharges, int totalTimeCredit) {
		super();
		this.numberOfRides = numberOfRides;
		this.totalTimeSpentOnABikeWithoutActualRide = totalTimeSpentOnABike;
		this.totalAmountOfCharges = totalAmountOfCharges;
		this.totalTimeCredit = totalTimeCredit;
	}
	
	public int getNumberOfRides() {
		return this.numberOfRides;
	}
	public void setNumberOfRides(int numberOfRides) {
		this.numberOfRides = numberOfRides;
	}
	public int getTotalTimeSpentOnABikeWithoutActualRide() {
		return totalTimeSpentOnABikeWithoutActualRide;
	}
	public void setTotalTimeSpentOnABikeWithoutActualRide(int totalTimeSpentOnABike) {
		this.totalTimeSpentOnABikeWithoutActualRide = totalTimeSpentOnABike;
	}
	public int getTotalAmountOfCharges() {
		return totalAmountOfCharges;
	}
	public void setTotalAmountOfCharges(int totalAmountOfCharges) {
		this.totalAmountOfCharges = totalAmountOfCharges;
	}
	
	public int getTimeCredit() {
		return totalTimeCredit;
	}

	public void setTimeCredit(int timeCredit) {
		this.totalTimeCredit = timeCredit;
	}

	/**
	 * Increase the number of rides by a certain integer
	 * @param number
	 * 		Number of additional rides
	 */
	public void increaseNumberOfRidesBy(int number) {
		this.numberOfRides=numberOfRides+number;
	}
	
	/**
	 * Increase total time spent on bike by a number of minutes
	 * @param number
	 * 		Number of added minutes
	 */
	public void increaseTotalTimeSpentOnABikeBy(int number) {
		this.totalTimeSpentOnABikeWithoutActualRide=totalTimeSpentOnABikeWithoutActualRide+number;
	}
	/**
	 * Increase the total amount of charges by a certain integer
	 * @param number
	 * 		Amount of added charges
	 */
	public void increaseTotalAmountOfChargesBy(int number) {
		this.totalAmountOfCharges=totalAmountOfCharges+number;
	}
	/**
	 * Increase total time-credit by a certain integer
	 * @param number
	 * 		Minutes added
	 */
	public void increaseTotalTimeCreditBy(int number) {
		this.totalTimeCredit = totalTimeCredit + number;
	}
}
