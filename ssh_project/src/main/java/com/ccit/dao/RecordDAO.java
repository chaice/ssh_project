package com.ccit.dao;

import com.ccit.pojo.Record;
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
    private SessionFactory sessionFactory;
    @Inject
    private PatientDAO patientDAO;
    public List<Record> findByPatientId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Record.class);
        criteria.add(Restrictions.eq("patient.id",id));
        return criteria.list();
    }
}
