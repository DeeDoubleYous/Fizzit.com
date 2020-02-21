package bton.ci536.database;

/**
 * A type for storing the results of the product database look up. Impliments getters and setters for the item stored.
 * @author Alex Wishart
 */

public class Product {
	private String productBarcode;
	private double productPrice;
	private String productName;
	private String productType;
	private String productTypeDescription;
	
	public Product(String bar, double price, String name, String type, String desc) {
		this.productBarcode = bar;
		this.productPrice = price;
		this.productName = name;
		this.productType = type;
		this.productTypeDescription = desc;
	}
	
	public String getBarcode() {
		return productBarcode;
	}
	
	public double getPrice() {
		return productPrice;
	}
	
	public String getName() {
		return productName;
	}
	
	public String getType() {
		return productType;
	}
	
	public String getDescription() {
		return this.getDescription();
	}
	
	public void setBarcode(String bar) {
		this.productBarcode = bar;
	}
	
	public void setPrice(double price) {
		this.productPrice = price;
	}
	
	public void setName(String name) {
		this.productName = name;
	}
	
	public void setType(String type) {
		this.productType = type;
	}
	
	public void setDescription(String desc) {
		this.productTypeDescription = desc;
	}
	
}
