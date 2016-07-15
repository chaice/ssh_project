package com.ccit.mappers;


import com.ccit.pojo.Document;

import java.util.List;

public interface DocumentMapper {
    List<Document> findAll(Integer fid);

    void insert(Document doc);

    Document findById(Integer id);
}
