package com.ccit.service;

import com.ccit.dao.InsuranceDAO;
import com.ccit.pojo.Insurance;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class InsuranceService {
    @Inject
    private InsuranceDAO insuranceDAO;

    public List<Insurance> findAll(){
        return insuranceDAO.findAll();
    }

    public void saveOrUpdate(Insurance insurance) {
        insuranceDAO.saveOrUpdate(insurance);

    }

    public void delete(Integer id) {
        insuranceDAO.deleteById(id);
    }

    public Insurance findById(Integer id) {
        return insuranceDAO.findById(id);
    }
}
