package myVelibCore.exceptions;

import myVelibCore.byciclePackage.Bycicle;

public class AddBikeFailException extends Exception {
	private Bycicle bycicleConcerned;
	private Exception reason;
	
	public AddBikeFailException(Bycicle bycicleConcerned, Exception reason) {
		super();
		this.bycicleConcerned = bycicleConcerned;
		this.reason = reason;
		super.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "Impossible to add a bike because : \r\n" + reason.getMessage();
	}
}
