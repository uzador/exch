package zdr.dto;

import zdr.domain.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
@Entity
@Table(name = "TRADE_VOLUME_CURRENT")
public class AggregateEntityCurrentTime extends GenericAggregateEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date cur_time;

    public AggregateEntityCurrentTime(Aggregate aggregate) {
        super(aggregate);
        cur_time = new Date(System.currentTimeMillis());
    }

    public Date getCur_time() {
        return cur_time;
    }

    public void setCur_time(Date cur_time) {
        this.cur_time = cur_time;
    }

    @Override
    public String toString() {
        return "AggregateEntityCurrentTime{" +
                "id=" + getId() +
                ", market_name='" + getMarket_name() + '\'' +
                ", market_title='" + getMarket_title() + '\'' +
                ", engine='" + getEngine() + '\'' +
                ", secid='" + getSecid() + '\'' +
                ", value=" + getValue() +
                ", volume=" + getVolume() +
                ", numtrades=" + getNumtrades() +
                ", cur_time=" + cur_time +
                '}';
    }
}
