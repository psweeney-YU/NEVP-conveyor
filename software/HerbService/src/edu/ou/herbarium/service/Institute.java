package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Institute entity. @author MyEclipse Persistence Tools
 */

public class Institute implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer instituteId;
	private String instituteName;
	private String instituteInfo;
	private Integer deleted;
	private Set<Ownership> ownerships = new HashSet<Ownership>(0);
	private Set<System> systems = new HashSet<System>(0);
	private Set<Collection> collections = new HashSet<Collection>(0);

	// Constructors

	/** default constructor */
	public Institute() {
	}

	/** minimal constructor */
	public Institute(String instituteName) {
		this.instituteName = instituteName;
	}

	/** full constructor */
	public Institute(String instituteName, String instituteInfo,
			Integer deleted, Set<Ownership> ownerships, Set<System> systems, Set<Collection> collections) {
		this.instituteName = instituteName;
		this.instituteInfo = instituteInfo;
		this.deleted = deleted;
		this.ownerships = ownerships;
		this.systems = systems;
		this.collections = collections;
	}

	// Property accessors

	public Integer getInstituteId() {
		return this.instituteId;
	}

	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getInstituteInfo() {
		return this.instituteInfo;
	}

	public void setInstituteInfo(String instituteInfo) {
		this.instituteInfo = instituteInfo;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Set<Ownership> getOwnerships() {
		return this.ownerships;
	}

	public void setOwnerships(Set<Ownership> ownerships) {
		this.ownerships = ownerships;
	}

	public Set<System> getSystems() {
		return this.systems;
	}

	public void setSystems(Set<System> systems) {
		this.systems = systems;
	}
	@XmlTransient
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

}