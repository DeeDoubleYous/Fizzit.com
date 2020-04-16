package bton.ci536.fizzit.trade;

import bton.ci536.fizzit.database.Customer;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
public class TradeRepository {
    
    @Resource
    UserTransaction ut;
    
    @PersistenceContext(unitName = "fizzit")
    EntityManager em;
    
    
    public List<Trade> getByCustomer(Customer customer) {
        return getByCustomerId(customer.getCustomerId());
    }
    
    public List<Trade> getByCustomerId(String customerId) {
        List<Trade> trades = em
                .createNamedQuery("byCustomer", Trade.class)
                .setParameter("custId", customerId)
                .getResultList();
        
        em.getEntityManagerFactory().getCache().evictAll();
        return trades;
    }
    
    public Trade getByCustomerAndTradeId(Customer c, String tradeId) {
        Trade t = em
                .createNamedQuery("byCustomerAndTradeId", Trade.class)
                .setParameter("custId", c.getCustomerId())
                .setParameter("tradeId", Long.valueOf(tradeId))
                .getSingleResult();
        
        em.getEntityManagerFactory().getCache().evictAll();
        return t;
    }
    
    public List<Trade> getAll() {
        List<Trade> trades = em
                .createNamedQuery("all", Trade.class)
                .getResultList();
        em.getEntityManagerFactory().getCache().evictAll();
        return trades;
    }
    
   public void update(Trade trade) {
        
        try {
            ut.begin();
            em.merge(trade);
            ut.commit();
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
}
