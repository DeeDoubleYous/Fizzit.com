package bton.ci536.fizzit.trade;

import java.io.Serializable;


/**
 * Represents the items that are being traded in by the customer. 
 * 
 * Is not a bean currently but will likely become an Entity in the future.
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
public class TradeItem implements Serializable{

    private static final long serialVersionUID = 1L;
    private String barcode;
    private String name;
    
    public TradeItem() {};
    public TradeItem(String barcode,String name) {
        this.barcode = barcode;
        this.name = name;
        
    }
    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
