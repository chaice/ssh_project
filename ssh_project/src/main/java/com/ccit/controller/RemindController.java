package com.ccit.controller;

import com.ccit.service.RecordService;
import com.ccit.utils.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/remind")
public class RemindController {
    @Inject
    private RecordService recordService;
    @RequestMapping(method = RequestMethod.GET)
    public String remind(HttpServletRequest request, Model model,
                         @RequestParam(required = false,defaultValue = "1")Integer p){
        List<QueryParam> paramList = QueryParam.getParam(request);
        model.addAttribute("page",recordService.findAll(paramList,p));
        return "remind/remind";
    }
}
