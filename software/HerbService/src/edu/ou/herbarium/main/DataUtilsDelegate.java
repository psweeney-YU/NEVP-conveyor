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

@javax.jws.WebService(targetNamespace = "http://main.herbarium.ou.edu/", serviceName = "DataUtilsService", portName = "DataUtilsPort")
public class DataUtilsDelegate {

	edu.ou.herbarium.main.DataUtils dataUtils = new edu.ou.herbarium.main.DataUtils();

	public Folder getTopFolders() {
		return dataUtils.getTopFolders();
	}

	public List<Institute> getInstitutes() {
		return dataUtils.getInstitutes();
	}

	public Institute updateInstitute(Institute ins) {
		return dataUtils.updateInstitute(ins);
	}

	public void deleteInstitute(Institute ins) {
		dataUtils.deleteInstitute(ins);
	}

	public Institute insertInstitute(String insName, String insInfo) {
		return dataUtils.insertInstitute(insName, insInfo);
	}

	public List<Specimen> getSpecimens() {
		return dataUtils.getSpecimens();
	}

	public List<Specimen> getSpecimensByFolder(int folderId) {
		return dataUtils.getSpecimensByFolder(folderId);
	}

	public int getSpecimenNumberByFolder(int folderId) {
		return dataUtils.getSpecimenNumberByFolder(folderId);
	}

	public List<Folder> getFolders() {
		return dataUtils.getFolders();
	}

	public List<Specimen> getSpecimensByWeek(int start, int end) {
		return dataUtils.getSpecimensByWeek(start, end);
	}

	public Specimen getSpecimenById(int specId) {
		return dataUtils.getSpecimenById(specId);
	}

	public List<Specimen> getSpecimens2(int pageNo, int itemNoPerPage) {
		return dataUtils.getSpecimens2(pageNo, itemNoPerPage);
	}

	public ImageRaw getImageRaw(int imageRawId) {
		return dataUtils.getImageRaw(imageRawId);
	}

	public User validateLogin(String username, String password) {
		return dataUtils.validateLogin(username, password);
	}

	public List<Authority> getAuthorities(int userId) {
		return dataUtils.getAuthorities(userId);
	}

	public Collector insertCollector(String fullName) {
		return dataUtils.insertCollector(fullName);
	}

	public Specimen insertSpecimen(String imgFileUrl, String collectorName,
			Specimen spec, DataHandler full, DataHandler full_2,
			DataHandler full_3, DataHandler full_4) {
		return dataUtils.insertSpecimen(imgFileUrl, collectorName, spec, full,
				full_2, full_3, full_4);
	}

	public Specimen updateSpecimen(Specimen spec, String collectorIds) {
		return dataUtils.updateSpecimen(spec, collectorIds);
	}

	public List<Collector> getCollectorsBySpecimen(Specimen spec) {
		return dataUtils.getCollectorsBySpecimen(spec);
	}

	public Set<SpecCollectorMap> getScms(int specId) {
		return dataUtils.getScms(specId);
	}

	public List<Collector> getCollectors() {
		return dataUtils.getCollectors();
	}

	public List<Collector> getCollectorsByPage(int pageNo, int pageSize) {
		return dataUtils.getCollectorsByPage(pageNo, pageSize);
	}

	public List<Collection> getCollections() {
		return dataUtils.getCollections();
	}

	public List<Collector> getCollectorsByName(String name) {
		return dataUtils.getCollectorsByName(name);
	}

	public void updateCollector(Collector user) {
		dataUtils.updateCollector(user);
	}

	public void updateUser(User user) {
		dataUtils.updateUser(user);
	}

	public List<User> getUsers() {
		return dataUtils.getUsers();
	}

	public void setPort(String portNumber) {
		dataUtils.setPort(portNumber);
	}

	public User getUserByName(String username) {
		return dataUtils.getUserByName(username);
	}

	public User insertUser(String username, String password, String email,
			String iplantName, String iplantPassword, String iplantAddress,
			String iplantZone) {
		return dataUtils.insertUser(username, password, email, iplantName,
				iplantPassword, iplantAddress, iplantZone);
	}

	public Collector insertCollector2(Collector collector) {
		return dataUtils.insertCollector2(collector);
	}

	public Folder getParent(Integer specimenId) {
		return dataUtils.getParent(specimenId);
	}

	public System insertSystem(String systemInfo, int instituteId) {
		return dataUtils.insertSystem(systemInfo, instituteId);
	}

	public System updateSystem(System sys, int insId) {
		return dataUtils.updateSystem(sys, insId);
	}

	public System deleteSystem(System sys, Integer instituteId) {
		return dataUtils.deleteSystem(sys, instituteId);
	}

	public List<System> getSystems(Integer insId) {
		return dataUtils.getSystems(insId);
	}

	public List<SystemSetting> getSettings(Integer sysId) {
		return dataUtils.getSettings(sysId);
	}

	public SystemSetting insertSystemSetting(Integer sysId, String cameraName,
			String cameraExpoTime, Integer cameraIso, Integer cameraAperture,
			Integer colorTargetPositionX, Integer colorTargetPositionY,
			Integer conveyorVoltage, Integer sensorVoltage, String workspacePath) {
		return dataUtils.insertSystemSetting(sysId, cameraName, cameraExpoTime,
				cameraIso, cameraAperture, colorTargetPositionX,
				colorTargetPositionY, conveyorVoltage, sensorVoltage,
				workspacePath);
	}

	public SystemSetting updateSystemSetting(SystemSetting sysset, System sys) {
		return dataUtils.updateSystemSetting(sysset, sys);
	}

	public SystemSetting deleteSystemSetting(SystemSetting sys) {
		return dataUtils.deleteSystemSetting(sys);
	}

	public Folder insertFolder(String folderName, String folderPath,
			String folderInfo) {
		return dataUtils.insertFolder(folderName, folderPath, folderInfo);
	}

}