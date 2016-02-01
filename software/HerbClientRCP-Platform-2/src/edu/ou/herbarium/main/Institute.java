package edu.ou.herbarium.main;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for institute complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="institute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="instituteId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="instituteInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instituteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerships" type="{http://main.herbarium.ou.edu/}ownership" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="systems" type="{http://main.herbarium.ou.edu/}system" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "institute", propOrder = { "deleted", "instituteId",
		"instituteInfo", "instituteName", "ownerships", "systems" })
public class Institute {

	protected Integer deleted;
	protected Integer instituteId;
	protected String instituteInfo;
	protected String instituteName;
	@XmlElement(nillable = true)
	protected List<Ownership> ownerships;
	@XmlElement(nillable = true)
	protected List<System> systems;

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
	 * Gets the value of the instituteId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getInstituteId() {
		return instituteId;
	}

	/**
	 * Sets the value of the instituteId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setInstituteId(Integer value) {
		this.instituteId = value;
	}

	/**
	 * Gets the value of the instituteInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInstituteInfo() {
		return instituteInfo;
	}

	/**
	 * Sets the value of the instituteInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInstituteInfo(String value) {
		this.instituteInfo = value;
	}

	/**
	 * Gets the value of the instituteName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInstituteName() {
		return instituteName;
	}

	/**
	 * Sets the value of the instituteName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInstituteName(String value) {
		this.instituteName = value;
	}

	/**
	 * Gets the value of the ownerships property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the ownerships property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getOwnerships().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Ownership }
	 * 
	 * 
	 */
	public List<Ownership> getOwnerships() {
		if (ownerships == null) {
			ownerships = new ArrayList<Ownership>();
		}
		return this.ownerships;
	}

	/**
	 * Gets the value of the systems property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the systems property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSystems().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link System }
	 * 
	 * 
	 */
	public List<System> getSystems() {
		if (systems == null) {
			systems = new ArrayList<System>();
		}
		return this.systems;
	}

}
