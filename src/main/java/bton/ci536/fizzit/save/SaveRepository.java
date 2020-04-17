package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class SaveRepository {
	
	@PersistenceContext(unitName = "fizzit")
	EntityManager em;
	
	public List<SavedProduct> getByCustomer(Customer cust){
		return getByCustomerId(cust.getCustomerId());
	}
	
	public List<SavedProduct> getByCustomerId(String customerId){
		List<SavedProduct> saved = em.createNamedQuery("byCustomer", SavedProduct.class).setParameter("custId", customerId).getResultList();
		em.getEntityManagerFactory().getCache().evictAll();
		return saved;
	}

}
