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
public class TradeLimitValidator implements Validator<String>, Serializable{

    @Inject
    private LocalTradeList localTradeList;

    public void setLocalTradeList(LocalTradeList localTradeList) {
        this.localTradeList = localTradeList;
    }
    
    @Override
    public void validate(
            FacesContext context, 
            UIComponent component, 
            String value) 
            throws ValidatorException {
        
        /*
            Check the local list of trades against limit
            NB: limit is hardcoded here - In future should resolve by storing 
            this in a Bean that can be configured while the application is running.
        */
        if(localTradeList.getItems().size() >= 100){
            throw new ValidatorException(
                    new FacesMessage("100 items is the maximum trade amount!"));
        }
        
    }
    
}
