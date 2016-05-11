package zdr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yzadorozhnyy on 11.05.2016.
 */
@RestController
public class SimpleController {

    @RequestMapping("/")
    public String index() {
        return "Greeting";
    }
}
