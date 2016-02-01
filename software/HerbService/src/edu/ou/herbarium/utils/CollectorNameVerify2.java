package edu.ou.herbarium.utils;

import org.hibernate.Transaction;

import edu.ou.herbarium.data.HibernateSessionFactory;
import edu.ou.herbarium.service.Collector;
import edu.ou.herbarium.service.CollectorDAO;
import edu.ou.herbarium.service.Huhbotanistsrdf2013oct23DAO;

public class CollectorNameVerify2 {
	public static void main (String args[]){
		CollectorNameVerify2 cnv = new CollectorNameVerify2();
		cnv.verify();
	}
	
	public void verify(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO da0 = new CollectorDAO();
		Collector c = da0.findById(248721);
		System.out.println(c.getCollectorFullName());
		
	}
}
