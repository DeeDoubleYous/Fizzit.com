package bton.ci536.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Looks up the products from the database and stores them in an arraylist. Allows for looking up items by barcode
 * @author Alex Wishart
 */

public class LookUp {
	Connection con;
	ResultSet rs;
	Statement stat;
	
	private String userName = "nm555_AW";
	private String password = "vxy[-m,Vd{kK";
	private String url = "jdbc:mysql://178.128.37.54/nm555_FizzitTwo";
	
	ArrayList<Product> products;
	
	public LookUp(){
		
	}
	
	public void run() {
		try {
			products = new ArrayList<Product>();
			con = DriverManager.getConnection(url, userName, password);
			stat = con.createStatement();
			String sql = "SELECT Barcode, productPrice, productName, productType, ProductTypeDescription FROM Product";
			rs = stat.executeQuery(sql);
			while(rs.next()){
				products.add(new Product(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * <p>
	 * Takes a barcode and returns a <b>Product</b> that corresponds from the data stored from the database. 
	 * Returns <b>null</b> if the barcode provided doesn't exist.
	 * </p>
	 * @param barcode
	 * @return Product/null
	 */
	
	public Product getProduct(String barcode) {
		for(Product item:products) {
			if(item.getBarcode().equals(barcode)) {
				return item;
			}
		}
		return null;
	}

}
