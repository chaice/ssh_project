package com.ccit.service;

import com.ccit.dao.IllDAO;
import com.ccit.pojo.Ill;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class IllService {
    @Inject
    private IllDAO illDAO;

    public List<Ill> findAll(){
        return  illDAO.findAll();
    }

    public void saveOrUpdate(Ill ill) {
        illDAO.saveOrUpdate(ill);
    }

    public void delete(Integer id) {
        illDAO.deleteById(id);
    }

    public Ill findById(Integer id) {
        return illDAO.findById(id);
    }
}
