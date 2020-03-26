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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@NamedQueries({
        @NamedQuery(
                name = "byCustomer",
                query = "select t from Trade t where t.customer.customerId like :custId"
        ),
        @NamedQuery(
                name = "all",
                query = "select t from Trade t"
        )
        
})
@Named
@Entity
@Table(name = "Trade")
public class Trade implements Serializable, Comparable<Trade>{
    
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
    private Set<TradeStatus> tradeStatuses;
    
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

    public Set<TradeStatus> getTradeStatuses() {
        return tradeStatuses;
    }

    public void setTradeStatuses(Set<TradeStatus> tradeStatuses) {
        this.tradeStatuses = tradeStatuses;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public long getTradeId() {return tradeId;}
    
    public void setTradeItems(Set<TradeItem> tradeItems) {
        this.tradeItems = tradeItems;
    }
    
    public Set<TradeItem> getTradeItems() {return tradeItems;}
    
    
    public TradeStatus getLatestStatus() {
        return tradeStatuses.stream().max(TradeStatus::compareTo).get();
    }
    
    public double getTotalValue() {
        return tradeItems
                .stream()
                .mapToDouble(t -> t.getItemAmount() * t.getItemQuantity())
                .sum();       
    }

    @Override
    public int compareTo(Trade o) {
        return this.getLatestStatus().compareTo(o.getLatestStatus());
    }
    
}
