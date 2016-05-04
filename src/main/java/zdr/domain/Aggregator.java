package zdr.domain;

import java.util.Arrays;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
public class Aggregator {
    private Aggregate[] aggregates;

    public boolean isEmpty() {
        return aggregates.length == 0;
    }

    @Override
    public String toString() {
        return "aggregates=" + Arrays.toString(aggregates);
    }

    private class Aggregate {
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
}
