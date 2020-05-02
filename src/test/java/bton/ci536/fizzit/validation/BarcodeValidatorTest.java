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
    public void testValidate(String testName, String barcode, boolean validity) {
        System.out.println(testName);
        
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
            //        name of test,                 String to test,   expResult 
            arguments("not_valid_empty",            "",               false),
            arguments("not_valid_too_short",        "a",              false), 
            arguments("validate_isbn9",             "123456789",      true),    //ISBN 9
            arguments("not_valid_9_alphanumeric",   "abcdefghi",      false),      
            arguments("not_valid_10_alphanumeric",  "1a3b5c7d9",      false),
            arguments("validate_isbn10",            "1234567891",     true),    //ISBN 10
            arguments("not_valid_11_char",          "abcdefghij",     false),
            arguments("not_valid_12_char",          "a2b4c6d8e1",     false),
            arguments("validate_ean13",             "1234567891123",  true),    //EAN-13
            arguments("not_valid_13_alpha",         "abcdefghijklm",  false),
            arguments("not_valid_13_alphanumeric",  "1a3b5c7d9e2f3",  false),
            arguments("not_valid_too_long",         "12345678901234", false)
        );
    }
    
}
