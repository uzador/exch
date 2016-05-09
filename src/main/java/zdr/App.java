package zdr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        log.info("Loader started");
        Loader loader = ctx.getBean("loader", Loader.class);
//        loader.loadTradeVolumes("SBER", LocalDate.of(2016, 5, 1));
        loader.loadTradeVolumes("SBER", LocalDate.of(2013, 3, 25));
        log.info("Loader finished");

        SpringApplication.exit(ctx);
    }

    /*@Bean
    public LoaderService getLoaderService() {
        return new LoaderService();
    }*/

    /*@Bean
    public Loader getLoader() {
        return new Loader();
    }*/
}
