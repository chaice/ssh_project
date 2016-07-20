package com.ccit.service;

import com.ccit.mappers.ChartMapper;
import com.ccit.pojo.Sales;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

@Named
public class ChartService {
    @Inject
    private ChartMapper chartMapper;

    public Long getNewAdd(){
        String start = DateTime.now().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        String end = DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd");
        return chartMapper.getNewAdd(start,end);
    }
    public Long getSalesed(){
        String start = DateTime.now().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        String end = DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd");
        return chartMapper.salesed(start,end);
    }
    public Float getMoney(){
        String start = DateTime.now().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        String end = DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd");
        Float total = chartMapper.sales(start,end);
        return total;
    }
    public List<Map<String,Object>> getUserValue(){
        String start = DateTime.now().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        String end = DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd");
        return chartMapper.getUserValue(start,end);
    }
    public List<Map<String,Object>> gerProgressCount(){
        String start = DateTime.now().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        String end = DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd");
        return chartMapper.gerProgressCount(start,end);
    }
}
