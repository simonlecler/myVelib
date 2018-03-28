package myVelibCore.exceptions;

public class NoBikeToReturnException extends Exception {
	
	@Override
	public String getMessage() {
		return "The user has no bike to return !";
	}
}
