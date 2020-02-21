package bton.ci536.fizzit.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import bton.ci536.database.LookUp;
import bton.ci536.database.Product;

class LookUpTest {

	@Test
	void testNull() {
		LookUp l = new LookUp();
		l.run();
		Product p =l.getProduct("123456790");
		assertEquals(p,null);
	}
	
	@Test
	void testFound() {
		LookUp l = new LookUp();
		l.run();
		Product p = l.getProduct("123456789");
		assertNotEquals(p, null);
	}

}
