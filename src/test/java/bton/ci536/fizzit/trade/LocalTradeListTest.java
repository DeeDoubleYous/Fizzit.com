package bton.ci536.fizzit.trade;

import bton.ci536.fizzit.database.Product;
import bton.ci536.mock.MockEntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
public class LocalTradeListTest {
    
    private LocalTradeList instance;
    private MockEntityManager entityManager;
    
    public LocalTradeListTest() {
    }
    
    @BeforeEach
    public void setUp() {
        this.entityManager = new MockEntityManager();
        this.instance = new LocalTradeList();
        instance.em = entityManager;
    }
    
    @Test
    public void testSubmit() {
        System.out.println("submit");
        
        String barcode = "123456789";
        int type = 1;
        String name = "test";
        double price = 23.56;
        
        Product product = new Product(barcode, type, price, name);
        TradeItem item = product.toTradeItem();
        // Add our product to the entityManager;
        entityManager.findResult = product;
        
        // EntityManager will return our dbProduct then change it to a TradeItem
        instance.setBarcode(product.getBarcode());
        instance.submit();
        
        //Confirm that the code correctly calls the entityManager 
        assertTrue(instance.getItems().contains(item)); 
        //Should only be one item in trade list
        assertEquals(1, instance.getTotalItems());
        //Total value of item should be same as list ~ within 0.001 delta
        assertEquals(price, instance.getTotalValue(), 0.001);
        
        //Submitting with the same barcode should not call the EntityManager but still increase 
        //item count and total value.
        
        instance.setBarcode(barcode); //need to set barcode again.
        instance.submit(); 
        
        //Only one call to find 
        assertEquals(1, entityManager.findCalledCount); 
        //Should only be two items in trade list
        assertEquals(2, instance.getTotalItems());
        //Total value of two item should be same as list ~ within 0.001 delta
        assertEquals(price * 2, instance.getTotalValue(), 0.001);
        
    }

    @Test
    public void testAdd_And_DeleteItem() {
        System.out.println("add_And_deleteItem");
        
        String barcode = "123456789";
        int type = 1;
        String name = "test";
        double price = 23.56;
        
        Product product = new Product(barcode, type, price, name);
        TradeItem item = product.toTradeItem();
        
        instance.addItem(item);
        
        //confirm the item is in the list
        assertTrue(instance.getItems().contains(item));
        //delete the item 
        instance.deleteItem(item);
        
        //should be empty now
        assertTrue(instance.getItems().isEmpty());
        //total items and value should be at zero too after the delete
        assertEquals(0, instance.getTotalItems());
        assertEquals(0, instance.getTotalValue(), 0.001);
    }

    @Test
    public void testClear() {
        System.out.println("clear");
        
        String barcode = "123456789";
        int type = 1;
        String name = "test";
        double price = 23.56;
        
        Product product1 = new Product(barcode, type, price, name);
        Product product2 = new Product("123475693", type, price, name);
        TradeItem item = product1.toTradeItem();
        TradeItem item2 = product2.toTradeItem();
        
        // add two items
        instance.addItem(item);
        instance.addItem(item2);
        
        //confirm the items are in the list
        assertEquals(2, instance.getTotalItems());
        assertTrue(instance.getTotalValue() > 0);
        //clear should remove all items and set total values to zero.
        instance.clear();
        
        assertEquals(0, instance.getTotalItems());
        assertEquals(0, instance.getTotalValue(), 0.001);
    }
    
}

