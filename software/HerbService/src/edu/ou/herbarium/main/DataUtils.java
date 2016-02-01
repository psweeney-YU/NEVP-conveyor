package edu.ou.herbarium.main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.catalina.util.MD5Encoder;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.Transaction;

import edu.ou.herbarium.data.HibernateSessionFactory;
import edu.ou.herbarium.service.Authority;
import edu.ou.herbarium.service.Collection;
import edu.ou.herbarium.service.CollectionDAO;
import edu.ou.herbarium.service.Collector;
import edu.ou.herbarium.service.CollectorDAO;
import edu.ou.herbarium.service.Folder;
import edu.ou.herbarium.service.FolderDAO;
import edu.ou.herbarium.service.Huhbotanistsrdf2013oct23;
import edu.ou.herbarium.service.Huhbotanistsrdf2013oct23DAO;
import edu.ou.herbarium.service.ImageRaw;
import edu.ou.herbarium.service.ImageRawDAO;
import edu.ou.herbarium.service.Institute;
import edu.ou.herbarium.service.InstituteDAO;
import edu.ou.herbarium.service.SpecCollectorMap;
import edu.ou.herbarium.service.SpecCollectorMapDAO;
import edu.ou.herbarium.service.Specimen;
import edu.ou.herbarium.service.SpecimenDAO;
import edu.ou.herbarium.service.System;
import edu.ou.herbarium.service.SystemDAO;
import edu.ou.herbarium.service.SystemSetting;
import edu.ou.herbarium.service.SystemSettingDAO;
import edu.ou.herbarium.service.User;
import edu.ou.herbarium.service.UserAuthorityMap;
import edu.ou.herbarium.service.UserAuthorityMapDAO;
import edu.ou.herbarium.service.UserDAO;
import edu.ou.herbarium.utils.ImageUtils;
import edu.ou.herbarium.utils.ImageUtils2;

public class DataUtils {
	public static String ports="";//GUID
	public static String  SERVER_DIR = "C:/work/apache-tomcat-7.0.40/webapps/HerbService/img"; 
	public static final String FULL_IMAGE_NAME = "full.jpg";
	public static final String THUMB_IMAGE_NAME = "full_3.jpg";
	public static final String THUMB_IMAGE_NAME_2 = "full_4.jpg";
	public static final String DISPLAY_IMAGE_NAME = "full_2.jpg";
	public static int currentFolder = 1;
	public Folder getTopFolders(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	FolderDAO folderDao = new FolderDAO();
    	List<Folder> topFolders = folderDao.findByProperty("folderId", "12");
    	topFolders.get(0).getFolders();
    	topFolders.get(0).getSpecimens();
    	for(Specimen spec : topFolders.get(0).getSpecimens()){
    		spec.getImageRaw();
    		Set<SpecCollectorMap> mapSet = spec.getSpecCollectorMaps();
    		for(SpecCollectorMap map : mapSet){
    			map.getCollector();
    		}
    	}
    	t.commit();
    	return topFolders.get(0);
	}
	
	public List<Institute> getInstitutes(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		InstituteDAO insDao = new InstituteDAO();
		List<Institute> institutes = insDao.findAll();
		t.commit();
		return institutes;
	}
	
	public Institute updateInstitute(Institute ins){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		InstituteDAO insDao = new InstituteDAO();
		insDao.merge(ins);
		t.commit();
		return ins;
	}
	
	public void deleteInstitute(Institute ins){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		InstituteDAO insDao = new InstituteDAO();
		insDao.delete(ins);
		t.commit();
	}
	
	public Institute insertInstitute(String insName, String insInfo){
		Institute ins = new Institute();
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		InstituteDAO insDao = new InstituteDAO();
		ins.setInstituteInfo(insInfo);
		ins.setInstituteName(insName);
		insDao.attachDirty(ins);
		t.commit();
		return ins;
	}
	
	
	public List<Specimen> getSpecimens(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	SpecimenDAO specimenDao = new SpecimenDAO();
    	List<Specimen> specimens = specimenDao.findAll();
    	for(Specimen spec : specimens){
    		spec.setImageRaw(getImageRaw(spec.getImageRaw().getImageRawId()));
    		java.lang.System.out.println(spec.getCounty()+"**"+spec.getBarcode()+spec.getCollectorNotes());
    		String path = spec.getImageRaw().getImageRawPath();
    		String replace = path.replaceAll(path.split("/")[0], "");
    		File imageFile = new File(SERVER_DIR+replace+THUMB_IMAGE_NAME_2);
    		FileDataSource dataSource = new FileDataSource(imageFile);
    		DataHandler fileDataHandler = new DataHandler(dataSource);
    		spec.setHandler(fileDataHandler);
    		spec.setFolderName(spec.getFolder().getFolderName());
    		java.lang.System.out.println(spec.getFolder().getFolderName());
    		spec.setImagePath(SERVER_DIR+replace);
    	}
    	t.commit();
    	return specimens;
	}
	
	public List<Specimen> getSpecimensByFolder(int folderId){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	SpecimenDAO specimenDao = new SpecimenDAO();
    	List<Specimen> specimens =  specimenDao.findByFolderNumber(folderId);
    	for(Specimen spec : specimens){
    		spec.setImageRaw(getImageRaw(spec.getImageRaw().getImageRawId()));
    		java.lang.System.out.println(spec.getCounty()+"**"+spec.getBarcode()+spec.getCollectorNotes());
    		String path = spec.getImageRaw().getImageRawPath();
    		String replace = path.replaceAll(path.split("/")[0], "");
    		File imageFile = new File(SERVER_DIR+replace+THUMB_IMAGE_NAME_2);
    		FileDataSource dataSource = new FileDataSource(imageFile);
    		DataHandler fileDataHandler = new DataHandler(dataSource);
    		spec.setHandler(fileDataHandler);
    		spec.setFolderName(spec.getFolder().getFolderName());
    		java.lang.System.out.println(spec.getFolder().getFolderName());
    		spec.setImagePath(SERVER_DIR+replace);
    	}
    	t.commit();
    	return specimens;
	}
	
	public int getSpecimenNumberByFolder(int folderId){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	SpecimenDAO specimenDao = new SpecimenDAO();
    	List<Specimen> specimens =  specimenDao.findByFolderNumber(folderId);
    	t.commit();
    	return specimens.size();
	}
	
	public List<Folder> getFolders(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	FolderDAO folderDao = new FolderDAO();
    	List<Folder> folders = folderDao.findAll();
    	t.commit();
    	return folders;
	}
	
	public List<Specimen> getSpecimensByWeek(int start, int end){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();//Date
		SpecimenDAO specimenDao = new SpecimenDAO();
		List<Specimen> specimens =  specimenDao.findByWeek(start,end);
		t.commit();
		return specimens;
		
	}
	
	public Specimen getSpecimenById(int specId){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	SpecimenDAO specimenDao = new SpecimenDAO();
    	Specimen spec = specimenDao.findById(specId);
    	if(spec==null) return null;
    	spec.setImageRaw(getImageRaw(spec.getImageRaw().getImageRawId()));
		File imageFile = new File(SERVER_DIR+spec.getImageRaw().getImageRawPath()+"/"+THUMB_IMAGE_NAME_2);
		FileDataSource dataSource = new FileDataSource(imageFile);
		DataHandler fileDataHandler = new DataHandler(dataSource);//CollectionCode
		spec.setHandler(fileDataHandler);
		spec.setImagePath(spec.getImageRaw().getImageRawPath());
    	return spec;
	}
	
	public List<Specimen> getSpecimens2(int pageNo, int itemNoPerPage){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	SpecimenDAO specimenDao = new SpecimenDAO();
    	List<Specimen> specimens = specimenDao.findPage(pageNo, itemNoPerPage);
    	for(Specimen spec : specimens){
    		spec.setImageRaw(getImageRaw(spec.getImageRaw().getImageRawId()));
    		File imageFile = new File(SERVER_DIR+spec.getImageRaw().getImageRawPath()+"/"+THUMB_IMAGE_NAME);
    		FileDataSource dataSource = new FileDataSource(imageFile);
    		DataHandler fileDataHandler = new DataHandler(dataSource);
    		spec.setHandler(fileDataHandler);
    	}
    	t.commit();
    	return specimens;
	}
	
	public ImageRaw getImageRaw(int imageRawId){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		ImageRawDAO dao = new ImageRawDAO();
		ImageRaw raw = dao.findById(imageRawId);
		File imageFile = new File(SERVER_DIR+raw.getImageRawPath()+"/"+FULL_IMAGE_NAME);
		FileDataSource dataSource = new FileDataSource(imageFile);
		DataHandler fileDataHandler = new DataHandler(dataSource);
		raw.setHandler(fileDataHandler);
		t.commit();
		return raw;
	}
	
	public User validateLogin(String username, String password){
		String localDir = java.lang.System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
		SERVER_DIR = localDir+"apache-tomcat-7.0.40/webapps/HerbService/img";
		java.lang.System.out.println("localDir"+localDir);
		List<Authority> authorities = new ArrayList<Authority>();
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		UserDAO dao = new UserDAO();
		UserAuthorityMapDAO mapDao = new UserAuthorityMapDAO();
		List<User> users = dao.findByUsername(username);
	    if(users.size()==0){
	    	t.commit();
	    	return null;
	    }
	    else{
	    	User user = users.get(0);
	    	if(user.getPassword().equals(password)==false){
	    		t.commit();
	    		return null;
	    	}
	    	else{
	    		java.util.Date date= new java.util.Date();
	    		Timestamp lastLoginTime = new Timestamp(date.getTime());
	    		user.setLastLoginTime(lastLoginTime);
	    		updateUser(user);
	    		t.commit();
	    		
	    		return user;
	    	}
	    }
	}
	
	public List<Authority> getAuthorities(int userId){
		List<Authority> authorities = new ArrayList<Authority>();
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		UserDAO dao = new UserDAO();
		UserAuthorityMapDAO mapDao = new UserAuthorityMapDAO();
		User user = dao.findById(userId);
		List<UserAuthorityMap> maps = mapDao.findAll();
		for(UserAuthorityMap map : maps){
			if(map.getUser().getUserId()==user.getUserId()){
				authorities.add(map.getAuthority());
			}
		}
	    t.commit();
	    return authorities;
	}
	
	public Collector insertCollector(String fullName){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		Collector c = new Collector();
		CollectorDAO cdao = new CollectorDAO();
		List<Collector> list = cdao.findByCollectorFullName(fullName);
		if(list.size()>0) return list.get(0);
		c.setCollectorFullName(fullName);
		cdao.attachDirty(c);
		t.commit();
		return c;
	}
	
	
	public Specimen insertSpecimen(
			String imgFileUrl,
			String collectorName,
			Specimen spec,
			DataHandler full,
			DataHandler full_2,
			DataHandler full_3,
			DataHandler full_4){
		//create preview image on server
		java.lang.System.out.println("imgFileUrl="+imgFileUrl);
		String [] pathArray = imgFileUrl.split("/");
		String subImgFileUrl = imgFileUrl.replaceAll(pathArray[0], "");
		String absPath = SERVER_DIR+subImgFileUrl;
		
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
    	Specimen inputSpecimen = spec;
    	// alignment to collection
    	String barcode = spec.getBarcode();
    	
    	String collectionName = spec.getCollectionCode();
    	CollectionDAO collectionDAO = new CollectionDAO();
    	List collections = collectionDAO.findByCollectionCode(collectionName);
    	if(collections.size()!=0){
    		inputSpecimen.setCollection((Collection)collections.get(0));
    	}
    	InstituteDAO instituteDao = new InstituteDAO();
    	ImageRawDAO imgRawDao = new ImageRawDAO();
    	
    	
    	int nameIndex = 0;
    	File folder = new File(SERVER_DIR+subImgFileUrl);
    	if(folder.exists()){
	    	while(folder.exists()){
	    		folder = new File(SERVER_DIR+subImgFileUrl+"_"+nameIndex);
	    		nameIndex++;
	    	}
	    	folder.mkdir();
    	}
    	else{
	    	String [] dirPathArray = (SERVER_DIR+subImgFileUrl).split("/");
	    	String paths = "";
	    	for(int i=0;i<dirPathArray.length;i++){
	    		paths+=dirPathArray[i]+"/";
	    		File tempFolder = new File(paths);
	    		
	    		if(tempFolder.exists()==false) tempFolder.mkdir();
	    	}
    	}
    	
    	try{
    		ByteArrayOutputStream output = new ByteArrayOutputStream();
        	full.writeTo(output);
    		InputStream in = new ByteArrayInputStream(output.toByteArray());
    		BufferedImage src = ImageIO.read(in);
    		
    		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
        	full_2.writeTo(output1);
    		InputStream in1 = new ByteArrayInputStream(output1.toByteArray());
    		BufferedImage src1 = ImageIO.read(in1);
    		
    		ByteArrayOutputStream output2 = new ByteArrayOutputStream();
        	full_3.writeTo(output2);
    		InputStream in2 = new ByteArrayInputStream(output2.toByteArray());
    		BufferedImage src2 = ImageIO.read(in2);
    		
    		ByteArrayOutputStream output3 = new ByteArrayOutputStream();
        	full_4.writeTo(output3);
    		InputStream in3 = new ByteArrayInputStream(output3.toByteArray());
    		BufferedImage src3 = ImageIO.read(in3);
    		java.lang.System.out.println(folder.getAbsolutePath());
    		ImageIO.write(src, "JPEG", new File(folder.getAbsolutePath()+"/"+FULL_IMAGE_NAME));
    		ImageIO.write(src1, "JPEG", new File(folder.getAbsolutePath()+"/"+DISPLAY_IMAGE_NAME));
    		ImageIO.write(src2, "JPEG", new File(folder.getAbsolutePath()+"/"+THUMB_IMAGE_NAME));
    		ImageIO.write(src3, "JPEG", new File(folder.getAbsolutePath()+"/"+THUMB_IMAGE_NAME_2));
    	    
    	}catch(Exception e){
    		e.printStackTrace();
    	}

//    	List<Folder> topFolders = folderDao.findByProperty("folderId", "1");
    	CollectorDAO collectorDao = new CollectorDAO();
    	SpecCollectorMapDAO scmDao = new SpecCollectorMapDAO();
    	FolderDAO folderDao = new FolderDAO();
    	Folder parent = folderDao.findById(currentFolder);
    	inputSpecimen.setFolder(parent);
//    	inputSpecimen.setFolder(topFolders.get(0));
    	ImageRaw imgRaw = new ImageRaw();
    	imgRaw.setImageRawPath(imgFileUrl);
    	imgRaw.setImageRawName("full.dng");
    	imgRawDao.attachDirty(imgRaw);
    	inputSpecimen.setImageRaw(imgRaw);
    	String nameCharArray [] = collectorName.split("#")[0].split(" ");
    	String recordNo = "";
    	if(collectorName.split("#").length>1){
    		recordNo = collectorName.split("#")[1];
    	}
    	List<Collector> collectors = collectorDao.findByCollectorFullName(collectorName.split("#")[0]);
    	Collector collector = null;
    	SpecCollectorMap scm = null;
    	
    	if(collectors.size()==0){
    		collector = new Collector();
    		collector.setCollectorFullName(collectorName.split("#")[0]);
    		String guid = java.util.UUID.randomUUID().toString();
    		collector.setCollectorInfo(guid);
    		collector.setGuid(guid);
    		collectorDao.attachDirty(collector);
    	}
    	else collector = collectors.get(0);
    	inputSpecimen.setRecordNumber(recordNo);
    	collectorDao.attachDirty(collector);
    	collectorDao.save(collector);
    	SpecimenDAO specimenDao = new SpecimenDAO();
    	specimenDao.attachDirty(inputSpecimen);
    	specimenDao.save(inputSpecimen);
    	
    	scm = new SpecCollectorMap();
		scm.setSpecimen(inputSpecimen);
		scm.setCollector(collector);
//    	scmDao.attachDirty(scm);
    	scmDao.save(scm);
//    	scmDao.attachDirty(scm);
    	List<SpecCollectorMap> listst = new ArrayList<SpecCollectorMap>();
    	listst.add(scm);
    	Set<SpecCollectorMap> setst = new HashSet<SpecCollectorMap>(listst);
    	inputSpecimen.setSpecCollectorMaps(setst);
//    	specimenDao.attachDirty(inputSpecimen);
    	specimenDao.save(inputSpecimen);
    	t.commit();
    	try {
	    	String host = "127.0.0.1";
	    	java.lang.System.out.println("new Specimen id = "+inputSpecimen.getSpecimenId());
		    byte[] message = (inputSpecimen.getSpecimenId()+"").getBytes();
	        InetAddress address;
			address = InetAddress.getByName(host);
	        if(ports!=null&&ports.equals("")==false) 
	        	if(ports.contains("*")==false){
	        		DatagramPacket packet = new DatagramPacket(message, message.length,
		        			address, Integer.parseInt(ports));
		        	DatagramSocket dsocket = new DatagramSocket();
		        	dsocket.send(packet);
	        	}else{
	        		java.lang.System.out.println(ports);
			        for(int i=0;i<ports.split("\\*").length;i++){
			        	DatagramPacket packet = new DatagramPacket(message, message.length,
			        			address, Integer.parseInt(ports.split("\\*")[i]));
			        	DatagramSocket dsocket = new DatagramSocket();
			        	dsocket.send(packet);
			        }
	        	}
    	} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return inputSpecimen;
	}
	
	
	public Specimen updateSpecimen(Specimen spec, String collectorIds){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		SpecimenDAO specimenDao = new SpecimenDAO();
		SpecCollectorMapDAO scmDao = new SpecCollectorMapDAO();
		FolderDAO folderDao = new FolderDAO();
		Folder folder = folderDao.findById(spec.getFolderNumber());
		spec.setFolder(folder);
//		for(String id : collectorIds.split("-")){
//			int intId = Integer.parseInt(id);
//			list.add(scmDao.findById(intId));
//		}
//		Set<SpecCollectorMap> scmSet = new HashSet<SpecCollectorMap>(list);
//		spec.setSpecCollectorMaps(scmSet);
		
//		2015-3-10
//		Specimen s = new Specimen();
//		s.setBarcode(spec.getBarcode());
//		s.setBeginEventDate(spec.getBeginEventDate());
//		s.setChecksum(spec.getChecksum());
//		s.setCollection(spec.getCollection());
//		s.setCollectionCode(spec.getCollectionCode());
//		s.setCollectorNotes(spec.getCollectorNotes());
//		s.setCountry(spec.getCountry());
//		s.setCounty(spec.getCounty());
//		s.setCreationDate(spec.getCreationDate());
//		s.setDarwinCountry(spec.getDarwinCountry());
//		s.setEndEventDate(spec.getEndEventDate());
//		s.setExportDate(spec.getExportDate());
//		s.setFamily(spec.getFamily());
//		s.setFolder(spec.getFolder());
//		s.setFolderName(spec.getFolderName());
//		s.setFolderNumber(spec.getFolderNumber());
//		s.setGeneralNotes(spec.getGeneralNotes());
//		s.setGenus(spec.getGenus());
//		s.setHandler(spec.getHandler());
//		s.setHerbarium(spec.getHerbarium());
//		s.setIdentificationQualifier(spec.getIdentificationQualifier());
//		s.setIdropSync(spec.getIdropSync());
//		s.setImageData(spec.getImageData());
//		s.setImageError(spec.getImageError());
//		s.setImagePath(spec.getImagePath());
//		s.setImageRaw(spec.getImageRaw());
//		s.setInfraspecificEpithet(spec.getInfraspecificEpithet());
//		s.setInfraspecificRank(spec.getInfraspecificRank());
//		s.setMissingInfo(spec.getMissingInfo());
//		s.setModificationDate(spec.getModificationDate());
//		s.setRecordDate(spec.getRecordDate());
//		s.setRecordedByGuid(spec.getRecordedByGuid());
//		s.setRecordEnterBy(spec.getRecordEnterBy());
//		s.setRecordNumber(spec.getRecordNumber());
//		s.setScientificName(spec.getScientificName());
//		s.setScientificNameAuthorship(spec.getScientificNameAuthorship());
//		s.setSheetNotes(spec.getSheetNotes());
//		s.setSpecificEpithet(spec.getSpecificEpithet());
//		s.setSpecimenId(spec.getSpecimenId());
//		s.setSpecimenType(spec.getSpecimenType());
//		s.setStateProvince(spec.getStateProvince());
//		s.setStorageLocation(spec.getStorageLocation());
//		s.setSystem(spec.getSystem());
//		s.setTown(spec.getTown());
//		s.setUser(spec.getUser());
//		s.setWatermark(spec.getWatermark());
//		s.setWatermarkDate(spec.getWatermarkDate());
//		s.setWrittenNumber(spec.getWrittenNumber());
		specimenDao.getSession().saveOrUpdate(spec);
		specimenDao.getSession().flush();
    	t.commit();
    	return spec;
	}
	
	public List<Collector> getCollectorsBySpecimen(Specimen spec){
		List<Collector> collectors = new ArrayList<Collector>();
		for(SpecCollectorMap map : spec.getSpecCollectorMaps()){
			collectors.add(map.getCollector());
		}
		return collectors;
	}
	
	public Set<SpecCollectorMap> getScms (int specId){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		SpecCollectorMapDAO scmDao = new SpecCollectorMapDAO();
		List<SpecCollectorMap> lls =  scmDao.findAll();
		ArrayList<SpecCollectorMap> list = new ArrayList<SpecCollectorMap>();
		for(SpecCollectorMap scm : lls){
			if(scm.getSpecimen().getSpecimenId()==specId){
				list.add(scm);
			}
		}
//		SpecimenDAO specimenDao = new SpecimenDAO();
//		Specimen ss = specimenDao.findById(specId);
//		List ll = scmDao.findByProperty("specimen", ss);
		Set<SpecCollectorMap> setst = new HashSet<SpecCollectorMap>(list);
		return setst;
	}
	
	public List<Collector> getCollectors(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO collectorDao = new CollectorDAO();
		List<Collector> collectors = collectorDao.findAll();
		t.commit();
		return collectors;
	}
	
	public List<Collector> getCollectorsByPage(int pageNo, int pageSize){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO collectorDao = new CollectorDAO();
		List<Collector> collectors = collectorDao.findByPage(pageNo, pageSize);
		t.commit();
		return collectors;
	}
	
	
	public List<Collection> getCollections(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectionDAO collectionDao = new CollectionDAO();
		List<Collection> col = collectionDao.findAll();
		t.commit();
		return col;
	}
	
	
	public List<Collector> getCollectorsByName(String name){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO collectorDao = new CollectorDAO();
		List<Collector> collectors = collectorDao.findByName(name);
		t.commit();
		return collectors;
	}
	
	public void updateCollector(Collector user){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		CollectorDAO sdao = new CollectorDAO();
		Collector c = new Collector();
		c.setBirth(user.getBirth());
		c.setCollectorFullName(user.getCollectorFullName());
		c.setCollectorId(user.getCollectorId());
		c.setCollectorInfo(user.getCollectorInfo());
		c.setCollectorNo(user.getCollectorNo());
		c.setDeath(user.getDeath());
		c.setDeleted(user.getDeleted());
		c.setGuid(user.getGuid());
		c.setImageData(user.getImageData());
		c.setInstitute(user.getInstitute());
		c.setIsGroup(user.getIsGroup());
		sdao.getSession().saveOrUpdate(c);
		sdao.getSession().flush();
		t.commit();
	}
	
	public void updateUser(User user){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		UserDAO sdao = new UserDAO();
		sdao.merge(user);
		t.commit();
	}
	
	public List<User> getUsers(){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		UserDAO udao = new UserDAO();
		List<User> users = udao.findAll();
		t.commit();
		return users;
	}
	
	public void setPort(String portNumber){
		java.lang.System.out.println(ports);
		if(ports==null||ports.equals("")) ports += portNumber; 
		else ports+="*"+portNumber;
	}
	
	
	public User getUserByName(String username){
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		UserDAO dao = new UserDAO();
		List<User> users = dao.findByUsername(username);
		t.commit();
		if(users.size()==0) return null;
		else return users.get(0);
	}
	
	public User insertUser(String username, 
						String password, 
						String email,
						String iplantName,
						String iplantPassword,
						String iplantAddress,
						String iplantZone){
		
		Transaction t = HibernateSessionFactory.getSession().beginTransaction();
		User u = new User();
		UserDAO udao = new UserDAO();
		u.setEmail(email);
		u.setIplantPassword(iplantPassword);
		u.setIplantUserName(iplantName);
		u.setIplantAddress(iplantAddress);
		u.setIplantZone(iplantZone);
		u.setPassword(password);
		u.setUsername(username);
		u.setUuid(UUID.randomUUID().toString());
		udao.attachDirty(u);
		t.commit();
		return u;
	}
	
	
public Collector insertCollector2(Collector collector){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	CollectorDAO cdao = new CollectorDAO();
	cdao.attachDirty(collector);
	t.commit();
	return collector;
}

public Folder getParent(Integer specimenId){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SpecimenDAO specimenDao = new SpecimenDAO();
	Specimen spec = specimenDao.findById(specimenId);
	Folder f = spec.getFolder();
	t.commit();
	return f;
}

public System insertSystem(String systemInfo, int instituteId){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	InstituteDAO cdao = new InstituteDAO();
	SystemDAO sdao = new SystemDAO();
	Institute ins = cdao.findById(instituteId);
	System sys = new System();
	sys.setInstitute(ins);
	sys.setSystemDescription(systemInfo);
	sdao.attachDirty(sys);
	t.commit();
	return sys;
}

public System updateSystem(System sys,int insId){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemDAO sdao = new SystemDAO();
	InstituteDAO idao = new InstituteDAO();
	Institute ins = idao.findById(insId);
	sys.setInstitute(ins);
	sdao.merge(sys);
	t.commit();
	return sys;
}

public System deleteSystem(System sys, Integer instituteId){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemDAO sdao = new SystemDAO();
	SystemSettingDAO stdao = new SystemSettingDAO();
	InstituteDAO insdao = new InstituteDAO();
	Institute institute = insdao.findById(instituteId);
	for(SystemSetting set : sys.getSystemSettings()){
		set.setSystem(sys);
		HibernateSessionFactory.getSession().clear();
		stdao.delete(set);
	}
	
	sys.setInstitute(institute);
	HibernateSessionFactory.getSession().clear();
	sdao.delete(sys);
	t.commit();
	return sys;
}

public List<System> getSystems(Integer insId){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemDAO cdao = new SystemDAO();
	List<System> allset = cdao.findAll();
	List<System> subset = new ArrayList<System>();
	for(System ss : allset){
		String id1 = ss.getInstitute().getInstituteId()+"";
		String id2 = insId+"";
		if(id1.equals(id2)){
			subset.add(ss);
		}
	}
	t.commit();
	return subset;
}

public List<SystemSetting> getSettings(Integer sysId){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemDAO cdao = new SystemDAO();
	SystemSettingDAO stdao = new SystemSettingDAO();
	List<SystemSetting> allset = stdao.findAll();
	List<SystemSetting> subset = new ArrayList<SystemSetting>();
	for(SystemSetting ss : allset){
		String id1 = ss.getSystem().getSystemId()+"";
		String id2 = sysId+"";
		if(id1.equals(id2)){
			subset.add(ss);
		}
	}
	t.commit();
	return subset;
}

public SystemSetting insertSystemSetting(Integer sysId,//TimestampAdapter
		String cameraName,
		String cameraExpoTime,
		Integer cameraIso,
		Integer cameraAperture,
		Integer colorTargetPositionX,
		Integer colorTargetPositionY,
		Integer conveyorVoltage,
		Integer sensorVoltage,
		String workspacePath){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemDAO cdao = new SystemDAO();
	System sys = cdao.findById(sysId);
	SystemSetting st = new SystemSetting();
	java.util.Date date= new java.util.Date();
	st.setSystem(sys);
	st.setSettingDate(new Timestamp(date.getTime()));
	st.setCameraName(cameraName);
	st.setCameraExpoTime(cameraExpoTime);
	st.setCameraIso(cameraIso);
	st.setCameraAperture(cameraAperture);
	st.setColorTargetPositionX(colorTargetPositionX);
	st.setColorTargetPositionY(colorTargetPositionY);
	st.setConveyorVoltage(conveyorVoltage);
	st.setWorkspacePath(workspacePath);
	SystemSettingDAO stdao = new SystemSettingDAO();
	stdao.attachDirty(st);
	t.commit();
	return st;
}

public SystemSetting updateSystemSetting(SystemSetting sysset, System sys){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemSettingDAO sdao = new SystemSettingDAO();
	sysset.setSystem(sys);
	sdao.merge(sysset);
	t.commit();
	return sysset;
}

public SystemSetting deleteSystemSetting(SystemSetting sys){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	SystemSettingDAO sdao = new SystemSettingDAO();
	sdao.delete(sdao.findById(sys.getSettingId()));
	t.commit();
	return sys;
}


public Folder insertFolder(String folderName, String folderPath, String folderInfo){
	Transaction t = HibernateSessionFactory.getSession().beginTransaction();
	Folder f = new Folder();
	FolderDAO fdao = new FolderDAO();
	f.setFolderName(folderName);
	f.setFolderPath(folderPath);
	f.setFolderInfo(folderInfo);
	fdao.attachDirty(f);
	
	t.commit();
	return f;
}

}
