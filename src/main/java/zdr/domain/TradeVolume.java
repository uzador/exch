package zdr.domain;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
public class TradeVolume {
    private Aggregator aggregator;
    private Info info;

    private TradeVolume(Builder builder) {
        this.aggregator = builder.aggregator;
        this.info = builder.info;
    }

    public Aggregator getAggregator() {
        return aggregator;
    }

    public Info getInfo() {
        return info;
    }

    public static class Builder {
        private Aggregator aggregator;
        private Info info;

        public Builder setAggregator(Aggregator aggregator) {
            this.aggregator = aggregator;
            return this;
        }

        public Builder setInfo(Info info) {
            this.info = info;
            return this;
        }

        public TradeVolume build() {
            return new TradeVolume(this);
        }
    }

    @Override
    public String toString() {
        return "TradeVolume{"+ info + ", " + aggregator;
    }
}
