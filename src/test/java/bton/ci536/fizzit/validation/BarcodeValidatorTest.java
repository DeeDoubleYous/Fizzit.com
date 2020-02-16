package bton.ci536.fizzit.validation;

import java.util.stream.Stream;
import javax.faces.validator.ValidatorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */

public class BarcodeValidatorTest {
    
    private final BarcodeValidator validator;
    
    public BarcodeValidatorTest() {
        this.validator = new BarcodeValidator();
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

    @ParameterizedTest
    @MethodSource("validateProvider")
    public void testValidate(String barcode, boolean validity) {
        System.out.println("validate");
        
        try {
            validator.validate(null, null, barcode);
            assertEquals(validity, true);
        } catch (ValidatorException ex)
        {
            assertEquals(validity, false, ex.getMessage());
        }
    }
    
    static Stream<Arguments> validateProvider() {
        return Stream.of(
                arguments("", false),
                arguments("a", false), 
                arguments("123456789", true),       //ISBN 9
                arguments("abcdefghi", false),      
                arguments("1a3b5c7d9", false),
                arguments("1234567891", true),      //ISBN 10
                arguments("abcdefghij", false),
                arguments("a2b4c6d8e1", false),
                arguments("1234567891123", true),    //EAN-13
                arguments("abcdefghijklm", false),
                arguments("1a3b5c7d9e2f3", false),
                arguments("12345678911234", false)
        );
    }
    
}
