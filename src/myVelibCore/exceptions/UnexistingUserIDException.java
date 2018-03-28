package myVelibCore.exceptions;

public class UnexistingUserIDException extends Exception {
	private int unexistingID;

	public UnexistingUserIDException(int unexistingID) {
		super();
		this.unexistingID = unexistingID;
	}
	
	@Override
	public String getMessage() {
		return "The following ID : " + unexistingID + "could not be find in user database.\r\n";
	}

}
