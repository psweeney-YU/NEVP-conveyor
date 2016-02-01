package edu.ou.herbarium.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	private String uuid;
	private String worksplaceUrl;
	private String email;
	private String iplantUserName;
	private String iplantPassword;
	private String iplantAddress;
	private String iplantZone;
	private Integer deleted;
	private Timestamp createTime;
	private Timestamp lastLoginTime;
	private Set<Specimen> specimens = new HashSet<Specimen>(0);
	private Set<UserAuthorityMap> userAuthorityMaps = new HashSet<UserAuthorityMap>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public User(String username, String password, String uuid,
			String worksplaceUrl, String email, String iplantUserName,
			String iplantPassword, String iplantAddress, String iplantZone,
			Integer deleted, Timestamp createTime, Timestamp lastLoginTime,
			Set<Specimen> specimens, Set<UserAuthorityMap> userAuthorityMaps) {
		this.username = username;
		this.password = password;
		this.uuid = uuid;
		this.worksplaceUrl = worksplaceUrl;
		this.email = email;
		this.iplantUserName = iplantUserName;
		this.iplantPassword = iplantPassword;
		this.iplantAddress = iplantAddress;
		this.iplantZone = iplantZone;
		this.deleted = deleted;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.specimens = specimens;
		this.userAuthorityMaps = userAuthorityMaps;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWorksplaceUrl() {
		return this.worksplaceUrl;
	}

	public void setWorksplaceUrl(String worksplaceUrl) {
		this.worksplaceUrl = worksplaceUrl;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIplantUserName() {
		return this.iplantUserName;
	}

	public void setIplantUserName(String iplantUserName) {
		this.iplantUserName = iplantUserName;
	}

	public String getIplantPassword() {
		return this.iplantPassword;
	}

	public void setIplantPassword(String iplantPassword) {
		this.iplantPassword = iplantPassword;
	}

	public String getIplantAddress() {
		return this.iplantAddress;
	}

	public void setIplantAddress(String iplantAddress) {
		this.iplantAddress = iplantAddress;
	}

	public String getIplantZone() {
		return this.iplantZone;
	}

	public void setIplantZone(String iplantZone) {
		this.iplantZone = iplantZone;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}
	@XmlTransient
	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

	public Set<UserAuthorityMap> getUserAuthorityMaps() {
		return this.userAuthorityMaps;
	}

	public void setUserAuthorityMaps(Set<UserAuthorityMap> userAuthorityMaps) {
		this.userAuthorityMaps = userAuthorityMaps;
	}

}