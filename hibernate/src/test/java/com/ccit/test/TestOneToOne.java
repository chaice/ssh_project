package com.ccit.test;

import com.ccit.pojo.Card;
import com.ccit.pojo.Person;
import com.ccit.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class TestOneToOne {
    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = new Person();
        person.setName("王六");

        Card card = new Card();
        card.setCardname("vip-111");
        card.setPerson(person);

        session.save(person);
        session.save(card);

        session.getTransaction().commit();
    }
   @Test
    public void testFind(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class,16);
        System.out.println(person.getCard().getCardname());

        session.getTransaction().commit();
    }
    @Test
    public void testFindCard(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class,16);
        System.out.println(card.getCardname());
        System.out.println(card.getPerson().getName());
        session.getTransaction().commit();
    }
    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

//        Card card = (Card) session.get(Card.class,16);
//        session.delete(card);
        Person person = (Person) session.get(Person.class,17);
        session.delete(person);
        session.getTransaction().commit();
    }
}
