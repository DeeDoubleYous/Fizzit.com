package bton.ci536.fizzit.trade;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Override
    public int compareTo(TradeStatus o) {
        return statusDateTime.compareTo(o.statusDateTime);
    }
    
}
