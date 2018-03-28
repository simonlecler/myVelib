package myVelibCore.planningPolicyPackage;

import myVelibCore.exceptions.BadInstantiationException;

public class PolicyChoice {
	public static PlanningPolicy getPolicy(String policy) throws BadInstantiationException {
		if(policy.equalsIgnoreCase("Avoid Plus Stations")) {return new AvoidPlusStation();}
		if(policy.equalsIgnoreCase("Prefer Plus Stations")) {return new PreferPlusStation();}
		if(policy.equalsIgnoreCase("Uniformity Policy")) {return new UniformityPolicy();}
		if(policy.equalsIgnoreCase("Shortest Path")) {return new ShortestPath();}
		if(policy.equalsIgnoreCase("Fastest Path")) {return new FastestPath();}
		else{throw new BadInstantiationException(policy, "Policy");}
	}
}
