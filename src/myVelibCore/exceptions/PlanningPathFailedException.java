package myVelibCore.exceptions;

public class PlanningPathFailedException extends Exception {
	private String policy;
	private Exception reason;
	
	public PlanningPathFailedException(String policy, Exception reason) {
		super();
		this.policy = policy;
		this.reason = reason;
		super.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "The policy " +policy+ "has failed to plan a path because : \r\n" + reason.getMessage();
	}
}
