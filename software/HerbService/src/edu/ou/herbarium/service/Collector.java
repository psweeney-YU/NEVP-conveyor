package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Collector entity. @author MyEclipse Persistence Tools
 */

public class Collector implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer collectorId;
	private ImageData imageData;
	private String collectorNo;
	private Integer institute;
	private String guid;
	private String collectorFullName;
	private String collectorInfo;
	private Integer deleted;
	private String birth;
	private String death;
	private Integer isGroup;
	private Set<SpecCollectorMap> specCollectorMaps = new HashSet<SpecCollectorMap>(0);

	// Constructors

	/** default constructor */
	public Collector() {
	}

	/** full constructor */
	public Collector(ImageData imageData, 
			String collectorNo, Integer institute, String guid,
			String collectorFullName, Set<SpecCollectorMap> specCollectorMaps, String collectorInfo,
			String birth, String death, Integer isGroup, Integer deleted) {
		this.imageData = imageData;
		this.collectorNo = collectorNo;
		this.institute = institute;
		this.guid = guid;
		this.collectorFullName = collectorFullName;
		this.collectorInfo = collectorInfo;
		this.specCollectorMaps = specCollectorMaps;
		this.birth = birth;
		this.death = death;
		this.isGroup = isGroup;
		this.deleted = deleted;
	}

	// Property accessors

	public Integer getCollectorId() {
		return this.collectorId;
	}

	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}

	public ImageData getImageData() {
		return this.imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public String getCollectorNo() {
		return this.collectorNo;
	}

	public void setCollectorNo(String collectorNo) {
		this.collectorNo = collectorNo;
	}

	public Integer getInstitute() {
		return this.institute;
	}

	public void setInstitute(Integer institute) {
		this.institute = institute;
	}

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCollectorFullName() {
		return this.collectorFullName;
	}

	public void setCollectorFullName(String collectorFullName) {
		this.collectorFullName = collectorFullName;
	}

	public String getCollectorInfo() {
		return this.collectorInfo;
	}

	public void setCollectorInfo(String collectorInfo) {
		this.collectorInfo = collectorInfo;
	}

	@XmlTransient
	public Set<SpecCollectorMap> getSpecCollectorMaps() {
		return this.specCollectorMaps;
	}

	public void setSpecCollectorMaps(Set<SpecCollectorMap> specCollectorMaps) {
		this.specCollectorMaps = specCollectorMaps;
	}
	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}

	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}



}