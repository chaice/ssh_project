package com.ccit.controller;

import com.ccit.pojo.DataTableResult;
import com.ccit.pojo.Notice;
import com.ccit.service.NoticeService;
import com.google.common.collect.Maps;
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
public class NoticeController {
    @Inject
    private NoticeService noticeService;
    @RequestMapping(value = "/notice",method = RequestMethod.GET)
    public String notice(){
        return "notice/notice";
    }
    @RequestMapping(value = "/notice/new",method = RequestMethod.GET)
    public String addNotice(){
        return "/notice/notice_new";
    }
    @RequestMapping(value = "/notice/new",method = RequestMethod.POST)
    public String addNotice(Notice notice){
        noticeService.save(notice);
        return "redirect:/notice";
    }
    @RequestMapping(value = "/notice/data",method = RequestMethod.GET)
    @ResponseBody
    public DataTableResult<Notice> getNotice(HttpServletRequest request){
        String show = request.getParameter("show");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object> params = Maps.newHashMap();
        params.put("start",start);
        params.put("length",length);
        List<Notice> noticeList = noticeService.findByParams(params);
        Long recordsTotal = noticeService.getCount();
        Long recordsFiltered = noticeService.getCount();
        DataTableResult<Notice> result = new DataTableResult<Notice>(show,recordsTotal,recordsFiltered,noticeList);
        return result;
    }
    @RequestMapping(value = "/notice/{id}",method = RequestMethod.GET)
    public String viewNotice(@PathVariable("id")Integer id, Model model){
        Notice notice = noticeService.findById(id);
        model.addAttribute("notice",notice);
        return "notice/notice_view";
    }
}
