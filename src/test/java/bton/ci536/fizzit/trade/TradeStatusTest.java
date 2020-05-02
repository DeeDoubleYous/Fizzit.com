package bton.ci536.fizzit.trade;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
public class TradeStatusTest {
    
    public TradeStatusTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testNext() {
        System.out.println("next");
        TradeStatus instance = new TradeStatus();
        
        assertEquals(0, instance.getStatus());
        TradeStatus ts = instance;
        
        /*  
            Each call to next produces a new trade status with an incremented status,
            this will continue until the status hits COMPLETED and when then return the same status 
            for each subsequent call.
        */
        for (int i = 1; i <= TradeStatus.COMPLETED; i++) {
            ts = ts.next();
            assertEquals(i, ts.getStatus());
        }
        
        //Call next again should produce the same TradeStatus with a status of COMPLETED
        ts = ts.next();
        assertEquals(TradeStatus.COMPLETED, ts.getStatus());
        assertEquals(ts, ts);
        
    }
    
}
