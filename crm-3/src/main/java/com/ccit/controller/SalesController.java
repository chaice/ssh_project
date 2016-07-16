package com.ccit.controller;

import com.ccit.pojo.DataTableResult;
import com.ccit.pojo.Sales;
import com.ccit.pojo.SalesFile;
import com.ccit.pojo.SalesLog;
import com.ccit.service.CustomerService;
import com.ccit.service.SalesFileService;
import com.ccit.service.SalesLogService;
import com.ccit.service.SalesService;
import com.google.common.collect.Maps;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SalesController {
    @Inject
    private SalesService salesService;
    @Inject
    private CustomerService customerService;
    @Inject
    private SalesFileService salesFileService;
    @Inject
    private SalesLogService salesLogService;
    @RequestMapping(method = RequestMethod.GET)
    public String salesList(Model model){
        model.addAttribute("cuslist",customerService.findAll());
        return "sales/saleslist";
    }
    @ResponseBody
    @RequestMapping(value = "/saleslist",method = RequestMethod.GET)
    public DataTableResult<Sales> list(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        Map<String,Object> params = Maps.newHashMap();
        params.put("start",start);
        params.put("length",length);
        List<Sales> data = salesService.findByParams(params);
        Long recordTotal = salesService.count();
        Long recordFilter = salesService.countByParams(params);
        DataTableResult<Sales> result = new DataTableResult<Sales>(draw,recordTotal,recordFilter,data);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newSales(Sales sales){
        salesService.add(sales);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String salesDelete(@PathVariable Integer id){
        salesService.delete(id);
        return "success";
    }
    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public String view(@PathVariable Integer id,Model model) throws NotFoundException {
        Sales sales = salesService.findBy(id);
        List<SalesFile> salesFiles = salesFileService.findBySalesId(id);
        List<SalesLog> salesLogs = salesLogService.findBySalesId(id);
        if(sales == null) {
            throw new NotFoundException("没有找到!");
        }
        model.addAttribute("salesLogs",salesLogs);
        model.addAttribute("salesFile",salesFiles);
        model.addAttribute("sales",sales);
        return "sales/view";
    }
}
