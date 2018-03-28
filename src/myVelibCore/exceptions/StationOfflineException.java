package myVelibCore.exceptions;

public class StationOfflineException extends Exception {
	
	@Override
	public String getMessage() {
		return "The station is offline. Unable to do any operation until it is put back online !";
	}
}
