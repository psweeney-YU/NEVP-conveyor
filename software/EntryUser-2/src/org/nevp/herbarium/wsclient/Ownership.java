package org.nevp.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ownership complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ownership">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ownershipId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rights" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ownership", propOrder = { "ownershipId", "rights", "usage" })
public class Ownership {

	protected Integer ownershipId;
	protected String rights;
	protected String usage;

	/**
	 * Gets the value of the ownershipId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getOwnershipId() {
		return ownershipId;
	}

	/**
	 * Sets the value of the ownershipId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setOwnershipId(Integer value) {
		this.ownershipId = value;
	}

	/**
	 * Gets the value of the rights property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * Sets the value of the rights property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRights(String value) {
		this.rights = value;
	}

	/**
	 * Gets the value of the usage property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * Sets the value of the usage property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsage(String value) {
		this.usage = value;
	}

}
