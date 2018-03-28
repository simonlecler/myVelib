package myVelibCore.exceptions;

public class UnimplementedSubclassWithInputException extends Exception {
	// Exception called when the user tries to use a subclass not fully (or not at all) implemented
	private static final long serialVersionUID = 5530717093433683944L;
	private String objectType; //The superclass
	private String wrongInput; //The faulty input
	

	public UnimplementedSubclassWithInputException(String objectType, String wrongInput) {
		super();
		this.objectType = objectType;
		this.wrongInput = wrongInput;
	}

	@Override
	public String getMessage() {
		return "You're trying to use a not-fully (or not at all) implemented type of " + objectType +". You've typed "+ wrongInput +". You might have made a typo !";
	}
	
}
