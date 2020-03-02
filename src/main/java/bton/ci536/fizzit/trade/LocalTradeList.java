package bton.ci536.fizzit.trade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bton.ci536.fizzit.database.Product;
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
    
    @PersistenceContext(unitName = "twoo")
    EntityManager em;
	
    private String barcode;
    private List<Product> items;
    
    public LocalTradeList() {
        this.items = new ArrayList<>();
    }

    /**
     * Method is called after creation of object 
     * this makes sure that the items list is valid.
     */
    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public List<Product> getItems() {
        return items;
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
        
        // Does barcode match a product from TWOO? 
        Product p = em.find(Product.class, barcode);
        
        if(p == null) { // No match found - add error message
            FacesContext.getCurrentInstance()
                    .addMessage("form:trade-btn",
                        new FacesMessage("Sorry, we have no offer for this product."));
        } else { // Product found so load into the items list.
            items.add(p);
        }
        
    }
}
