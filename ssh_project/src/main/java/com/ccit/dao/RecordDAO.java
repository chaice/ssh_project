package com.ccit.dao;

import com.ccit.pojo.Record;
import com.ccit.utils.Page;
import com.ccit.utils.QueryParam;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class RecordDAO extends BaseDAO<Record,Integer> {
    @Inject
    private PatientDAO patientDAO;
    public List<Record> findByPatientId(Integer id) {
        Criteria criteria = getSession().createCriteria(Record.class);
        criteria.add(Restrictions.eq("patient.id",id));
        return criteria.list();
    }

    @Override
    public Page<Record> findAll(List<QueryParam> paramList, Integer p) {
        Criteria criteria = getSession().createCriteria(Record.class);
        criteria.createAlias("patient","patient");
        return super.findAll(criteria,paramList,p);
    }
}
