package bton.ci536.fizzit.database;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.*;
/**
 * A type for storing the results of the product database look up. Implements getters and setters for the item stored.
 * @author Alex Wishart
 */
@Named
@Entity()
@Table(name = "Product")
public class Product implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "Barcode")
	private String barcode;
	@Column(name = "productPrice")
	private double price;
	@Column(name = "productName")
	private String name;
	@Column(name = "productType")
	private String type;

	
	public String getBarcode() {
		return barcode;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setBarcode(String bar) {
		this.barcode = bar;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
}
