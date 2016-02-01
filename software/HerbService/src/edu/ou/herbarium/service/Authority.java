package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Authority entity. @author MyEclipse Persistence Tools
 */

public class Authority implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer authorityId;
	private String authorityName;
	private String authorityTabClass;
	private Set<UserAuthorityMap> userAuthorityMaps = new HashSet<UserAuthorityMap>(0);

	// Constructors

	/** default constructor */
	public Authority() {
	}

	/** minimal constructor */
	public Authority(String authorityName, String authorityTabClass) {
		this.authorityName = authorityName;
		this.authorityTabClass = authorityTabClass;
	}

	/** full constructor */
	public Authority(String authorityName, String authorityTabClass,
			Set<UserAuthorityMap> userAuthorityMaps) {
		this.authorityName = authorityName;
		this.authorityTabClass = authorityTabClass;
		this.userAuthorityMaps = userAuthorityMaps;
	}

	// Property accessors

	public Integer getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityTabClass() {
		return this.authorityTabClass;
	}

	public void setAuthorityTabClass(String authorityTabClass) {
		this.authorityTabClass = authorityTabClass;
	}
	@XmlTransient
	public Set<UserAuthorityMap> getUserAuthorityMaps() {
		return this.userAuthorityMaps;
	}

	public void setUserAuthorityMaps(Set<UserAuthorityMap> userAuthorityMaps) {
		this.userAuthorityMaps = userAuthorityMaps;
	}

}