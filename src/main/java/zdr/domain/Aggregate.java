package zdr.domain;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
class Aggregate {
    private String market_name;
    private String market_title;
    private String engine;
    private String tradedate;
    private String secid;
    private String value;
    private String volume;
    private String numtrades;

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
