package myVelibCore.exceptions;

public class NotEnoughSlotsException extends Exception {
	private int nbikes;
	private int totalFreeSlots;
	
	public NotEnoughSlotsException(int nbikes, int totalFreeSlots) {
		super();
		this.nbikes = nbikes;
		this.totalFreeSlots = totalFreeSlots;
	}
	
	@Override
	public String getMessage() {
		return "Not enough slots to add to add the required amount of bikes ! You tried to add "+nbikes+" bikes but there is only" + totalFreeSlots + " free slots !";
	}
}
