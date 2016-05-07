package zdr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
@SpringBootApplication
@EnableScheduling
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        /*String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/

//        Loader loader = ctx.getBean("loader", Loader.class);
//        loader.loadTradeVolumes("SBER", LocalDate.of(2016, 4, 5));
        log.error("Message logged at ERROR level");
        log.warn("Message logged at WARN level");
        log.info("Message logged at INFO level");
        log.debug("Message logged at DEBUG level");

//        Service service = new Service();
//        service.service();
    }

    @Bean
    public LoaderService getLoaderService() {
        return new LoaderService();
    }

    @Bean
    public Loader getLoader() {
        return new Loader();
    }
}
