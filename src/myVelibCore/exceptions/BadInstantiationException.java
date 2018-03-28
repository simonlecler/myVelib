package myVelibCore.exceptions;

public class BadInstantiationException extends Exception{
	private static final long serialVersionUID = 6295451575248835260L;
	private String wrongInput; // The input in string (from the user or typed somewhere in the code
	private String objectType; //The class which was supposed to be instantiated (but it failed)
	
	
	
	public BadInstantiationException(String wrongInput, String objectType) {
		this.wrongInput=wrongInput;
		this.objectType=objectType;
	}
	
	
	@Override
	public String getMessage() {
		return "You're trying to instantiate a " + objectType + " but " + wrongInput + " is not a valid input for this type of object.";
	}

}
