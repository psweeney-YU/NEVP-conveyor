package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * System entity. @author MyEclipse Persistence Tools
 */

public class System implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer systemId;
	private Institute institute;
	private String systemDescription;
	private Integer deleted;
	private Set<Specimen> specimens = new HashSet<Specimen>(0);
	private Set<SystemSetting> systemSettings = new HashSet<SystemSetting>(0);

	// Constructors

	/** default constructor */
	public System() {
	}

	/** minimal constructor */
	public System(Institute institute) {
		this.institute = institute;
	}

	/** full constructor */
	public System(Institute institute, String systemDescription, Integer deleted, Set<Specimen> specimens,
			Set<SystemSetting> systemSettings) {
		this.institute = institute;
		this.systemDescription = systemDescription;
		this.setDeleted(deleted);
		this.specimens = specimens;
		this.systemSettings = systemSettings;
	}

	// Property accessors

	public Integer getSystemId() {
		return this.systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	@XmlTransient
	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public String getSystemDescription() {
		return this.systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}
	@XmlTransient
	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}

	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

	public Set<SystemSetting> getSystemSettings() {
		return this.systemSettings;
	}

	public void setSystemSettings(Set<SystemSetting> systemSettings) {
		this.systemSettings = systemSettings;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}