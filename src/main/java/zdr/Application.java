package zdr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        /*String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/

        Loader loader = (Loader) ctx.getBean("loader", Loader.class);
        loader.run();
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
