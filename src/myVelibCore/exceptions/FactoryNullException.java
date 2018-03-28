package myVelibCore.exceptions;

public class FactoryNullException extends Exception {
	private String factoryUsed;
	private String classWished;
	
	public FactoryNullException(String factoryUsed, String classWished) {
		super();
		this.factoryUsed = factoryUsed;
		this.classWished = classWished;
	}
	
	@Override
	public String getMessage() {
		return "You tried to use the factory for the following class : " + factoryUsed + " to instantiate the following class : "+ classWished+". This is considered an incorrect operation !";
	}
	
}
