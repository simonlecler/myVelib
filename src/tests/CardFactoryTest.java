package tests;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.CardNone;
import myVelibCore.userAndCardPackage.CardVLibre;
import myVelibCore.userAndCardPackage.CardVMax;

class CardFactoryTest {

	@Test
	void testGetCardString() throws BadInstantiationException, FactoryNullException{
		AbstractFactory cardFactory = FactoryProducer.getFactory("Card");
		Card cardNone = cardFactory.getCard("");
		Card cardVlibre = cardFactory.getCard("Vlibre");
		Card cardVmax = cardFactory.getCard("Vmax");
		assertTrue(cardNone instanceof CardNone);
		assertTrue(cardVlibre instanceof CardVLibre);
		assertTrue(cardVmax instanceof CardVMax);
		assertTrue(cardVlibre.getId() != cardVmax.getId());
	}

	@Test
	void testGetCardStringInt() throws BadInstantiationException, FactoryNullException{
		AbstractFactory cardFactory = FactoryProducer.getFactory("Card");
		Card cardNone = cardFactory.getCard("", 10);
		Card cardVlibre = cardFactory.getCard("Vlibre", 54);
		Card cardVmax = cardFactory.getCard("Vmax", 12);
		assertTrue(cardNone instanceof CardNone);
		assertTrue(cardVlibre instanceof CardVLibre);
		assertTrue(cardVmax instanceof CardVMax);
		assertTrue(cardNone.getBalance()==0);
		assertTrue(cardVlibre.getBalance()==54);
		assertTrue(cardVmax.getBalance()==12);
	}

}
