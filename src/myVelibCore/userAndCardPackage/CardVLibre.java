package myVelibCore.userAndCardPackage;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
/**
 * Card Vlibre
 * @author Simon Lecler
 *
 */
public class CardVLibre extends Card {
/**
 * Default constructor, the balance is initialized to 0
 */
	public CardVLibre() {
		super();
	}
	/**
	 * Constructor with initial balance
	 * @param balance
	 * 		The initial balance on the card
	 */
	public CardVLibre(int balance) {
		super(balance);
	}

	@Override
	public int accept (CardVisitor visitor, Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException {
		return visitor.visit(this,bycicle,minutes);
	}
	
}
