package zdr.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
@Entity
@Table(name = "TRADE_VOLUME")
public class AggregateEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String market_name;
    private String market_title;
    private String engine;
    private String tradedate;
    private String secid;
    private String value;
    private String volume;
    private String numtrades;

    public AggregateEntity() {
    }

    public AggregateEntity(Aggregate aggregate) {
        this.engine = aggregate.getEngine();
        this.market_name = aggregate.getMarket_name();
        this.market_title = aggregate.getMarket_title();
        this.numtrades = aggregate.getNumtrades();
        this.secid = aggregate.getSecid();
        this.tradedate = aggregate.getTradedate();
        this.value = aggregate.getValue();
        this.volume = aggregate.getVolume();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getMarket_title() {
        return market_title;
    }

    public void setMarket_title(String market_title) {
        this.market_title = market_title;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTradedate() {
        return tradedate;
    }

    public void setTradedate(String tradedate) {
        this.tradedate = tradedate;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumtrades() {
        return numtrades;
    }

    public void setNumtrades(String numtrades) {
        this.numtrades = numtrades;
    }
}
