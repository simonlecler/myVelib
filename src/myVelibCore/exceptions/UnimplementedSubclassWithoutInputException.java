package myVelibCore.exceptions;

public class UnimplementedSubclassWithoutInputException extends Exception {
		private static final long serialVersionUID = 5530717093433683944L;
		private String objectType; //Superclass
		// Remark : we could pass the faulty object in attribute for debugging, but that will be useless for a standard user
		
		
		public UnimplementedSubclassWithoutInputException(String objectType) {
			super();
			this.objectType = objectType;
			
		}

		@Override
		public String getMessage() {
			return "You're trying to use a not-fully (or not at all) implemented type of " + objectType +".";
		}
	}

