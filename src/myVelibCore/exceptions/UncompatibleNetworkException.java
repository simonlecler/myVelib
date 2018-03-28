package myVelibCore.exceptions;

public class UncompatibleNetworkException extends Exception {
	// Error thrown when an operation is performed between 2 objects from different networks
	String objectType1;
	String objectType2;
	
	public UncompatibleNetworkException(String objectType1, String objectType2) {
		super();
		this.objectType1 = objectType1;
		this.objectType2 = objectType2;
	}
	
	@Override
	public String getMessage() {
		return "You're trying to perform an operation between two objects from different networks. The 2 objects concern are of type "+ objectType1+" and "+ objectType2;
	}
}
