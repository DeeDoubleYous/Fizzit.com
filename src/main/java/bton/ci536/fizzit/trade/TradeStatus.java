package bton.ci536.fizzit.trade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Max Cripps <43726912+mc1098@users.noreply.github.com>
 */
@Entity
@Table(name = "TradeStatus")
public class TradeStatus implements Serializable, Comparable<TradeStatus>{
    
    public final static int PENDING_DELIVERY = 0;
    public final static int IN_TRANSIT_TO_WAREHOUSE = 1;
    public final static int CHECKING_AT_WAREHOUSE = 2;
    public final static int COMPLETED = 3;
    public final static int CANCELLED = 4;
    
    @Id
    @Column(name = "status")
    private int status;
    
    @Column(name = "statusDateTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime statusDateTime;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tradeTradeId")
    private Trade trade;
    
    public TradeStatus() {
        this.status = 0;
        this.statusDateTime = LocalDateTime.now();
    };
    
    public TradeStatus(Trade trade) {
        this();
        this.trade = trade;
    }
    
    public TradeStatus(Trade trade, int status) {
        this(trade);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(LocalDateTime statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }
    
    public String getStatusString() {
        switch(status){
            case PENDING_DELIVERY: 
                return "Pending Delivery";
            case IN_TRANSIT_TO_WAREHOUSE: 
                return "In Transit To Warehouse";
            case CHECKING_AT_WAREHOUSE: 
                return "Checking at Warehouse";
            case COMPLETED: 
                return "Completed";
            case CANCELLED: 
                return "Cancelled";
        }
        return "unknown";
    }
    
    public TradeStatus next() {
        if(status >= COMPLETED)
            return this;
        TradeStatus next = new TradeStatus(trade);
        next.status = this.status+1;
        return next;
    }
    
    @Override
    public int compareTo(TradeStatus o) {
        int dt = this.statusDateTime.compareTo(o.statusDateTime);
        if(dt == 0) { //If somehow these are the same then sort by the status
            return Integer.compare(this.status, o.status);
        } else {
            return dt;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.status;
        hash = 29 * hash + Objects.hashCode(this.statusDateTime);
        hash = 29 * hash + Objects.hashCode(this.trade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TradeStatus other = (TradeStatus) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.statusDateTime, other.statusDateTime)) {
            return false;
        }
        if (!Objects.equals(this.trade, other.trade)) {
            return false;
        }
        return true;
    }
    
    
    
}
