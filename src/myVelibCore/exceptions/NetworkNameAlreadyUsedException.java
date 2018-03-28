package myVelibCore.exceptions;

public class NetworkNameAlreadyUsedException extends Exception {
	private String nameAlreadyUsed;

	public NetworkNameAlreadyUsedException(String nameAlreadyUsed) {
		super();
		this.nameAlreadyUsed = nameAlreadyUsed;
	}
	
	@Override
	public String getMessage() {
		return "The following network name : " + this.nameAlreadyUsed + "is already used. Please pick an other one !";
	}
}
