package myVelibCore.exceptions;

public class NoBikeToComputeCostException extends Exception {
	
	@Override
	public String getMessage() {
		return "Impossible to compute the cost of the ride because the user don't have any bike";
	}
}
