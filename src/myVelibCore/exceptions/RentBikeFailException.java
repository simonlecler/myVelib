package myVelibCore.exceptions;

public class RentBikeFailException extends Exception {
	private Exception reason;

	public RentBikeFailException(Exception reason) {
		super();
		this.reason= reason;
		this.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "Impossible to rent a bike from the station because : \r\n" + reason.getMessage();
	}
}
