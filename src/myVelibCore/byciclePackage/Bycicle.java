package myVelibCore.byciclePackage;

import myVelibCore.utilities.IDGenerator;

public abstract class Bycicle {
	private final int id;
	/**
	 * Constructor of Bycicle
	 * Give a unique ID to the bycicle
	 */
	public Bycicle() {
		super();
		this.id = IDGenerator.getInstance().getNextID();
	}
	

	@Override
	public String toString() {
		return "Bycicle [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	
}
