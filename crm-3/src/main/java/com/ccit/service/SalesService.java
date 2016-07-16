package com.ccit.service;

import com.ccit.mappers.CustomerMapper;
import com.ccit.mappers.SalesLogMapper;
import com.ccit.mappers.SalesMapper;
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
        //Todo 文件,记录 暂时没做好.删除wait...
    }

    public Sales findBy(Integer id) {
        if(ShiroUtil.isManager()){
            return salesMapper.findById(id,null);
        }
         return salesMapper.findById(id,ShiroUtil.getPrincipal().getId());
    }
}
