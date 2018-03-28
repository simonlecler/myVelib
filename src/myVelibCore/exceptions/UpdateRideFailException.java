package myVelibCore.exceptions;

public class UpdateRideFailException extends Exception {
	private Exception reason;

	public UpdateRideFailException(Exception reason) {
		super();
		this.reason = reason;
		this.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "The user class failed to update the ride because : " + reason.getMessage();
	}
}
