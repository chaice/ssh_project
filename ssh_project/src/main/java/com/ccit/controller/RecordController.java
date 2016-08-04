package com.ccit.controller;

import com.ccit.pojo.Patient;
import com.ccit.pojo.Record;
import com.ccit.service.IllService;
import com.ccit.service.PatientService;
import com.ccit.service.RecordService;
import com.ccit.service.UserService;
import com.ccit.utils.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Inject
    private PatientService patientService;
    @Inject
    private UserService userService;
    @Inject
    private IllService illService;
    @Inject
    private RecordService recordService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String recordList(@RequestParam(required = false,defaultValue = "1")Integer p, HttpServletRequest request, Model model){
        List<QueryParam> queryList = QueryParam.getParam(request);
        model.addAttribute("page", recordService.findAll(queryList,p));
        return "record/recordlist";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String record(Model model){
        model.addAttribute("officeList",userService.findAllOffice());
        model.addAttribute("illList",illService.findAll());
        return "record/record";
    }
    @ResponseBody
    @RequestMapping(value = "/patient.json",method = RequestMethod.GET)
    public List<Patient> getMsg(){
        return patientService.findAll();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Record record){
        recordService.add(record);
        return "redirect:/record/list";
    }
    @ResponseBody
    @RequestMapping(value = "/addfile",method = RequestMethod.POST)
    public Integer addFile(MultipartFile file){
         return recordService.saveFile(file);
    }

    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public String patientRecord(@PathVariable Integer id){
            return "";
    }
}
