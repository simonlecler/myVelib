package myVelibCore.utilities;
/**
 * <b> Singleton pattern that allows to generate a unique ID for the objects</b>
 * @author USER
 *
 */
public class IDGenerator {
	/**
	 * The unique instance of IDGenerator
	 */
	private static IDGenerator instance = null;
	/**
	 * The ID, it is an Integer. Each time an ID is demanded, it allocates the next Integer
	 */
	private int num;
	
	private IDGenerator() {}
	/**
	 * 
	 * @return The unique instance of IDGenerator
	 */
	public static synchronized IDGenerator getInstance() {
			if (instance==null) {
				instance = new IDGenerator();
			}
			return instance;
	}
	/**
	 * 
	 * @return The next ID
	 */
	public int getNextID() {
		this.num++;
		return num;
	}
	
}
