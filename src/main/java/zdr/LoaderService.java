package zdr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zdr.dao.AggregateRepository;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
public class LoaderService /*implements CommandLineRunner*/ {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AggregateRepository aggregateRepository;

    /*@Override
    public void run(String... strings) throws Exception {
    }*/

    /*@Scheduled(initialDelay = 1000, fixedRate = 60000)
    public void run() {
        logger.info("Number of books: " + aggregateRepository.count());
    }*/
}
