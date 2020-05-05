package bton.ci536.fizzit.database;

import bton.ci536.fizzit.trade.TradeItem;
import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.*;

/**
 * A type for storing the results of the product database look up. Implements
 * getters and setters for the item stored.
 *
 * @author Alex Wishart | d.wishart1@uni.brighton.ac.uk
 */
@Named
@Entity()
@Table(name = "TwooProduct")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "productBarcode")
    private String barcode;
    @Column(name = "productType")
    private int type;
    @Column(name = "productPrice")
    private double price;
    @Column(name = "productName")
    private String name;

    public Product() {
    }
    
    public Product(String barcode, int type, double price, String name) {
        this.barcode = barcode;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setBarcode(String bar) {
        this.barcode = bar;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TradeItem toTradeItem() {
        TradeItem item = new TradeItem();
        item.setBarcode(barcode);
        item.setItemType(type);
        item.setItemAmount(price);
        item.setItemName(name);
        item.setItemQuantity(1);
        return item;
    }
}
