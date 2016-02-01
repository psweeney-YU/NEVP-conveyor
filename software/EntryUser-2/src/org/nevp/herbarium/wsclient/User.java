package org.nevp.herbarium.wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for user complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="user">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iplantAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iplantPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iplantUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iplantZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastLoginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAuthorityMaps" type="{http://main.herbarium.ou.edu/}userAuthorityMap" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="worksplaceUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = { "createTime", "deleted", "email",
		"iplantAddress", "iplantPassword", "iplantUserName", "iplantZone",
		"lastLoginTime", "password", "userAuthorityMaps", "userId", "username",
		"uuid", "worksplaceUrl" })
public class User {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	protected Integer deleted;
	protected String email;
	protected String iplantAddress;
	protected String iplantPassword;
	protected String iplantUserName;
	protected String iplantZone;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar lastLoginTime;
	protected String password;
	@XmlElement(nillable = true)
	protected List<UserAuthorityMap> userAuthorityMaps;
	protected Integer userId;
	protected String username;
	protected String uuid;
	protected String worksplaceUrl;

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateTime(XMLGregorianCalendar value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the deleted property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * Sets the value of the deleted property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDeleted(Integer value) {
		this.deleted = value;
	}

	/**
	 * Gets the value of the email property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the iplantAddress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIplantAddress() {
		return iplantAddress;
	}

	/**
	 * Sets the value of the iplantAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIplantAddress(String value) {
		this.iplantAddress = value;
	}

	/**
	 * Gets the value of the iplantPassword property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIplantPassword() {
		return iplantPassword;
	}

	/**
	 * Sets the value of the iplantPassword property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIplantPassword(String value) {
		this.iplantPassword = value;
	}

	/**
	 * Gets the value of the iplantUserName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIplantUserName() {
		return iplantUserName;
	}

	/**
	 * Sets the value of the iplantUserName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIplantUserName(String value) {
		this.iplantUserName = value;
	}

	/**
	 * Gets the value of the iplantZone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIplantZone() {
		return iplantZone;
	}

	/**
	 * Sets the value of the iplantZone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIplantZone(String value) {
		this.iplantZone = value;
	}

	/**
	 * Gets the value of the lastLoginTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * Sets the value of the lastLoginTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setLastLoginTime(XMLGregorianCalendar value) {
		this.lastLoginTime = value;
	}

	/**
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * Gets the value of the userAuthorityMaps property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the userAuthorityMaps property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getUserAuthorityMaps().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link UserAuthorityMap }
	 * 
	 * 
	 */
	public List<UserAuthorityMap> getUserAuthorityMaps() {
		if (userAuthorityMaps == null) {
			userAuthorityMaps = new ArrayList<UserAuthorityMap>();
		}
		return this.userAuthorityMaps;
	}

	/**
	 * Gets the value of the userId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the value of the userId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setUserId(Integer value) {
		this.userId = value;
	}

	/**
	 * Gets the value of the username property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the value of the username property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsername(String value) {
		this.username = value;
	}

	/**
	 * Gets the value of the uuid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the value of the uuid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUuid(String value) {
		this.uuid = value;
	}

	/**
	 * Gets the value of the worksplaceUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorksplaceUrl() {
		return worksplaceUrl;
	}

	/**
	 * Sets the value of the worksplaceUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorksplaceUrl(String value) {
		this.worksplaceUrl = value;
	}

}
