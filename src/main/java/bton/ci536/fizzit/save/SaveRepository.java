package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Named
public class SaveRepository {
	
	@PersistenceContext(unitName = "fizzit")
	EntityManager em;
	
	@Resource
	UserTransaction utx;
	
	private List<SavedProduct> saved;
	
	public SaveRepository() {
	}
	
	public void getByCustomer(Customer customer) {
		getByCustomerId(customer.getCustomerId());
	}
	
	public void getByCustomerId(String customerId){
		this.saved = em.createNamedQuery("custSavedList", SavedProduct.class).setParameter("custId", customerId).getResultList();
	}
	
	public Collection<SavedProduct> getSaved(Customer customer) {
		getByCustomerId(customer.getCustomerId());
		return saved;
	}
}
