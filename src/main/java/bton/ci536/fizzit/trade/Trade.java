package bton.ci536.fizzit.trade;

import bton.ci536.fizzit.database.Customer;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Named
@Entity
@Table(name = "Trade")
public class Trade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tradeId")
    private long tradeId;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "Trade",
            orphanRemoval = true
    )
    @JoinColumn(referencedColumnName = "tradeTradeId")
    private TreeSet<TradeStatus> tradeStatuses;
    
//    @Column(name = "tradeStatus")
//    private int status;
    
    @OneToMany(
            cascade=CascadeType.ALL,
            mappedBy = "Trade",
            orphanRemoval = true
    )
    @JoinColumn(referencedColumnName = "tradeTradeId")
    private Set<TradeItem> tradeItems;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerCustomerId")
    private Customer customer;
    
    
    public Trade() {
        this.tradeStatuses = new TreeSet<>();
        tradeStatuses.add(new TradeStatus(this));
    }

    public TreeSet<TradeStatus> getTradeStatuses() {
        return tradeStatuses;
    }

    public void setTradeStatuses(TreeSet<TradeStatus> tradeStatuses) {
        this.tradeStatuses = tradeStatuses;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public long getTradeId() {return tradeId;}
    
    public void setTradeItems(Set<TradeItem> tradeItems) {
        this.tradeItems = tradeItems;
    }
    
    public Set<TradeItem> getTradeItems() {return tradeItems;}
    
    
    
    
}
