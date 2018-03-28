package myVelibCore.exceptions;

import myVelibCore.byciclePackage.Bycicle;

public class RemoveBikeFailException extends Exception {
	private String bycicleType;
	private Exception reason;
	
	public RemoveBikeFailException(String bycicleType, Exception reason) {
		super();
		this.bycicleType= bycicleType;
		this.reason = reason;
		super.initCause(reason);
	}
	
	@Override
	public String getMessage() {
		return "Impossible to remove the following type of bike : " + bycicleType +" from the station because : \r\n" + reason.getMessage();
	}
}
