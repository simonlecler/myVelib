package myVelibCore.exceptions;

public class CardNoneNoBalanceException extends Exception {
	private static final long serialVersionUID = -428692902745066439L;
	
	@Override
	public String getMessage() {
		return "Warning, you're trying to give time credit to an user without a card";
	}
}
