package com.ccit.mappers;


import com.ccit.pojo.Topic;

import java.util.List;
import java.util.Map;

public interface TopicMapper {
    void insert(Topic topic);
    void delete(Integer id);
    void update(Topic topic);

    Topic findOneById(Integer id);
    List<Topic> findAll();
    List<Topic> findByIds(Integer...integers);
    List<Topic> findByIf(Map<String,Object>map);
    void deleteByIds(Integer...integers);
    void inserts(List<Topic>topicList);
}
