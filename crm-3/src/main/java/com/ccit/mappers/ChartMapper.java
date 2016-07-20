package com.ccit.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ChartMapper {
    Long getNewAdd(@Param("start")String start, @Param("end") String end);
    Long salesed(@Param("start")String start, @Param("end") String end);
    Float sales(@Param("start")String start, @Param("end") String end);
    List<Map<String,Object>> getUserValue(@Param("start")String start, @Param("end") String end);
    List<Map<String,Object>> gerProgressCount(@Param("start")String start, @Param("end") String end);
}
