package com.ccit.controller;

import com.ccit.pojo.Ill;
import com.ccit.service.IllService;
import com.ccit.service.UserService;
import com.ccit.utils.Page;
import com.ccit.utils.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ill")
public class IllController {
    @Inject
    private IllService illService;
    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String ill(Model model, HttpServletRequest request,
                      @RequestParam(required = false,defaultValue = "1")Integer p){
        List<QueryParam> queryParamList = QueryParam.getParam(request);
        Page<Ill> page = illService.findAll(queryParamList,p);
        model.addAttribute("officeList",userService.findAllOffice());
        model.addAttribute("page",page);
        return "ill/ill";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Ill ill){
        illService.saveOrUpdate(ill);
        return "redirect:/ill";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        illService.delete(id);
        return "redirect:/ill";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Ill update(@PathVariable Integer id){
        return illService.findById(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Ill ill){
        illService.saveOrUpdate(ill);
        return "redirect:/ill";
    }

}
