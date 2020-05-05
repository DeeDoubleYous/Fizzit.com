package bton.ci536.fizzit.trade;

import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
public class TradeTest {
    
    private Trade instance;
   
    
    public TradeTest() {
    }
    
    @BeforeEach
    public void setUp() {
        this.instance = new Trade();
    }
    

    @Test
    public void testGetLatestStatus() {
        System.out.println("getLatestStatus");
        
        TradeStatus expResult = new TradeStatus(instance, 1);
        // instance already has a default Trade status but add a later one
        instance.getTradeStatuses().add(expResult);
        
        TradeStatus result = instance.getLatestStatus();
        assertEquals(2, instance.getTradeStatuses().size());
        assertEquals(expResult, result);
    }

    @Test
    public void testNextStatus() {
        System.out.println("nextStatus");
        
        // This should create a new TradeStatus and add it to the internal set
        instance.nextStatus();
        
        //TradeStatus starts at 0 and increments so the latest after nextStatus should be 1.
        assertEquals(1, instance.getLatestStatus().getStatus());
        
        //NB TradeStatus is responsible for providing an end point to this increment 
        //so won't be tested here.
        
    }

    @Test
    public void testCancelTrade() {
        System.out.println("cancelTrade");
        
        instance.cancelTrade();
        
        //latest status should be cancelled
        assertEquals(TradeStatus.CANCELLED, instance.getLatestStatus().getStatus());
        //should be two TradeStatus - default and the cancellation
        assertEquals(2, instance.getTradeStatuses().size());
        
        instance.cancelTrade();
        //should still be only two TradeStatus (shouldn't persist multiple cancellations of one trade)
        assertEquals(2, instance.getTradeStatuses().size());
        
    }

    @Test
    public void testGetTotalValue() {
        System.out.println("getTotalValue");
        
        //Add a quantity of more than one to test this is accounted for correctly.
        TradeItem item = new TradeItem(null, 0, null, 25.00, 2, instance);
        TradeItem item2 = new TradeItem(null, 0, null, 30.23, 1, instance);
        
        //add both trade items to the trade.
        instance.setTradeItems(new HashSet<TradeItem>(){{add(item); add(item2);}});
        
        
        double expResult = 80.23; // 25 * 2 = 50 + 30.23 = 80.23
        double result = instance.getTotalValue();
        assertEquals(expResult, result, 0.001);
    }
    
}
