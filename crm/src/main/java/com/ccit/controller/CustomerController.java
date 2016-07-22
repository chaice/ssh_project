package com.ccit.controller;

import com.ccit.pojo.Customer;
import com.ccit.pojo.DataTableResult;
import com.ccit.service.CustomerService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Inject
    private CustomerService customerService;
    @RequestMapping(method = RequestMethod.GET)
    public String customer(Model model){

        return "customer/customer";
    }
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public DataTableResult<Customer> customer(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");
        Map<String,Object> params = Maps.newHashMap();
        params.put("start",start);
        params.put("length",length);
        params.put("keyword",keyword);
        List<Customer> customerList = customerService.findByParams(params);
        Long recordsTotal = customerService.count();
        Long recordsFiltered = customerService.countByParams(params);
        DataTableResult<Customer> result = new DataTableResult<Customer>(draw,recordsTotal,recordsFiltered,customerList);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String addCustomer(Customer customer){
         customerService.insert(customer);
        return "success";
    }
}
