package myVelibCore.exceptions;

public class UnexistingStationIDException extends Exception {
	private int unexistingID;

	public UnexistingStationIDException(int unexistingID) {
		super();
		this.unexistingID = unexistingID;
	}
	
	@Override
	public String getMessage() {
		return "The following ID : " + unexistingID + "could not be find in station database.\r\n";
	}

}
