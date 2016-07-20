package com.ccit.controller;

import com.ccit.service.ChartService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chart")
public class ChartController {
    @Inject
    private ChartService chartService;
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
        Long add = chartService.getNewAdd();
        Long salesed = chartService.getSalesed();
        model.addAttribute("add",add);
        model.addAttribute("salesed",salesed);
        model.addAttribute("total",chartService.getMoney());
        return "chart/chart";
    }
    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Map<String, Object> getUser(){
        List<Map<String,Object>> list = chartService.getUserValue();
        List<Object> listname = Lists.newArrayList();
        List<Object> listvalue = Lists.newArrayList();
        for (Map<String,Object> map:list) {
            listname.add(map.get("name"));
            listvalue.add(map.get("price").toString());
        }
        Map<String,Object> result = Maps.newHashMap();
        result.put("listname",listname);
        result.put("listvalue",listvalue);
        return result;
    }
    @ResponseBody
    @RequestMapping(value ="/progress",method = RequestMethod.GET)
    public List<Map<String, Object>> gerProgressCount(){
        List<Map<String,Object>> list = chartService.gerProgressCount();
        return list;
    }
}
