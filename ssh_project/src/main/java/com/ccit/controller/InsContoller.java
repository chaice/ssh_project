package com.ccit.controller;


import com.ccit.pojo.Insurance;
import com.ccit.service.InsuranceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/insurance")
public class InsContoller {
    @Inject
    private InsuranceService insuranceService;
    @RequestMapping(method = RequestMethod.GET)
    public String insurance(Model model){
        model.addAttribute("insList",insuranceService.findAll());
        return "insurance/insurance";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Insurance insurance){
        insuranceService.saveOrUpdate(insurance);
        return "redirect:/insurance";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        insuranceService.delete(id);
        return "redirect:/insurance";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Insurance update(@PathVariable Integer id){
       return insuranceService.findById(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Insurance insurance){
        insuranceService.saveOrUpdate(insurance);
        return "redirect:/insurance";
    }

}
