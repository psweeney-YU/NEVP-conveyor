package edu.ou.herbarium.utils;

import java.util.List;

import org.hibernate.Transaction;

import edu.ou.herbarium.data.HibernateSessionFactory;
import edu.ou.herbarium.service.Collector;
import edu.ou.herbarium.service.CollectorDAO;
import edu.ou.herbarium.service.Huhbotanistsrdf2013oct23;
import edu.ou.herbarium.service.Huhbotanistsrdf2013oct23DAO;

public class CollectorMapping {
	public static void main(String args[]){
		CollectorMapping cm = new CollectorMapping();
		cm.getNewCollectors(Integer.parseInt(args[0]));
	}

	public void getNewCollectors(int page){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		Huhbotanistsrdf2013oct23DAO da0 = new Huhbotanistsrdf2013oct23DAO();
		CollectorDAO cdao = new CollectorDAO();
		List<Huhbotanistsrdf2013oct23> list = da0.findPage(page, 30000);
		for(int i=0;i<list.size();i++){
			Huhbotanistsrdf2013oct23 oct = list.get(i);
			Collector c = new Collector();
			c.setCollectorFullName(oct.getId().getCollectorName());
			if(oct.getId().getType().equals("group"))
				c.setIsGroup(1);
			else c.setIsGroup(0);
			c.setGuid(oct.getId().getHuhUri().replaceAll("http://purl.oclc.org/net/edu.harvard.huh/guid/uuid/", ""));
			c.setCollectorInfo(oct.getId().getHuhUri());
			cdao.attachDirty(c);
		}
		System.out.println(list.size()+"**"+list.get(0).getId().getId());
		t.commit();
	}
}
