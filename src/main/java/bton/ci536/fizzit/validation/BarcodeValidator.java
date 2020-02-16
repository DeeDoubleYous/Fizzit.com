package bton.ci536.fizzit.validation;

import bton.ci536.fizzit.trade.LocalTradeList;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
public class BarcodeValidator implements Validator, Serializable{
    
    //Inject the localTradeList bean to validate limit.
    @Inject
    private LocalTradeList localTradeList;
    
    public void setLocalTradeList(LocalTradeList localTradeList) {
        this.localTradeList = localTradeList;
    }
    
    @Override
    public void validate(
            FacesContext context, 
            UIComponent component, 
            Object value            //value to validate 
    ) throws ValidatorException {
        
        /*
            Check the local list of trades against limit
            NB: limit is hardcoded here - In future should resolve by storing 
            this in a Bean that can be configured while the application is running.
        */
        if(localTradeList.getItems().size() == 100) {
            throw new ValidatorException(
                    new FacesMessage("100 items is the maximum trade amount!"));
        } 
        
        String barcode = (String) value;
        
        if(barcode.matches("\\d*")){ //string is all numbers
            switch(barcode.length())
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
