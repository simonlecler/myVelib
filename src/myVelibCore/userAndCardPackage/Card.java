package myVelibCore.userAndCardPackage;



import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.utilities.IDGenerator;
/**
 * This defines the different cards that a user can have
 * @author Simon Lecler
 *
 */
public abstract class Card {
	/**
	 * The amount registered on the card
	 */
	private int balance;
	/**
	 * The unique ID of the card
	 */
	private final int id;
	/**
	 * The object computing statistics for this card
	 */
	private CardStatitics cardStatitics;
	/**
	 * Default constructor of the card
	 * <p> By default the balance is 0</p>
	 */
	public Card() {
		super();
		this.balance = 0;
		this.id = IDGenerator.getInstance().getNextID();
	}
/**
 * Constructor of the card setting the balance
 * @param balance
 * 		The initial amount on the card
 */
	public Card(int balance) {
		super();
		this.balance = balance;
		this.id = IDGenerator.getInstance().getNextID();
	}


	/**
	 * Accept method of the observer pattern
	 * @param visitor
	 * @param bycicle
	 * @param minutes
	 * @return
	 */
	public abstract int accept (CardVisitor visitor,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) throws CardNoneNoBalanceException {
		this.balance = balance;
	}

	public void increaseBalance(int augmentation) throws CardNoneNoBalanceException {
		this.balance = balance + augmentation;
		this.cardStatitics.increaseTotalTimeCreditEarned(augmentation);
	}

	public int getId() {
		return id;
	}

	public CardStatitics getCardStatitics() {
		return cardStatitics;
	}
	
	
	
}
