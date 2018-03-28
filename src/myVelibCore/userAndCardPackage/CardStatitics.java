package myVelibCore.userAndCardPackage;
/**
 * This class records the statistics of this card
 * @author Edouard Benauw
 *
 */
public class CardStatitics {
	/**
	 * The total amount of time earned by the user of this card
	 */
	private int totalTimeCreditEarned;
	/**
	 * Default constructor
	 */
	public CardStatitics() {
		super();
	}

	public int getTotalTimeCreditEarned() {
		return totalTimeCreditEarned;
	}

	public void setTotalTimeCreditEarned(int totalTimeCreditEarned) {
		this.totalTimeCreditEarned = totalTimeCreditEarned;
	}
/**
 * 
 * @param increasing
 * 		The amount of time we want to add to the card
 * 
 */
	public void increaseTotalTimeCreditEarned(int increasing) {
		this.totalTimeCreditEarned = totalTimeCreditEarned+increasing;
	}
}
