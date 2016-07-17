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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
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
    @Value("${filepath}")
    private String filepath;
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
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Sales getSales(@PathVariable Integer id) throws NotFoundException {
        if(salesService.findBy(id) != null){
            return salesService.findBy(id);
        }else{
            throw new NotFoundException("没有发现!");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateSales(Sales sales){
        salesService.update(sales);
        return "success";
    }
    @RequestMapping(value = "/addlog",method = RequestMethod.POST)
    public String saleslog(@RequestParam()Integer salesid,@RequestParam()String context){
        salesLogService.addlog(salesid, context);
        return "redirect:/sales/view/"+salesid;
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String fileUpload(MultipartFile file, @RequestParam("salesid")Integer salesid) throws FileNotFoundException {
       String filename = salesFileService.addFile(file,salesid);
        return "redirect:/sales/view/"+salesid;
    }
    @RequestMapping(value = "/down/{id}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> filedown(@PathVariable Integer id) throws NotFoundException, UnsupportedEncodingException, FileNotFoundException {
        SalesFile salesFile = salesFileService.findById(id);
        if(salesFile == null){
            throw new NotFoundException("没有发现文件!");
        }
        String filename = new String(salesFile.getName().getBytes("UTF-8"),"ISO-8859-1");
        File file = new File(filepath,salesFile.getFilename());
        if(!file.exists()){
            throw new NotFoundException("没有找到!");
        }
        FileInputStream input = new FileInputStream(file);
        return ResponseEntity
                .ok()
                .header("Context-Disposition","attachment;filename=\""+filename+"\"")
                .contentType(MediaType.parseMediaType(salesFile.getContenttype()))
                .contentLength(file.length())
                .body(new  InputStreamResource(input));
    }
}
