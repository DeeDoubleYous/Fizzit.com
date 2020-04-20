package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;
import bton.ci536.fizzit.trade.LocalTradeList;
import bton.ci536.fizzit.trade.TradeItem;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

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
	
	public void moveToTradeList(Customer customer, SavedProduct item, LocalTradeList tradeList) {
		try {
			TradeItem found = em.find(TradeItem.class, item.getProductBarcode());
			found.setItemQuantity(item.getProductQuantity());
			tradeList.addItem(found);
		}catch(Exception e) {
			
		}
	}
	
	public void removeItem(Customer customer, SavedProduct item) {
		if(item.getProductQuantity() > 1) {
			try {
				item.setProductQuantity(item.getProductQuantity() - 1);
				ut.begin();
				em.merge(item);
				ut.commit();
			}catch(Exception e) {
				try {
					ut.rollback();
				}catch(Exception ex) {
					ex.printStackTrace(System.err);
				}
				e.printStackTrace(System.err);
			}
		}else{
			FacesContext.getCurrentInstance().addMessage("form:save-form", new FacesMessage("elsed"));
			removeAllSaved(customer, item);
		}
	}
	
	public void removeAllSaved(Customer customer, SavedProduct item) {
		em.remove(item);
	}
	
}
