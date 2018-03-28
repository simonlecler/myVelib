package myVelibCore.userAndCardPackage;

import myVelibCore.utilities.GPSLocation;
/**
 * <b>This class stores the last inputs of the user</b>
 * It stores :
 * <ul>
 * <li>The GPS coordinates of the last wanted destination</li>
 * <li>The last wanted policy</li>
 * <li>The last wanted type of bicycle</li>
 * </ul>
 * @author Edouard Benauw
 *
 */
public class UserLastInput {
	/**
	 * GPS coordinates of the last wanted destination
	 */
	private GPSLocation lastWantedDestination;
	/**
	 * Last wanted policy
	 */
	private String lastWantedPolicy;
	/**
	 * Last wanted type of bicycle
	 */
	private String lastWantedBycicle;
	/**
	 * Default constructor
	 */
	public UserLastInput() {
		super();
	}
	
	
	/**
	 * Constructor with parameters
	 * @param lastWantedDestination
	 * @param lastWantedPolicy
	 * @param lastWantedBycicle
	 */
	public UserLastInput(GPSLocation lastWantedDestination, String lastWantedPolicy, String lastWantedBycicle) {
		super();
		this.lastWantedDestination = lastWantedDestination;
		this.lastWantedPolicy = lastWantedPolicy;
		this.lastWantedBycicle = lastWantedBycicle;
	}



	public GPSLocation getLastWantedDestination() {
		return lastWantedDestination;
	}
	public void setLastWantedDestination(GPSLocation lastWantedDestination) {
		this.lastWantedDestination = lastWantedDestination;
	}
	public String getLastWantedPolicy() {
		return lastWantedPolicy;
	}
	public void setLastWantedPolicy(String lastWantedPolicy) {
		this.lastWantedPolicy = lastWantedPolicy;
	}
	public String getLastWantedBycicle() {
		return lastWantedBycicle;
	}
	public void setLastWantedBycicle(String lastWantedBycicle) {
		this.lastWantedBycicle = lastWantedBycicle;
	}
	
	
}
