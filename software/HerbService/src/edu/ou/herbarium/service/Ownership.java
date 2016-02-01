package edu.ou.herbarium.service;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Ownership entity. @author MyEclipse Persistence Tools
 */

public class Ownership implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ownershipId;
	private Institute institute;
	private String rights;
	private String usage;

	// Constructors

	/** default constructor */
	public Ownership() {
	}

	/** minimal constructor */
	public Ownership(Institute institute) {
		this.institute = institute;
	}

	/** full constructor */
	public Ownership(Institute institute, String rights, String usage) {
		this.institute = institute;
		this.rights = rights;
		this.usage = usage;
	}

	// Property accessors

	public Integer getOwnershipId() {
		return this.ownershipId;
	}

	public void setOwnershipId(Integer ownershipId) {
		this.ownershipId = ownershipId;
	}

	@XmlTransient
	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public String getRights() {
		return this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

}