package myVelibCore.userAndCardPackage;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
/**
 * Card Vmax
 * @author Simon Lecler
 *
 */
public class CardVMax extends Card {
/**
 * Default constructor, the balance is initialized to 0
 */
	public CardVMax() {
		super();
	}	
	/**
	 * Constructor that initializes the balance
	 * @param balance
	 * 		The initial balance on the card
	 */
	public CardVMax(int balance) {
		super(balance);
	}

	@Override
	public int accept (CardVisitor visitor,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException{
		return visitor.visit(this,bycicle,minutes);
	}
	
}
