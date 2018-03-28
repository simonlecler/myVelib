package myVelibCore.exceptions;

public class NoStartingStationAvailableException extends Exception{
	private static final long serialVersionUID = -4855095237139249880L;

	public NoStartingStationAvailableException() {
		super();
	}

	@Override
	public String getMessage() {
		return "All stations are empty, no starting station available";
	}
}
