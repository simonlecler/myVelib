package myVelibCore.exceptions;

public class RentTwoBikeException extends Exception {

	@Override
	public String getMessage() {
		return "The user is trying to rent a second bike, as he already has one";
	}
}
