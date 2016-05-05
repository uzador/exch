package zdr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import zdr.dao.AggregateRepository;
import zdr.domain.AggregateEntity;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
public class LoaderService implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AggregateRepository aggregateRepository;

    @Override
    public void run(String... strings) throws Exception {
        AggregateEntity a = new AggregateEntity();
        a.setEngine("Engine");
        a.setMarket_name("MarketName");
        a.setMarket_title("MarketTitle");
        a.setNumtrades("Numtrades");
        a.setSecid("Secid");
        a.setTradedate("TradeDate");
        a.setValue("Value");
        a.setVolume("Volume");

        aggregateRepository.save(a);
    }
}
