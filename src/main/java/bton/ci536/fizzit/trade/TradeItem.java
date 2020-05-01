package bton.ci536.fizzit.trade;

import java.io.Serializable;
import java.util.Objects;
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
 * This class is both a Java Bean and an Entity so will be persisted using 
 * JPA
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

    public TradeItem() {
    }

    public TradeItem(String barcode, int itemType, String itemName, 
            double itemAmount, int itemQuantity, Trade trade) {
        this.barcode = barcode;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemAmount = itemAmount;
        this.itemQuantity = itemQuantity;
        this.trade = trade;
    }
    
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.barcode);
        hash = 53 * hash + this.itemType;
        hash = 53 * hash + Objects.hashCode(this.itemName);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.itemAmount) ^ (Double.doubleToLongBits(this.itemAmount) >>> 32));
        hash = 53 * hash + this.itemQuantity;
        hash = 53 * hash + Objects.hashCode(this.trade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TradeItem other = (TradeItem) obj;
        if (this.itemType != other.itemType) {
            return false;
        }
        if (Double.doubleToLongBits(this.itemAmount) != Double.doubleToLongBits(other.itemAmount)) {
            return false;
        }
        if (this.itemQuantity != other.itemQuantity) {
            return false;
        }
        if (!Objects.equals(this.barcode, other.barcode)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.trade, other.trade)) {
            return false;
        }
        return true;
    }

}
