package bton.ci536.fizzit.save;

import bton.ci536.fizzit.database.Customer;

import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * Responsible for populating the data table on the saved page. Looks up the items based on the currently logged in user.
 * @see SavedProduct
 * @author Alex Wishart | d.wishart1@uni.brighton.ac.uk
 */


@Named
public class SaveRepository {
	
	@PersistenceContext(unitName = "fizzit")
	EntityManager em;
	
	@Resource
	UserTransaction utx;
	
	private List<SavedProduct> saved;
	
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
