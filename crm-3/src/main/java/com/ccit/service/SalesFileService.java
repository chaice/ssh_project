package com.ccit.service;

import com.ccit.mappers.SalesFileMapper;
import com.ccit.pojo.SalesFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class SalesFileService {
    @Inject
    private SalesFileMapper salesFile;

    public List<SalesFile> findBySalesId(Integer salesid) {
        return salesFile.findBySalesId(salesid);
    }
}
