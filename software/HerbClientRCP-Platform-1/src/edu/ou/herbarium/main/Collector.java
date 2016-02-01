package edu.ou.herbarium.main;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for collector complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="collector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="birth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectorFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="collectorInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectorNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="death" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageData" type="{http://main.herbarium.ou.edu/}imageData" minOccurs="0"/>
 *         &lt;element name="institute" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isGroup" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collector", propOrder = { "birth", "collectorFullName",
		"collectorId", "collectorInfo", "collectorNo", "death", "deleted",
		"guid", "imageData", "institute", "isGroup" })
public class Collector {

	protected String birth;
	protected String collectorFullName;
	protected Integer collectorId;
	protected String collectorInfo;
	protected String collectorNo;
	protected String death;
	protected Integer deleted;
	protected String guid;
	protected ImageData imageData;
	protected Integer institute;
	protected Integer isGroup;

	/**
	 * Gets the value of the birth property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * Sets the value of the birth property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBirth(String value) {
		this.birth = value;
	}

	/**
	 * Gets the value of the collectorFullName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectorFullName() {
		return collectorFullName;
	}

	/**
	 * Sets the value of the collectorFullName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCollectorFullName(String value) {
		this.collectorFullName = value;
	}

	/**
	 * Gets the value of the collectorId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCollectorId() {
		return collectorId;
	}

	/**
	 * Sets the value of the collectorId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCollectorId(Integer value) {
		this.collectorId = value;
	}

	/**
	 * Gets the value of the collectorInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectorInfo() {
		return collectorInfo;
	}

	/**
	 * Sets the value of the collectorInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCollectorInfo(String value) {
		this.collectorInfo = value;
	}

	/**
	 * Gets the value of the collectorNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectorNo() {
		return collectorNo;
	}

	/**
	 * Sets the value of the collectorNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCollectorNo(String value) {
		this.collectorNo = value;
	}

	/**
	 * Gets the value of the death property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeath() {
		return death;
	}

	/**
	 * Sets the value of the death property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeath(String value) {
		this.death = value;
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
	 * Gets the value of the guid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the value of the guid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGuid(String value) {
		this.guid = value;
	}

	/**
	 * Gets the value of the imageData property.
	 * 
	 * @return possible object is {@link ImageData }
	 * 
	 */
	public ImageData getImageData() {
		return imageData;
	}

	/**
	 * Sets the value of the imageData property.
	 * 
	 * @param value
	 *            allowed object is {@link ImageData }
	 * 
	 */
	public void setImageData(ImageData value) {
		this.imageData = value;
	}

	/**
	 * Gets the value of the institute property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getInstitute() {
		return institute;
	}

	/**
	 * Sets the value of the institute property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setInstitute(Integer value) {
		this.institute = value;
	}

	/**
	 * Gets the value of the isGroup property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsGroup() {
		return isGroup;
	}

	/**
	 * Sets the value of the isGroup property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsGroup(Integer value) {
		this.isGroup = value;
	}

}
