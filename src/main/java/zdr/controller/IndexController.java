package zdr.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zdr.dto.AggregateEntity;
import zdr.dto.View;
import zdr.dto.VolumeDate;
import zdr.service.Manager;

import java.util.List;
import java.util.stream.Collectors;
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
//        List<VolumeDate> chartDate = manager.getByMarketName("shares");
//        chartDate.stream().forEach(System.out::println);
        model.addAttribute("name", id);
        return "index";
    }

    @JsonView(View.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/get-json")
    public List<long[]> getChartDataBySecidAndMarketName(@RequestParam(value = "marketname", required = true) String marketName,
                                                         @RequestParam(value = "secid", required = true) String secid) {
        List<VolumeDate> chartDate = manager.getByMarketName(marketName, secid);
//        Object[] chartDataArr = chartDate.stream().map(element -> new long[]{element.getDate(), element.getVolume()}).toArray();
        List<long[]> chartDataArr = chartDate.stream().map(element -> new long[]{element.getDate(), element.getVolume()}).collect(Collectors.toList());
        return chartDataArr;
    }

    @ResponseBody
    @RequestMapping(value = "get-secids")
    public List<String> getSecids() {
        return manager.getSecids();
    }

    @ResponseBody
    @RequestMapping(value = "get-marketname-by-secid")
    public List<String> get(@RequestParam(value = "secid", required = true) String secId) {
        return manager.getMarketNamesBySecid(secId);
    }
}
