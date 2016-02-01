package org.nevp.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for authority complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="authority">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorityId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="authorityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authorityTabClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authority", propOrder = { "authorityId", "authorityName",
		"authorityTabClass" })
public class Authority {

	protected Integer authorityId;
	protected String authorityName;
	protected String authorityTabClass;

	/**
	 * Gets the value of the authorityId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAuthorityId() {
		return authorityId;
	}

	/**
	 * Sets the value of the authorityId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAuthorityId(Integer value) {
		this.authorityId = value;
	}

	/**
	 * Gets the value of the authorityName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuthorityName() {
		return authorityName;
	}

	/**
	 * Sets the value of the authorityName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuthorityName(String value) {
		this.authorityName = value;
	}

	/**
	 * Gets the value of the authorityTabClass property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuthorityTabClass() {
		return authorityTabClass;
	}

	/**
	 * Sets the value of the authorityTabClass property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuthorityTabClass(String value) {
		this.authorityTabClass = value;
	}

}
