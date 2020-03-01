package bton.ci536.fizzit.trade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bton.ci536.fizzit.database.Product;
/**
 * A Local list of to-be-traded items ({@link Products}) by the customer. 
 * @see TradeItem
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
@SessionScoped 
public class LocalTradeList implements Serializable{
    
	@PersistenceContext(name = "fizzitLookUp")
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
     * Submit will check the current barcode variable in this instance 
     * and will generate a new {@link Product} in the list if this 
     * barcode is accepted.
     * 
     * NB: currently it just simply creates a new item from the current barcode.
     */
    public void submit() {
        /*
            create a new TradeItem from barcode
            NB: in future this should seek item from 
            database. 
        */
    	

    }
}
