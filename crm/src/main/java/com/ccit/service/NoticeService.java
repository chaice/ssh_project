package com.ccit.service;

import com.ccit.mappers.NoticeMapper;
import com.ccit.pojo.Notice;
import com.ccit.pojo.User;
import com.ccit.utils.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class NoticeService {
    @Inject
    private NoticeMapper noticeMapper;

    public void save(Notice notice) {
        User user = ShiroUtil.getPrincipal();
        notice.setRealname(user.getName());
        notice.setUserid(user.getId());
        noticeMapper.save(notice);
    }
}
