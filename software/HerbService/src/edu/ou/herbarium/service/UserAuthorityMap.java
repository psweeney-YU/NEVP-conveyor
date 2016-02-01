package edu.ou.herbarium.service;

import javax.xml.bind.annotation.XmlTransient;

/**
 * UserAuthorityMap entity. @author MyEclipse Persistence Tools
 */

public class UserAuthorityMap implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userAuthorityMapId;
	private Authority authority;
	private User user;

	// Constructors

	/** default constructor */
	public UserAuthorityMap() {
	}

	/** full constructor */
	public UserAuthorityMap(Authority authority, User user) {
		this.authority = authority;
		this.user = user;
	}

	// Property accessors

	public Integer getUserAuthorityMapId() {
		return this.userAuthorityMapId;
	}

	public void setUserAuthorityMapId(Integer userAuthorityMapId) {
		this.userAuthorityMapId = userAuthorityMapId;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	@XmlTransient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}