package com.ccit.mappers;


import com.ccit.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    void save(Notice notice);

    List<Notice> findByParams(Map<String, Object> params);

    Long getCount();

    Notice findById(Integer id);
}
