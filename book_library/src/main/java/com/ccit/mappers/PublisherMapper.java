package com.ccit.mappers;

import com.ccit.pojo.Publisher;

import java.util.List;

public interface PublisherMapper {
    List<Publisher> findAll();
    Publisher findByPubname(String pubname);
}
