package bton.ci536.fizzit.validation;

import bton.ci536.fizzit.trade.LocalTradeList;
import javax.faces.validator.ValidatorException;
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
public class TradeLimitValidatorTest {
    
    private final LocalTradeList localTradeList;
    private final TradeLimitValidator validator;
    
    public TradeLimitValidatorTest() {
        this.localTradeList = new LocalTradeList();
        this.validator = new TradeLimitValidator();
        validator.setLocalTradeList(localTradeList);
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
    public void testValidate() {
        System.out.println("validate");
        
        //Validator actually doesn't validate the form data so all params are ignored.
        validator.validate(null, null, null); //should run without exception.
        
        /*
            Fill the localTradeList of dummy items to get to 100 limit. 
            then test.
        */
        localTradeList.setTotalItems(100);
        
        try {
            validator.validate(null, null, null);
            fail("Expected validator to fail as list is over limit.");
        } catch (ValidatorException e) {
            //Exception is expected here so test will pass if thrown.
        }
    }
    
}
