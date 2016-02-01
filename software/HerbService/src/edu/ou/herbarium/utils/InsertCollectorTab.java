package edu.ou.herbarium.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.w3c.dom.Element;

import edu.ou.herbarium.data.HibernateSessionFactory;
import edu.ou.herbarium.service.Collector;
import edu.ou.herbarium.service.CollectorDAO;
import edu.ou.herbarium.service.Institute;
import edu.ou.herbarium.service.InstituteDAO;
import edu.ou.herbarium.xml.XmlTool;
import edu.ou.herbarium.xml.XmlToolException;

public class InsertCollectorTab {
	public static void main(String args[]) throws Exception{
		
		File file = new File("C:/Users/eming/Desktop/Installation/botanists data/individual_botanists_2012July02.tab");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String readLine = "";
		int index = -1;
		while((readLine = br.readLine())!=null){
			index++;
			if(index<185258) continue;
			readLine = readLine.replace('\t', '$');
			String [] readArray = readLine.split("\\$");
			ArrayList<String> strstck = new ArrayList<String>();
			for(int i=0;i<readArray.length;i++){
				if(strstck.size()==5||strstck.size()==6){
					strstck.add(readArray[i]+" ");
					System.out.println(readArray[i]+" ");
					continue;
				}
				else if(readArray[i]!=null&&readArray[i].equals("")==false){
					strstck.add(readArray[i]);
					System.out.println(readArray[i]);
				}
			}
			System.out.println("xxxxxxxxxxxxxxx"+index);
			Collector collector = new Collector();
			collector.setCollectorFullName(strstck.get(2));
			collector.setCollectorInfo(strstck.get(0));
			collector.setBirth(strstck.get(5));
			collector.setDeath(strstck.get(6));
			if(strstck.get(4).equals("Team")) 
				collector.setIsGroup(1);
			String [] abouts = strstck.get(0).split("/");
			String uuid = abouts[abouts.length-1];
			collector.setGuid(uuid);
			Transaction t = HibernateSessionFactory.getSession().beginTransaction();
			CollectorDAO insDao = new CollectorDAO();
			insDao.attachDirty(collector);
			t.commit();
		}
		
		
		
//		
//		
//		
//		
		
	}
	
	public Collector insertCollector(String insName, String insInfo){
		Collector col = new Collector();
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO insDao = new CollectorDAO();
//		col.setCollectorFullName(collectorFullName);
//		col.setInstituteName(insName);
		insDao.attachDirty(col);
		t.commit();
		return col;
	}
	//GUID
	//CollectorFullName
	//
}
