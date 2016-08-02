package com.ccit.service;

import com.ccit.dao.*;
import com.ccit.pojo.*;
import com.ccit.utils.AgeUtil;
import com.ccit.utils.Page;
import com.ccit.utils.QueryParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Named
@Transactional
public class PatientService {
    @Inject
    private PatientDAO patientDAO;
    @Inject
    private StateDAO stateDAO;
    @Inject
    private SexDAO sexDAO;
    @Inject
    private InsuranceDAO insuranceDAO;
    @Inject
    private RecordDAO recordDAO;

    public List<State> findAllState(){
        return stateDAO.findAll();
    }

    public List<Sex> findAllSex(){
        return sexDAO.findAll();
    }

    public List<Insurance> findAllInsurance(){
        return insuranceDAO.findAll();
    }

    public void saveOrUpdate(Patient patient){
        String age = AgeUtil.getAge(patient.getIdnumber());
        patient.setAge(age);
        patientDAO.saveOrUpdate(patient);
    }

    public Patient findById(Integer id){
        return patientDAO.findById(id);
    }

    public void delete(Integer id){
        List<Record> recordList = recordDAO.findByPatientId(id);
        for(Record record : recordList){
            recordDAO.delete(record);
        }
        patientDAO.deleteById(id);
    }

    public Page<Patient> findAll(List<QueryParam> queryParams,Integer p){
        return patientDAO.findAll(queryParams,p);
    }

    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

}
