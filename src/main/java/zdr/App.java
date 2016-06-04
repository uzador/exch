package zdr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import zdr.util.AppConfig;
import zdr.util.SymbolConfig;
import zdr.util.Util;

import java.time.LocalDate;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        if (AppConfig.getInstance().get("mode") != null && AppConfig.getInstance().get("mode").equals("loader")) {
            log.info("Loader started");
            final Loader loader = ctx.getBean("loader", Loader.class);
            SymbolConfig.getInstance()
                    .getKeys()
                    .stream()
                    .forEach(key -> loader.loadTradeVolumes(key, LocalDate.parse(SymbolConfig.getInstance().get(key), Util.formatter)));

//        loader.loadTradeVolumes("SBER", startDate);
//        loader.loadTradeVolumes("SBER", LocalDate.of(2016, 5, 23));
//        loader.loadTradeVolumeOnCurrentDate();
            log.info("Loader finished");

//        SpringApplication.exit(ctx);
        }
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
