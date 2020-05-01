package bton.ci536.fizzit.database;

import bton.ci536.fizzit.trade.TradeItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple testing for a mostly Bean class 
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
public class ProductTest {
    
    public ProductTest() {
    }

    /**
     * Testing the conversion to a TradeItem - this should simply create an 
     * item with 1 quantity and no trade associated to it.
     */
    @Test
    public void testToTradeItem() {
        System.out.println("toTradeItem");
        String barcode = "123456789";
        int type = 1;
        String name = "Test Product";
        double amount = 99.99;
        Product instance = new Product(barcode, type, amount, name);
        TradeItem expResult = new TradeItem(barcode, type, name, amount, 1, null);
        TradeItem result = instance.toTradeItem();
        
        assertEquals(expResult, result);
    }
    
}
