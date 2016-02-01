package edu.ou.herbarium.utils;

import java.io.File;
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

public class InsertCollector {
	public static void main(String args[]) throws XmlToolException{
		int index = 1;
		while(true){
			if(index==79878) break;
			File file = new File("C:/Users/eming/Desktop/Installation/output/"+index+".xml");
			if(file.exists()==false) break;
			XmlTool xml = new XmlTool();
			xml.initialize(file);
//			Element root = xml.get_doc().getDocumentElement();
			try{
				Element root = xml.selectElement("/RDF/Person");
				String [] abouts = root.getAttribute("about").split("/");
				String uuid = abouts[abouts.length-1];
				String label = "";
				String resource = "";
				for(int i=0;i<root.getChildNodes().getLength();i++){
					if(root.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
						Element ele = (Element)root.getChildNodes().item(i);
						if(ele.getTagName().equals("isPrimaryTopicOf")){
							resource = ele.getAttribute("resource");
							
						}
						else if(ele.getTagName().equals("label")){
							label = ele.getTextContent();
						}
					}
				}
				Collector collector = new Collector();
				collector.setCollectorFullName(label);
				collector.setCollectorInfo(resource);
				collector.setGuid(uuid);
				
				try{
					Element birthEle = xml.selectElement("/RDF/Birth");
					for(int i=0;i<birthEle.getChildNodes().getLength();i++){
						if(birthEle.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
							Element ele = (Element)birthEle.getChildNodes().item(i);
							if(ele.getTagName().equals("date")){
								collector.setBirth(ele.getTextContent());
								
							}
						}
					}
					
				}catch(Exception e){
					
				}
				
				try{
					Element deathEle = xml.selectElement("/RDF/Death");
					for(int i=0;i<deathEle.getChildNodes().getLength();i++){
						if(deathEle.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
							Element ele = (Element)deathEle.getChildNodes().item(i);
							if(ele.getTagName().equals("date")){
								collector.setDeath(ele.getTextContent());
								
							}
						}
					}
				}catch(Exception e){
					
				}
				Transaction t = HibernateSessionFactory.getSession().beginTransaction();
				CollectorDAO insDao = new CollectorDAO();
				insDao.attachDirty(collector);
				t.commit();
				
				
			}catch(Exception e){
				Element root = xml.selectElement("/RDF/Group");
				String [] abouts = root.getAttribute("about").split("/");
				String uuid = abouts[abouts.length-1];
				String label = "";
				String resource = "";
				for(int i=0;i<root.getChildNodes().getLength();i++){
					if(root.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
						Element ele = (Element)root.getChildNodes().item(i);
						if(ele.getTagName().equals("isPrimaryTopicOf")){
							resource = ele.getAttribute("resource");
							
						}
						else if(ele.getTagName().equals("label")){
							label = ele.getAttribute("resource");
						}
					}
				}
				Collector collector = new Collector();
				collector.setCollectorFullName(label);
				collector.setCollectorInfo(resource);
				collector.setGuid(uuid);
				collector.setIsGroup(1);
				Transaction t = HibernateSessionFactory.getSession().beginTransaction();
				CollectorDAO insDao = new CollectorDAO();
				insDao.attachDirty(collector);
				System.out.println("insert "+index);
				t.commit();
			}
			index++;
		}
		
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
