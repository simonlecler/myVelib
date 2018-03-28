package myVelibCore.exceptions;

public class BadSpeedSelectionException extends Exception {
	private static final long serialVersionUID = 1982060044176771196L;
	private String wrongInput; //Input from the user or the program

	public BadSpeedSelectionException(String wrongInput) {
		super();
		this.wrongInput = wrongInput;
	}
	
	@Override
	public String getMessage() {
		return "You've tried to obtain the speed of the following type of transport : " + wrongInput + " . This is not a valid type of transport.";
	}
}
