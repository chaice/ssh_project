package com.ccit.controller;

import com.ccit.pojo.Customer;
import com.ccit.pojo.DataTableResult;
import com.ccit.service.CustomerService;
import com.ccit.service.UserService;
import com.google.common.collect.Maps;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Inject
    private CustomerService customerService;
    @Inject
    private UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    public String customer(Model model){
        List<Customer> company = customerService.findAllCompany();
        model.addAttribute("companyList",company);
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
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable Integer id){
        customerService.deleteById(id);
        return "redirect:/customer";
    }
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateCustomer(Customer customer){
        customerService.update(customer);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Customer update(@PathVariable Integer id){
        return customerService.findById(id);
    }
    @RequestMapping(value = "/view/{id}",method =RequestMethod.GET)
    public String view(@PathVariable Integer id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        model.addAttribute("userlist",userService.findAll());
        return "customer/view";
    }
    @RequestMapping(value = "/public/{id}",method = RequestMethod.GET)
    public String transformPublic(@PathVariable Integer id) throws NotFoundException {
        customerService.tranformPublic(id);
        return "redirect:/customer";
    }
    @RequestMapping(value = "/transformUser",method = RequestMethod.POST)
    public String transform(Integer userid,@RequestParam Integer id) throws NotFoundException {
        customerService.transformUser(id,userid);
        return "redirect:/customer";
    }
    @RequestMapping(value = "/build/{id}",method = RequestMethod.GET)
    public void build(@PathVariable Integer id){
        
    }
}
