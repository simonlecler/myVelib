package myVelibCore.exceptions;

public class NoBycicleAvailableException extends Exception{
	String bicycleType;
	

	public NoBycicleAvailableException(String bicycleType) {
		super();
		this.bicycleType = bicycleType;
	}


	@Override
	public String getMessage() {
		return  ("No" + " "+ this.bicycleType + " "+ "available in this station. Either there aren't any or the parking slot are not working. Sorry for the incovenience !");
	}

}
