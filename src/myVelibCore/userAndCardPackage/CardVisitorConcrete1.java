package myVelibCore.userAndCardPackage;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.byciclePackage.BycicleMechanical;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;

public class CardVisitorConcrete1 implements CardVisitor {
	
	public int getOptimalRetrievingFromBalance(int minutes, int balance, int numberOfFreeHour) {
		int balanceNeededToGoTo60Multiple = minutes%60;
		if(balance>balanceNeededToGoTo60Multiple) {
			balance = balance - balanceNeededToGoTo60Multiple;
			minutes = minutes - balanceNeededToGoTo60Multiple;
			if (minutes>60*numberOfFreeHour) {
				int numberOfHourToRetrieveFromMinutes = Math.max(minutes/60 - numberOfFreeHour,0);
				int numberOfMinutesEffectivelyRetrieveFromBalance = 60*Math.min(numberOfHourToRetrieveFromMinutes, balance/60);
				return(balanceNeededToGoTo60Multiple + numberOfMinutesEffectivelyRetrieveFromBalance);
			}
			return(balanceNeededToGoTo60Multiple);
		}
		return(0);
	}
	
	@Override
	public int visit(CardNone card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException {
		if (bycicle instanceof BycicleMechanical) {
			return Math.max((((minutes-1)/60)+1),0); 
			}	
		if (bycicle instanceof BycicleElectrical) {
				return Math.max((((minutes-1)/60)+1)*2,0);
		}
		else {
			throw new UnimplementedSubclassWithoutInputException("Bycicle");
		}
	}

	@Override
	public int visit(CardVLibre card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException {
		int balance = card.getBalance();
		if (bycicle instanceof BycicleMechanical) {
			int minutesRetrievedFromBalance =this.getOptimalRetrievingFromBalance(minutes, balance, 1);
			try{card.setBalance(balance- minutesRetrievedFromBalance);}
			catch(CardNoneNoBalanceException e) {System.out.println("NOT SUPPOSED TO HAPPEN, ERROR IN VISITOR PATTERN" + e.getMessage());} //Can't happen by construction !
			minutes = minutes - minutesRetrievedFromBalance;
			return Math.max(((minutes-1)/60),0);
		}	
		if (bycicle instanceof BycicleElectrical) {
			int minutesRetrievedFromBalance =this.getOptimalRetrievingFromBalance(minutes, balance, 0);
			try{card.setBalance(balance- minutesRetrievedFromBalance);} 
			catch(CardNoneNoBalanceException e) {System.out.println("NOT SUPPOSED TO HAPPEN, ERROR IN VISITOR PATTERN" + e.getMessage());} //Can't happen by construction !
			minutes = minutes - minutesRetrievedFromBalance;
			return Math.max(((minutes-1)/60)*2+1,0);
		}
		else {
			throw new UnimplementedSubclassWithoutInputException("Bycicle");
		}
	}

	@Override
	public int visit(CardVMax card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException {
		int balance = card.getBalance();
		if (bycicle instanceof BycicleMechanical) {
			int minutesRetrievedFromBalance =this.getOptimalRetrievingFromBalance(minutes, balance, 1);
			try{card.setBalance(balance- minutesRetrievedFromBalance);}
			catch(CardNoneNoBalanceException e) {System.out.println("NOT SUPPOSED TO HAPPEN, ERROR IN VISITOR PATTERN" + e.getMessage());} //Can't happen by construction !
			minutes = minutes - minutesRetrievedFromBalance;
			return Math.max(((minutes-1)/60),0);
		}	
		if (bycicle instanceof BycicleElectrical) {
			int minutesRetrievedFromBalance =this.getOptimalRetrievingFromBalance(minutes, balance, 1);
			try{card.setBalance(balance- minutesRetrievedFromBalance);}
			catch(CardNoneNoBalanceException e) {System.out.println("NOT SUPPOSED TO HAPPEN, ERROR IN VISITOR PATTERN" + e.getMessage());} //Can't happen by construction !
			minutes = minutes - minutesRetrievedFromBalance;
			return Math.max(((minutes-1)/60),0);
		}
		else {
			throw new UnimplementedSubclassWithoutInputException("Bycicle");
		}
	}

}
