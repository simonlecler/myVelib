package myVelibCore.exceptions;

public class ReturnBikeFailException extends Exception {
	private Exception reason;

	public ReturnBikeFailException(Exception reason) {
		super();
		this.reason= reason;
		this.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "Impossible to return a bike to the station because : \r\n" + reason.getMessage();
	}

}
