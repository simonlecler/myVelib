package tests;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.byciclePackage.BycicleElectrical;
import myVelibCore.byciclePackage.BycicleMechanical;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;

class BycicleFactoryTest {

	@Test
	void testGetBycicle() throws BadInstantiationException, FactoryNullException{
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		Bycicle elecBike = bycicleFactory.getBycicle("Electrical");
		Bycicle mechaBike = bycicleFactory.getBycicle("Mechanical");
		assertTrue(elecBike instanceof BycicleElectrical);
		assertTrue(mechaBike instanceof BycicleMechanical);
		assertTrue(elecBike.getId() != mechaBike.getId());
	}

}
