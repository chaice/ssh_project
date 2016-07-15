package com.ccit.utils;


import com.ccit.pojo.Customer;

public class MeCard {
    public static String getQRcode(Customer customer){
       String mecard = "MECARD:";
        if(customer.getName() != null){
            mecard = mecard+"N:"+customer.getName()+";";
        }
        if(customer.getTel() != null){
            mecard = mecard +"TEL:"+customer.getTel()+";";
        }
        if(customer.getCompanyname() != null){
            mecard = mecard +"ORG:"+customer.getCompanyname()+";";
        }
        if(customer.getEmail() != null){
            mecard = mecard +"EMAIL"+customer.getEmail()+";";
        }
        if(customer.getAddress() != null){
            mecard = mecard + "ADR:"+customer.getAddress()+";";
        }
        mecard = mecard +";";
        return mecard;
    }

}
