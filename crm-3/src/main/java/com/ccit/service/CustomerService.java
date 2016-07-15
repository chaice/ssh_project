package com.ccit.service;

import com.ccit.mappers.CustomerMapper;
import com.ccit.mappers.RoleMapper;
import com.ccit.pojo.Customer;
import com.ccit.pojo.Role;
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
    @Inject
    private RoleMapper roleMapper;
    public List<Customer> findByParams(Map<String, Object> params) {
        User user = ShiroUtil.getPrincipal();
        Role role = roleMapper.findById(user.getRoleid());
        if(role.getRolename().equals("经理")) {
            return customerMapper.findByParams(params);
        }
        params.put("userid",user.getId());
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
            Customer company = customerMapper.findById(customer.getCompanyid());
            customer.setCompanyname(company.getName());
        }
        String pinyin = PinYinUtil.getPinYin(customer.getName());
        customer.setPinyin(pinyin);
        customerMapper.insert(customer);
    }

    public List<Customer> findAllCompany() {
        return customerMapper.findAllCompany(Customer.TYPE_COMPANY,ShiroUtil.getPrincipal().getId());
    }

    public void deleteById(Integer id) {
        if(customerMapper.findById(id).getType().equals(Customer.TYPE_COMPANY)){
            List<Customer> list = customerMapper.findByCompanyId(id);
            if(list != null){
                for (Customer customer: list) {
                    customer.setCompanyid(null);
                    customer.setCompanyname("");
                    customerMapper.update(customer);
                }
            }
        }
        customerMapper.delete(id);
    }

    public void update(Customer customer) {
        if(customerMapper.findById(customer.getId()).getType().equals(Customer.TYPE_COMPANY)){
            List<Customer> list = customerMapper.findByCompanyId(customer.getId());
            if(list != null){
                for(Customer cus : list){
                    cus.setCompanyname(customer.getName());
                    customerMapper.update(cus);
                }
            }
            customerMapper.update(customer);
        }
        customer.setCompanyname(customerMapper.findById(customer.getCompanyid()).getName());
        customerMapper.update(customer);
    }

    public Customer findById(Integer id) {
       return customerMapper.findById(id);
    }
}
