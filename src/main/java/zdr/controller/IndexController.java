package zdr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yzadorozhnyy on 11.05.2016.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(@RequestParam(value = "name", required = false, defaultValue = "Just Name") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
