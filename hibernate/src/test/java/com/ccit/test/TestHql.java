package com.ccit.test;

import com.ccit.pojo.User;
import com.ccit.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import java.util.List;

/*
自由态:对象在内存中存在,在数据库中没有数据与其相关联
持久态:持久化对象处于由hibernate管理的状态,这种状态下对象的变化会被同步到数据库中
游离态:处于持久态的对象,在其对应的session关闭之后,进入游离态


Object---->持久态  load();get();
自由态---->持久态  save();persist();saveOrupdate();
游离态---->持久态  update();saveOrUpdate();
持久态---->自由态  delete();
持久态---->游离态  commit();close();clear()//清除session中所关联的所有对象,使其成为游离态;


*/

public class TestHql {

    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        for(User user : userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }
    /*
    save();保存完后会返回自动增长的ID值
    persist();不会返回值
    在IPA规范中没有save();只有persist();
     */
    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = new User();
        user.setUsername("cc");
        user.setPassword("123123");

        Integer id = (Integer) session.save(user);
        System.out.println(id);

        session.persist(user);

        session.getTransaction().commit();
    }
    /*
    都是查找对象
    get();当查找的对象不存在时会返回null
    load();会进行懒加载,只有当使用到对象时才会去查找;查找的对象不存在时永远不会返回null,
    返回ObjectNotFoundException
     */
    @Test
    public void testFindById(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,51);
        user.setUsername("jim");

        User user1 = (User) session.load(User.class,51);
        System.out.println(user1.getId()+":"+user1.getUsername());

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        user.setUsername("tom");
        session1.update(user);

        session1.getTransaction().commit();
    }
    /*
    merge();不会改变对象的状态,执行完没有id值.
    给merge一个有id的对象时,merge会先执行select,看是否存在这个对象,再决定是否执行Insert
     */
    @Test
    public void testMerge(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();//返回transaction对象
        User user = new User();
        user.setUsername("大雷");
        user.setPassword("123123");

        session.merge(user);

        System.out.println(user.getId());

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();
        User user1 = (User) session1.get(User.class,54);
        session1.merge(user1);

        session1.getTransaction().commit();
    }
    /*
    saveorupdate();会自动根据对象所处状态选择执行save还是update
     */
    @Test
    public void testSaveOrUpdate(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("小明");
        user.setPassword("123123");

        session.saveOrUpdate(user);

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.saveOrUpdate(user);

        session1.getTransaction().commit();
    }
    //delete();只能根据对象删除
    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,51);

        session.delete(user);

        session.getTransaction().commit();
    }


}
