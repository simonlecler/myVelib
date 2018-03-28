package myVelibCore.exceptions;

public class RecalculatePathFailedException extends Exception {
	private String policy;
	private Exception reason;
	
	public RecalculatePathFailedException(String policy, Exception reason) {
		super();
		this.policy = policy;
		this.reason=reason;
		super.initCause(reason); //store the reason why the exception was thrown
	}
	
	@Override
	public String getMessage() {
		return "The policy " + policy + "that you have selected before is unable to recalculate a path because : \r\n" + reason.getMessage();
	}
}
