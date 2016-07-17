package com.ccit.service;

import com.ccit.mappers.SalesLogMapper;
import com.ccit.mappers.SalesMapper;
import com.ccit.pojo.Sales;
import com.ccit.pojo.SalesLog;
import com.ccit.utils.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.List;

@Named
public class SalesLogService {
    @Inject
    private SalesMapper salesMapper;
    @Inject
    private SalesLogMapper salesLogMapper;
    public  List<SalesLog> findBySalesId(Integer salesid) {
        Sales sales = salesMapper.findById(salesid,null);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sf.format(sales.getCreattime());
        String context = time+" "+sales.getCreatuser()+"创建了:"+sales.getName();
        if(salesLogMapper.findByContext(context) == null){
            SalesLog salesLog = new SalesLog();
            salesLog.setType(SalesLog.TYPE_AUTO);
            salesLog.setSalesid(salesid);
            salesLog.setContext(context);
            salesLogMapper.add(salesLog);
        }
        return salesLogMapper.findBySalesId(salesid);
    }
    public SalesLog findByContext(String context){
        return salesLogMapper.findByContext(context);
    }

    public void addlog(Integer salesid ,String context){
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm");
        SalesLog salesLog = new SalesLog();
        salesLog.setContext(context);
        salesLog.setType(SalesLog.TYPE_HAND);
        salesLog.setSalesid(salesid);
        salesLogMapper.add(salesLog);
    }
}
