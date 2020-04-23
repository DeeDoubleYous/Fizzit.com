package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;
import bton.ci536.fizzit.trade.LocalTradeList;
import bton.ci536.fizzit.trade.TradeItem;
import java.io.IOException;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * Performs the actions that are involved with making changes to the Saved Product table in the database and making changes to the Trade List.
 * @see 
 * @author Alex Wishart | d.wishart1@uni.brighton.ac.uk
 */


@Named
public class Saver {
	
	@PersistenceContext(unitName = "fizzit")
	EntityManager em;
	
	@Resource
	UserTransaction ut;
	
	
	public void saveItem(Customer customer, TradeItem item, LocalTradeList tradeList) {
		if(customer.getCustomerId() == null) {
			try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("userlogin.xhtml");
            } catch(IOException ex)
            {
                ex.printStackTrace(System.err);
            }
		}else {
			try {
				SavedProduct saved = em.createNamedQuery("byCustAndBarcode", SavedProduct.class).setParameter("custId", customer.getCustomerId()).setParameter("prodBar", item.getBarcode()).getSingleResult();
				saved.setProductQuantity(saved.getProductQuantity() + item.getItemQuantity());
				try {
					ut.begin();
					em.merge(saved);
					ut.commit();
					tradeList.deleteItem(item);
				}catch(Exception ex){
					try {
						ut.rollback();
					}catch(Exception exc) {
						exc.printStackTrace(System.err);
					}
					ex.printStackTrace(System.err);
				}
			}catch(NoResultException e) {
				SavedProduct saved = new SavedProduct(item.getBarcode(), customer, item.getItemName(), item.getItemType(), item.getItemAmount(), item.getItemQuantity());
				try {
					ut.begin();
					em.persist(saved);
					ut.commit();
					tradeList.deleteItem(item);
				}catch(Exception ex) {
					try {
						ut.rollback();
					}catch(Exception exc) {
						exc.printStackTrace(System.err);
					}
				}
			}
		}
	}
		
	public void removeItem(Customer customer, SavedProduct item) {
		try {
			ut.begin();
			SavedProduct found = em.createNamedQuery("byCustAndBarcode", SavedProduct.class).setParameter("custId", customer.getCustomerId()).setParameter("prodBar", item.getProductBarcode()).getSingleResult();
			em.remove(found);
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}
	
	public void moveToTradeList(Customer customer, SavedProduct item, LocalTradeList tradeList) {
		tradeList.addItem(item.toTradeItem());
		removeItem(customer, item);
	}
	
}
