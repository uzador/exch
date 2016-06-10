package zdr.dto;

import zdr.domain.Aggregate;
import zdr.util.LocalDateConverter;
import zdr.util.Util;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
@Entity
@Table(name = "TRADE_VOLUME")
public class AggregateEntity extends GenericAggregateEntity {

    @Convert(converter = LocalDateConverter.class)
    private LocalDate tradedate;

    public AggregateEntity(Aggregate aggregate) {
        super(aggregate);
        this.tradedate = LocalDate.parse(aggregate.getTradedate() == null ? LocalDate.now().toString() : aggregate.getTradedate(), Util.formatter);
    }

    public LocalDate getTradedate() {
        return tradedate;
    }

    public void setTradedate(LocalDate tradedate) {
        this.tradedate = tradedate;
    }

    @Override
    public String toString() {
        return "AggregateEntity{" +
                "id=" + getId() +
                ", market_name='" + getMarket_name() + '\'' +
                ", market_title='" + getMarket_title() + '\'' +
                ", engine='" + getEngine() + '\'' +
                ", secid='" + getSecid() + '\'' +
                ", tradedate=" + getTradedate() +
                ", value=" + getValue() +
                ", volume=" + getVolume() +
                ", numtrades=" + getNumtrades() +
                '}';
    }
}