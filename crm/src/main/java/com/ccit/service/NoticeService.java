package com.ccit.service;

import com.ccit.mappers.NoticeMapper;
import com.ccit.pojo.Notice;
import com.ccit.pojo.User;
import com.ccit.utils.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

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

    public List<Notice> findByParams(Map<String, Object> params) {
        return noticeMapper.findByParams(params);
    }

    public Long getCount() {
        return noticeMapper.getCount();
    }

    public Notice findById(Integer id) {
        return noticeMapper.findById(id);
    }
}
