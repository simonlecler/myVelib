package myVelibCore.exceptions;

public class NoDestinationStationAvailableException extends Exception{

	public NoDestinationStationAvailableException() {
		super();
	}

	@Override
	public String getMessage() {
		return "All stations are full, no destination station available";
	}


	
}
