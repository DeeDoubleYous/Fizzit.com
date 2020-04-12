package bton.ci536.fizzit.trade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bton.ci536.fizzit.database.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 * A Local list of to-be-traded items ({@link Products}) by the customer. 
 * @see TradeItem
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
@SessionScoped 
public class LocalTradeList implements Serializable{
    
    @PersistenceContext(unitName = "fizzit")
    EntityManager em;
	
    private String barcode;
    private Map<String,TradeItem> items;
    private double totalValue = 0;
    private int totalItems = 0;
    
    public LocalTradeList() {
        this.items = new HashMap<>();
    }

    /**
     * Method is called after creation of object 
     * this makes sure that the items list is valid.
     */
    @PostConstruct
    public void init() {
        this.items = new HashMap<>();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public Collection<TradeItem> getItems() {
        return items.values();
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
    
    public double getTotalValue() {
        return totalValue;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    
    
    
    /**
     * Submit will check the current barcode field in this instance 
     * is within the TWOO database. 
     * if the product is in the TWOO database then the {@link Product} variable 
     * is added to the items list. When the product is not in the TWOO database 
     * this method will raise a {@link FaceMessage} to the "form:trade-btn" 
     * component.
     * 
     * @see Product
     * @see FacesContext
     * @see FacesMessage
     */
    public void submit() {
        
        if(items.containsKey(barcode)){//save pinging db and increment quantity
            items.get(barcode).incrementQuantity();
            totalItems++;
            totalValue += items.get(barcode).getItemAmount();
        } else { //try and fetch item from twoo
            
            Product p = em.find(Product.class, barcode);
            
            if(p == null) { // No match found (unwanted item) - add error message
                FacesContext.getCurrentInstance()
                        .addMessage("form:trade-btn",
                        new FacesMessage("Sorry, we have no offer for this product."));
                
            } else { // Product found so convert to trade item and add to list.
                items.put(barcode, p.toTradeItem());
                totalValue += p.getPrice();
                totalItems++;
            }
            
        }
        
        //regardless of outcome lets clear the barcode
        barcode = "";
        
    }
    
    
    /**
     * Will take a product item and removes it from the items arrayList. The only time this can be called
     * is from an item that is already in the list. Will also subtract the cost of the item from the totalValue
     * @param tradeItem
     */
    
    public void deleteItem(TradeItem tradeItem) {
        totalValue -= tradeItem.getItemAmount() * tradeItem.getItemQuantity();
        totalItems -= tradeItem.getItemQuantity();
        items.remove(tradeItem.getBarcode());
    }
    
    
    public void clear(){
        barcode = "";
        totalItems = 0;
        totalValue = 0;
        items.clear();
    }
}
