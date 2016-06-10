package zdr.dto;

import zdr.domain.Aggregate;
import zdr.util.LocalDateConverter;
import zdr.util.Util;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
@MappedSuperclass
public class GenericAggregateEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 16)
    private String market_name;
    @Column(length = 32)
    private String market_title;
    @Column(length = 16)
    private String engine;
    @Column(length = 16)
    private String secid;
    private BigDecimal value;
    private Long volume;
    private Long numtrades;

    public GenericAggregateEntity() {
    }

    public GenericAggregateEntity(Aggregate aggregate) {
        this.engine = aggregate.getEngine();
        this.market_name = aggregate.getMarket_name();
        this.market_title = aggregate.getMarket_title();
        this.numtrades = Long.valueOf(aggregate.getNumtrades());
        this.secid = aggregate.getSecid();
        this.value = new BigDecimal(aggregate.getValue().orElse("0"));
        this.volume = Long.valueOf(aggregate.getVolume().orElse("0"));
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

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getNumtrades() {
        return numtrades;
    }

    public void setNumtrades(Long numtrades) {
        this.numtrades = numtrades;
    }
}