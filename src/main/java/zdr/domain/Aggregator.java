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

    public Aggregate[] getAggregates() {
        return aggregates;
    }

    @Override
    public String toString() {
        return "aggregates=" + Arrays.toString(aggregates);
    }
}
