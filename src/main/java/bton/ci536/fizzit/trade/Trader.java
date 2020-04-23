package bton.ci536.fizzit.trade;

import bton.ci536.fizzit.database.Customer;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
public class Trader {
    
    @PersistenceContext(unitName = "fizzit")
    EntityManager em;
    
    @Resource
    UserTransaction ut;
    
    public void trade(Customer customer, LocalTradeList localTradeList) {
        
        if(customer.getCustomerId() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("userlogin.xhtml");
            } catch(IOException ex)
            {
                ex.printStackTrace(System.err);
            }
        } else {
            Collection<TradeItem> tradeItems = localTradeList.getItems();
            Trade trade = new Trade();
            trade.setCustomer(customer);
            tradeItems.forEach(ti -> ti.setTrade(trade));
            trade.setTradeItems(new HashSet(tradeItems));
            try{
            ut.begin();
            em.persist(trade);
            ut.commit();
            } catch(Exception ex) {
                try {ut.rollback();} catch(Exception exc) {
                    exc.printStackTrace(System.err);
                }
                ex.printStackTrace(System.err);
            }
            //clear the current trade information 
            localTradeList.clear();
            
            try { //redirect to confirmation page
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("./trade/confirmation.xhtml?id=" + trade.getTradeId());
            } catch(IOException ex) {
                ex.printStackTrace(System.err);
            }
            
        }
        
    }
}
