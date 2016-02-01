package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */

public class Collection implements java.io.Serializable {

	// Fields

	private Integer collectionId;
	private Institute institute;
	private String collectionCode;
	private String collectionInfo;
	private Set<Specimen> specimens = new HashSet<Specimen>(0);

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** full constructor */
	public Collection(Institute institute, String collectionCode,
			String collectionInfo, Set specimens) {
		this.institute = institute;
		this.collectionCode = collectionCode;
		this.collectionInfo = collectionInfo;
		this.specimens = specimens;
	}

	// Property accessors

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public String getCollectionCode() {
		return this.collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public String getCollectionInfo() {
		return this.collectionInfo;
	}

	public void setCollectionInfo(String collectionInfo) {
		this.collectionInfo = collectionInfo;
	}
	
	@XmlTransient
	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}

	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

}