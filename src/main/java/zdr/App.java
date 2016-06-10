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
import java.util.Objects;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        AppConfig.getInstance()
                .get("mode")
                .filter(mode -> mode.equals("loader"))
                .ifPresent(mode -> {
                    log.info("Mode {} started", mode);

                    final Loader loader = ctx.getBean("loader", Loader.class);
                    SymbolConfig.getInstance()
                            .getKeys()
                            .stream()
                            .forEach(key -> loader.loadTradeVolumes(key, LocalDate.parse(SymbolConfig.getInstance().get(key), Util.formatter)));

                    log.info("Mode {} finished", mode);
                });

        AppConfig.getInstance()
                .get("mode")
                .filter(mode -> mode.equals("updater"))
                .ifPresent(mode -> {
                    log.info("Mode {} started", mode);

                    final Loader loader = ctx.getBean("loader", Loader.class);
                    SymbolConfig.getInstance()
                            .getKeys()
                            .stream()
                            .forEach(key -> loader.updateTradeVolumes(key));

                    log.info("Mode {} finished", mode);
                });

        AppConfig.getInstance()
                .get("mode")
                .filter(mode -> mode.equals("current"))
                .ifPresent(mode -> {
                    log.info("Mode {} started", mode);

                    final Loader loader = ctx.getBean("loader", Loader.class);
                });
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
