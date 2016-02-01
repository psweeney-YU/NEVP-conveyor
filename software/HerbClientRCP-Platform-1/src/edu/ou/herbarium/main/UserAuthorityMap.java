package edu.ou.herbarium.main;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for userAuthorityMap complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="userAuthorityMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authority" type="{http://main.herbarium.ou.edu/}authority" minOccurs="0"/>
 *         &lt;element name="userAuthorityMapId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userAuthorityMap", propOrder = { "authority",
		"userAuthorityMapId" })
public class UserAuthorityMap {

	protected Authority authority;
	protected Integer userAuthorityMapId;

	/**
	 * Gets the value of the authority property.
	 * 
	 * @return possible object is {@link Authority }
	 * 
	 */
	public Authority getAuthority() {
		return authority;
	}

	/**
	 * Sets the value of the authority property.
	 * 
	 * @param value
	 *            allowed object is {@link Authority }
	 * 
	 */
	public void setAuthority(Authority value) {
		this.authority = value;
	}

	/**
	 * Gets the value of the userAuthorityMapId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getUserAuthorityMapId() {
		return userAuthorityMapId;
	}

	/**
	 * Sets the value of the userAuthorityMapId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setUserAuthorityMapId(Integer value) {
		this.userAuthorityMapId = value;
	}

}
