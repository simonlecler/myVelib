package myVelibCore.utilities;

import java.util.Comparator;

import myVelibCore.stationPackage.stationsStatitics.StationOperation;
/**
 * <b>Comparator for operation on stations according the date</b>
 * <p>This class allows to compare the operation on stations according to their date
 * @author Edouard Benauw
 *
 */
public class StationOperationComparatorByDate implements Comparator<StationOperation>{
	
	/**
	 * @param op1
	 * 		The first operation
	 * @param op2
	 * 		The second operation
	 * @return 1 op2 precedes op1, -1 if op1 precedes op2, 0 if op1 and op2 are simultaneous
	 */	
	@Override
	public int compare(StationOperation op1, StationOperation op2) { //by Date
		return(new TimeComparator().compare(op1.getDateOfOperation(), op2.getDateOfOperation()));
	}
}
