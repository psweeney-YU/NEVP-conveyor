package edu.ou.herbarium.utils;

import org.hibernate.Transaction;

import edu.ou.herbarium.data.HibernateSessionFactory;
import edu.ou.herbarium.service.Collector;
import edu.ou.herbarium.service.CollectorDAO;
import edu.ou.herbarium.service.Huhbotanistsrdf2013oct23DAO;

public class CollectorNameVerify {
	public static void main (String args[]){
		CollectorNameVerify cnv = new CollectorNameVerify();
		cnv.verify();
	}
	
	public void verify(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO da0 = new CollectorDAO();
		Collector cc = new Collector();
		
		int c = 0x00D7;
		String symbol = Character.toString((char)c);
		cc.setCollectorFullName("Àóöàáäü Êèéëȇ+×"+symbol);
		da0.attachDirty(cc);
		t.commit();
	}
}
