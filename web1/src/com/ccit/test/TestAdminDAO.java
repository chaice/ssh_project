package com.ccit.test;

import com.ccit.dao.AdminDAO;
import com.ccit.entity.Admin;
import org.junit.Test;

import java.util.List;

public class TestAdminDAO {
    AdminDAO ad = new AdminDAO();
    @Test
    public void testInsert(){
        Admin adm = new Admin();
        adm.setName("王重阳");
        adm.setPassword("2342");
        ad.insert(adm);
    }
    @Test
    public void testDelete(){
        Integer id = 9;
        ad.delete(id);
    }
    @Test
    public void testUpdate(){
        Admin adm = new Admin();
        adm.setId(11);
        adm.setName("许褚");
        adm.setPassword("1134");
        ad.update(adm);
    }
    @Test
    public void testFindOne(){
        System.out.println( ad.findOne(11));
    }
    @Test
    public void testFindAll(){
        List<Admin> list = ad.findAll();
        for (Admin ad:list) {
            System.out.println(ad);
        }
    }
}
