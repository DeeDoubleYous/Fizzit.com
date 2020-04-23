package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;
import bton.ci536.fizzit.trade.TradeItem;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	 @NamedQuery(
			 name = "custSavedList", 
			 query = "select s from SavedProduct s where s.customer.customerId like :custId"),
	 @NamedQuery(
			 name = "byCustAndBarcode",
			 query = "select s from SavedProduct s where s.customer.customerId like :custId and s.productBarcode like :prodBar")
})

@Named
@Entity
@Table(name = "SavedProduct")

public class SavedProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SavedProduct() {
		
	}
	
	public SavedProduct(String barcode, Customer customer, String name, int type, double price, int quant) {
		this.productBarcode = barcode;
		this.customer = customer;
		this.productType = type;
		this.productName = name;
		this.productPrice = price;
		this.productQuantity = quant;
	}

	@Id
	@Column(name="productBarcode")
	private String productBarcode;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerCustomerId")
	private Customer customer;
	
	@Column(name="productType")
	private int productType;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="productPrice")
	private double productPrice;
	
	@Column(name="productQuantity")
	private int productQuantity;
	
	public String getProductBarcode() {
		return productBarcode;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getProductType() {
		return productType;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}
	
	public void setProductBarcode(String barcode) {
		this.productBarcode = barcode;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setProductType(int type) {
		this.productType = type;
	}
	
	public void getProductName(String name) {
		this.productName = name;
	}
	
	public void setProductPrice(double price) {
		this.productPrice = price;
	}
	
	public void setProductQuantity(int quantity) {
		this.productQuantity = quantity;
	}
	
	public String getFormattedPrice() {
	        return String.format("£%.2f", productPrice * productQuantity);
	}
	
	public TradeItem toTradeItem() {
        TradeItem item = new TradeItem();
        item.setBarcode(productBarcode);
        item.setItemType(productType);
        item.setItemAmount(productPrice);
        item.setItemName(productName);
        item.setItemQuantity(productQuantity);
        return item;
    }
}
