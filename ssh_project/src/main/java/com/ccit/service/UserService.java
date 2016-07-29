package com.ccit.service;

import com.ccit.dao.OfficeDAO;
import com.ccit.dao.RoleDAO;
import com.ccit.dao.UserDAO;

import com.ccit.pojo.Office;
import com.ccit.pojo.Role;
import com.ccit.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class UserService {
    @Inject
    private UserDAO userDAO;
    @Inject
    private RoleDAO roleDAO;
    @Inject
    private OfficeDAO officeDAO;

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public List<Role> findAllRole(){
        return roleDAO.findAll();
    }

    public void add(User user) {
        userDAO.saveOrUpdate(user);
    }

    public void delete(Integer id) {
        userDAO.deleteById(id);
    }

    public User findById(Integer id) {
       return userDAO.findById(id);
    }

    public List<Office> findAllOffice(){
        return officeDAO.findAll();
    }

    public void addOffice(Office office) {
        officeDAO.saveOrUpdate(office);
    }

    public void deleteOffice(Integer id) {
        officeDAO.deleteById(id);
    }

    public Office findOffice(Integer id) {
       return officeDAO.findById(id);
    }
}
