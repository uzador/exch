package zdr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import zdr.dao.ProductRepository;
import zdr.domain.Product;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    /*@Bean
    MyCustomService myCustomService() {
        return new MyCustomService("");
    }*/

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        Product p = new Product();
        p.setDescription("My product");
        p.setImageUrl("ImageUrl");
        p.setPrice(BigDecimal.ONE);
        p.setProductId("111");
    }
}
