package zdr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zdr.dto.AggregateEntity;
import zdr.service.Manager;

/**
 * Created by yzadorozhnyy on 11.05.2016.
 */
@Controller
public class IndexController {

    @Autowired
    Manager manager;

    @RequestMapping("/")
    public String index(@RequestParam(value = "id", required = false, defaultValue = "1") Long id, Model model) {
        AggregateEntity aggregateEntity = manager.getAggregateEntityById(id);
        model.addAttribute("name", id);
        return "index";
    }
}
