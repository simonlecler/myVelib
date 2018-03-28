package myVelibCore.userAndCardPackage;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
/**
 * Default type of card, that correspond to the case a user doesn't have a card
 * @author USER
 *
 */
public class CardNone extends Card {
	/**
	 * The amount on the card, equal to zero and cannot be changed because this is not a card
	 */
	private final int  balance = 0;
	/**
	 * Constructor of the card
	 */
	public CardNone() {
		super();
	}


	@Override
	public int accept (CardVisitor visitor, Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException {
		return visitor.visit(this,bycicle,minutes);
	}
	
	
	@Override
	public void setBalance(int balance) throws CardNoneNoBalanceException {
		throw new CardNoneNoBalanceException();
	}
	
	@Override
	public void increaseBalance(int augmentation) throws CardNoneNoBalanceException  {
		throw new CardNoneNoBalanceException();
	}
}
