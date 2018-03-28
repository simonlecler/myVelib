package myVelibCore.userAndCardPackage;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;

public interface CardVisitor {
	//Exception are never thrown due to the construction of the visitor
	int visit(CardNone card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
	int visit(CardVLibre card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
	int visit(CardVMax card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
}
