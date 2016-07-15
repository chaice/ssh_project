package com.ccit.service;

import com.ccit.mappers.NoticeMapper;
import com.ccit.pojo.Notice;
import com.ccit.pojo.User;
import com.ccit.utils.ShiroUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Named
public class NoticeService {
    @Inject
    private NoticeMapper noticeMapper;
    @Value("${filepath}")
    private String filepath;

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

    public String saveFile(InputStream inputStream) throws IOException {
        String uuid = UUID.randomUUID().toString();
        FileOutputStream outputStream = new FileOutputStream(new File(filepath,uuid));
        IOUtils.copy(inputStream,outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return "/preview/"+uuid;
    }
}
