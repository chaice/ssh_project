package com.ccit.controller;

import com.ccit.pojo.Patient;
import com.ccit.service.PatientService;
import com.ccit.service.RecordService;
import com.ccit.utils.Page;
import com.ccit.utils.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Inject
    private PatientService patientService;
    @Inject
    private RecordService recordService;
    @RequestMapping(method = RequestMethod.GET)
    public String patient(Model model, HttpServletRequest request,
                          @RequestParam(required = false,defaultValue = "1")Integer p){
        List<QueryParam> paramList = QueryParam.getParam(request);
        Page<Patient> page = patientService.findAll(paramList,p);
        model.addAttribute("page",page);
        model.addAttribute("stateList",patientService.findAllState());
        model.addAttribute("sexList",patientService.findAllSex());
        model.addAttribute("insList",patientService.findAllInsurance());
        return "patient/patient";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Patient patient){
        patientService.saveOrUpdate(patient);
        return "redirect:/patient";
    }
//    @ResponseBody
//    @RequestMapping(value = "/check",method = RequestMethod.GET)
//    public String check(@RequestParam String idnumber){
//        String regx = "(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)";
//        Pattern r = Pattern.compile(regx);
//        //TODO 正则表达式
//    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        patientService.delete(id);
        return "redirect:/patient";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Patient update(@PathVariable Integer id){
        return patientService.findById(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Patient patient){
        patientService.saveOrUpdate(patient);
        return "redirect:/patient";
    }
    @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public String view(@PathVariable Integer id,Model model){
        model.addAttribute("stateList",patientService.findAllState());
        model.addAttribute("sexList",patientService.findAllSex());
        model.addAttribute("insList",patientService.findAllInsurance());
        model.addAttribute("patient",patientService.findById(id));
        model.addAttribute("recordList",recordService.findByPatientId(id));
        return "patient/view";
    }
}
