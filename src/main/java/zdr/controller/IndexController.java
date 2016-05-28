package zdr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zdr.dto.AggregateEntity;
import zdr.dto.VolumeDate;
import zdr.service.Manager;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by yzadorozhnyy on 11.05.2016.
 */
@Controller
public class IndexController {

    @Autowired
    Manager manager;

    @RequestMapping("/")
    public String index(@RequestParam(value = "id", required = false, defaultValue = "1") Long id, Model model) {
        List<VolumeDate> chartDate = manager.getByMarketName("shares");
        chartDate.stream().forEach(System.out::println);
        model.addAttribute("name", id);
        return "index";
    }
}
