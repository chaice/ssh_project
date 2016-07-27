package com.ccit.test;

import com.ccit.pojo.Dept;
import com.ccit.pojo.Employee;
import com.ccit.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestManyToOne {
    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = new Dept();
        dept.setDeptname("java开发部");

        Employee employee1 = new Employee();
        employee1.setEmpname("Tili");


        Employee employee2 = new Employee();
        employee2.setEmpname("Kucy");

        Set<Employee> employeeSet = new HashSet<Employee>();
        employeeSet.add(employee1);
        employeeSet.add(employee2);
        dept.setEmployeeSet(employeeSet);

        session.save(dept);
        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();
    }
    @Test
    public void testFind(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class,15);
        Set<Employee> employeeSet = dept.getEmployeeSet();
        for(Employee employee :employeeSet){
            System.out.println(employee.getEmpname());
        }

        session.getTransaction().commit();
    }
    /*N+1 问题
    在many-to-one加 fetch='join'
     */
    @Test
    public void testFindByMany(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();

        for(Employee employee : employeeList){
            System.out.println(employee.getEmpname() + ":" + employee.getDept().getDeptname());
        }

        session.getTransaction().commit();
    }
    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class,18);

        session.delete(dept);

        session.getTransaction().commit();
    }
}
