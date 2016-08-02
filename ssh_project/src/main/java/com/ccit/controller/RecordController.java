package com.ccit.controller;

import com.ccit.pojo.Patient;
import com.ccit.pojo.Record;
import com.ccit.service.IllService;
import com.ccit.service.PatientService;
import com.ccit.service.RecordService;
import com.ccit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
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
        return "redirect:/record";
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
