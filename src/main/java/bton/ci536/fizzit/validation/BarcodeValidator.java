package bton.ci536.fizzit.validation;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
public class BarcodeValidator implements Validator<String>, Serializable{
    
    
    @Override
    public void validate(
            FacesContext context, 
            UIComponent component, 
            String value            //value to validate 
    ) throws ValidatorException {
        
        if(value.matches("\\d*")){ //string is all numbers
            switch(value.length())
            {
                case 9:     //ISBN 9 (1967 - 1970) 
                case 10:    //ISBN 10 (ISO 2108)
                case 13:    //EAN-13 / GTIN-14 (w/o check)
                    return; // One condition has been met so return without fault.
            }
        }
        //No accepted format found so throw Exception
        throw new ValidatorException(
                new FacesMessage("We accept ISBN 9/10 and EAN-13 barcodes\n"
                        + "(so it should be either 9,10 or 13 digits!)."));
        
    }
    
    
    
}
