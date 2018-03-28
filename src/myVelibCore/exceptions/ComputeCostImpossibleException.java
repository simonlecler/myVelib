package myVelibCore.exceptions;

public class ComputeCostImpossibleException extends Exception{
	private Exception reason;

	public ComputeCostImpossibleException(Exception reason) {
		super();
		this.reason = reason;
		this.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "Impossible to compute the cost of the ride because : \r\n" + reason.getMessage();
	}
	
}
