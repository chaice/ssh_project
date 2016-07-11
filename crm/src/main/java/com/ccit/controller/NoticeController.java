package com.ccit.controller;

import com.ccit.pojo.Notice;
import com.ccit.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

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
        return "notice/notice";
    }
}
