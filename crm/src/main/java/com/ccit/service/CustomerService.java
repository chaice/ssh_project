package com.ccit.service;

import com.ccit.mappers.CustomerMapper;
import com.ccit.pojo.Customer;
import com.ccit.pojo.User;
import com.ccit.utils.PinYinUtil;
import com.ccit.utils.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class CustomerService {
    @Inject
    private CustomerMapper customerMapper;

    public List<Customer> findByParams(Map<String, Object> params) {
        Integer userid = ShiroUtil.getPrincipal().getId();
        params.put("userid",userid);
        return customerMapper.findByParams(params);
    }

    public Long countByParams(Map<String, Object> params) {
        Integer userid = ShiroUtil.getPrincipal().getId();
        params.put("userid",userid);
        return customerMapper.countByParams(params);
    }

    public Long count() {
        return customerMapper.count();
    }

    public void insert(Customer customer) {
        User user = ShiroUtil.getPrincipal();
        String creatuser = user.getName();
        Integer userid = user.getId();
        customer.setCreatuser(creatuser);
        customer.setUserid(userid);
        if(customer.getCompanyid() != null){
            Customer company = customerMapper.findById(userid,customer.getCompanyid());
            customer.setCompanyname(company.getName());
        }
        String pinyin = PinYinUtil.getPinYin(customer.getName());
        customer.setPinyin(pinyin);
        customerMapper.insert(customer);
    }
}
