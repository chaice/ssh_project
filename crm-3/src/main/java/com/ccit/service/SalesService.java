package com.ccit.service;

import com.ccit.mappers.CustomerMapper;
import com.ccit.mappers.SalesFileMapper;
import com.ccit.mappers.SalesLogMapper;
import com.ccit.mappers.SalesMapper;
import com.ccit.pojo.Customer;
import com.ccit.pojo.Sales;
import com.ccit.pojo.SalesLog;
import com.ccit.pojo.User;
import com.ccit.utils.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class SalesService {
    @Inject
    private SalesMapper salesMapper;
    @Inject
    private CustomerMapper customerMapper;
    @Inject
    private SalesLogMapper salesLogMapper;
    @Inject
    private SalesFileMapper salesFileMapper;
    /**
     * 查找到销售机会列表
     * @param params 包括 start length 查询的参数
     * @return
     */
    public List<Sales> findByParams(Map<String, Object> params) {
        if(ShiroUtil.isManager()){
            return salesMapper.findByParams(params);
        }
        params.put("userid",ShiroUtil.getPrincipal().getId());
        return salesMapper.findByParams(params);
    }

    public Long countByParams(Map<String, Object> params) {
        if(ShiroUtil.isManager()){
            return salesMapper.countByParams(params);
        }
        params.put("userid",ShiroUtil.getPrincipal().getId());
        return salesMapper.countByParams(params);
    }

    public Long count() {
        if(ShiroUtil.isManager()){
            return salesMapper.count();
        }
        return salesMapper.countUser(ShiroUtil.getPrincipal().getId());
    }

    public void add(Sales sales) {
        sales.setUserid(ShiroUtil.getPrincipal().getId());
        sales.setCreatuser(ShiroUtil.getPrincipal().getName());
        sales.setCustomername(customerMapper.findById(sales.getCustomerid()).getName());
        salesMapper.add(sales);
    }

    /**
     * 删除sales,必须删除相关文件&删除相关记录
     * @param id
     */
    public void delete(Integer id) {
        if(ShiroUtil.isManager()){
            if(salesLogMapper.findBySalesId(id) != null){
                salesLogMapper.deleteBySalesId(id);
            }
            if(salesFileMapper.findBySalesId(id) != null){
                salesFileMapper.deleteBySalesId(id);
            }
            salesMapper.delete(id);
        }
    }

    public Sales findBy(Integer id) {
        if(ShiroUtil.isManager()){
            return salesMapper.findById(id,null);
        }
         return salesMapper.findById(id,ShiroUtil.getPrincipal().getId());
    }

    public void update(Sales sales) {
        Customer cus = customerMapper.findById(sales.getCustomerid());
        sales.setCustomername(cus.getName());
        Sales sale = salesMapper.findById(sales.getId(),ShiroUtil.getPrincipal().getId());
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm");
        sales.setLasttime(now);
        if (!sale.equals(sales)){
            String context = now + " "+ ShiroUtil.getPrincipal().getName();
            if(!sale.getName().equals(sales.getName())){
                context = context +"将销售机会名从"+sale.getName()+"修改为:"+sales.getName();
            }
            if(!sale.getValue().equals(sales.getValue())){
                context = context + " 将价值修改为:" +sales.getValue();
            }
            if(!sale.getCustomername().equals(cus.getName())){
                context = context + " 将关联客户修改为:" + cus.getName();
            }
            if(!sale.getProgress().equals(sales.getProgress())){
                context = context +" 进度修改为:"+sales.getProgress();
            }
            SalesLog saleslog = new SalesLog();
            saleslog.setSalesid(sales.getId());
            saleslog.setContext(context);
            saleslog.setType(SalesLog.TYPE_AUTO);
            salesLogMapper.add(saleslog);
        }
        salesMapper.update(sales);
    }
}
