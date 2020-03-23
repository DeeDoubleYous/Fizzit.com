package bton.ci536.fizzit.trade;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Represents the items that are being traded in by the customer. 
 * 
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
@Entity
@Table(name = "TradeItem")
public class TradeItem implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "itemBarcode")
    private String barcode;
    
    @Column(name = "itemType")
    private int itemType;
    
    @Column(name = "itemName")
    private String itemName;
    
    @Column(name = "itemPrice")
    private double itemAmount;
    
    @Column(name = "itemQuantity")
    private int itemQuantity;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tradeTradeId")
    private Trade trade;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    public String getFormattedPrice() {
        
        return String.format("£%.2f", itemAmount * itemQuantity);
    }
    
    public void incrementQuantity(){
        itemQuantity++;
    }
    
    public void decrementQuantity(){
        itemQuantity--;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    
    
}
