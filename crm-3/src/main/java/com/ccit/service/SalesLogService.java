package com.ccit.service;

import com.ccit.mappers.SalesLogMapper;
import com.ccit.mappers.SalesMapper;
import com.ccit.pojo.Sales;
import com.ccit.pojo.SalesLog;
import com.ccit.utils.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class SalesLogService {

    @Inject
    private SalesLogMapper salesLogMapper;
    @Inject
    private SalesMapper salesMapper;
    public  List<SalesLog> findBySalesId(Integer salesid) {
            Sales sales = salesMapper.findById(salesid,null);
            SalesLog salesLog = new SalesLog();
            String context = sales.getCreattime()+" "+sales.getCreatuser()+"创建了:"+sales.getName();
            salesLog.setContext(context);
            salesLog.setSalesid(salesid);
            salesLog.setType(SalesLog.TYPE_AUTO);
            if(salesLogMapper.findByid(1) == null){

            }
        return salesLogMapper.findBySalesId(salesid);
    }
}
