package myVelibCore.exceptions;

public class StationFullException extends Exception{
	
	@Override
	public String getMessage() {
		return("This station is full (no working and free parking slot), you can't return a bike there" );
	}
	
}	
