package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Named
public class SaveRepository {
	
	@PersistenceContext(unitName = "fizzit")
	EntityManager em;
	
	@Resource
	UserTransaction utx;
	
	private Map<String, SavedProduct> saved;
	
	public SaveRepository() {
		this.saved = new HashMap<>();
	}
	
	public void getByCustomer(Customer customer) {
		getByCustomerId(customer.getCustomerId());
	}
	
	public void getByCustomerId(String customerId){
		List<SavedProduct> rs = em.createNamedQuery("custSavedList", SavedProduct.class).setParameter("custId", customerId).getResultList();
		saved.clear();
		for(SavedProduct p:rs) {
			saved.put(p.getProductBarcode(), p);
		}
	}
	
	public Collection<SavedProduct> getSaved(Customer customer) {
		getByCustomerId(customer.getCustomerId());
		return saved.values();
	}
}
