package zdr.dto;

import zdr.domain.Aggregate;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
@Entity
@Table(name = "TRADE_VOLUME_CURRENT")
public class AggregateEntityCurrentTime extends GenericAggregateEntity {

    @Temporal(TemporalType.TIME)
    private Date cur_time;

    public AggregateEntityCurrentTime(Aggregate aggregate) {
        super(aggregate);
        cur_time = new Date(System.currentTimeMillis());
    }

    public Date getTest() {
        return cur_time;
    }

    public void setTest(Date current_time) {
        this.cur_time = current_time;
    }

    @Override
    public String toString() {
        return "AggregateEntityCurrentTime{" +
                "id=" + getId() +
                ", market_name='" + getMarket_name() + '\'' +
                ", market_title='" + getMarket_title() + '\'' +
                ", engine='" + getEngine() + '\'' +
                ", secid='" + getSecid() + '\'' +
                ", tradedate=" + getTradedate() +
                ", value=" + getValue() +
                ", volume=" + getVolume() +
                ", numtrades=" + getNumtrades() +
                ", cur_time=" + cur_time +
                '}';
    }
}
