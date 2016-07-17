package com.ccit.mappers;


import com.ccit.pojo.SalesFile;

import java.util.List;

public interface SalesFileMapper {

    List<SalesFile> findBySalesId(Integer salesid);

    void deleteBySalesId(Integer salesid);

    void addFile(SalesFile salesFile);

    SalesFile findById(Integer id);
}
