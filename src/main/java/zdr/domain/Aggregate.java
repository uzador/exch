package zdr.domain;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
public class Aggregate {
    private String market_name;
    private String market_title;
    private String engine;
    private String tradedate;
    private String secid;
    private String value;
    private String volume;
    private String numtrades;

    public String getMarket_name() {
        return market_name;
    }

    public String getMarket_title() {
        return market_title;
    }

    public String getEngine() {
        return engine;
    }

    public String getTradedate() {
        return tradedate;
    }

    public String getSecid() {
        return secid;
    }

    public String getValue() {
        return value;
    }

    public String getVolume() {
        return volume;
    }

    public String getNumtrades() {
        return numtrades;
    }

    @Override
    public String toString() {
        return "Aggregate{" +
                "market_name='" + market_name + '\'' +
                ", market_title='" + market_title + '\'' +
                ", engine='" + engine + '\'' +
                ", tradedate='" + tradedate + '\'' +
                ", secid='" + secid + '\'' +
                ", value='" + value + '\'' +
                ", volume='" + volume + '\'' +
                ", numtrades='" + numtrades + '\'' +
                '}';
    }
}
