package myVelibCore.byciclePackage;

import myVelibCore.exceptions.BadSpeedSelectionException;

/**
 * This class allows to store the average speeds of the different means of transport
 */
public abstract class BycicleSpeeds {
	
	public static int getSpeed(String transportType) throws BadSpeedSelectionException {
		if(transportType.equalsIgnoreCase(BycicleElectrical.typeWritten)) {return 20;}
		if(transportType.equalsIgnoreCase(BycicleMechanical.typeWritten)) {return 15;}
		if(transportType.equalsIgnoreCase("Foot")) {return 4;}
		else {throw new BadSpeedSelectionException(transportType);}
	}
}
