package myVelibCore.exceptions;

public class PlanningRideFailException extends Exception {
	private Exception reason;

	public PlanningRideFailException(Exception reason) {
		super();
		this.reason = reason;
		this.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "The class user was unable to perform correctly the planningRide method because : \r\n"+reason.getMessage();
	}

}
