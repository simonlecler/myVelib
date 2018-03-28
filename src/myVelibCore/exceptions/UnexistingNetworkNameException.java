package myVelibCore.exceptions;

public class UnexistingNetworkNameException extends Exception {
	private String unexistingName;
	
	
	
	public UnexistingNetworkNameException(String unexistingName) {
		super();
		this.unexistingName = unexistingName;
	}



	@Override
	public String getMessage() {
		return "The following name : " + unexistingName + "could not be find in network database.\r\n";
	}
}
