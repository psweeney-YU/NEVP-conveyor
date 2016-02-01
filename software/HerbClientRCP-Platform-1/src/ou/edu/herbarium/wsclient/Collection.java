package ou.edu.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for collection complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collectionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectionId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="collectionInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="institute" type="{http://main.herbarium.ou.edu/}institute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collection", propOrder = { "collectionCode", "collectionId",
		"collectionInfo", "institute" })
public class Collection {

	protected String collectionCode;
	protected Integer collectionId;
	protected String collectionInfo;
	protected Institute institute;

	/**
	 * Gets the value of the collectionCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectionCode() {
		return collectionCode;
	}

	/**
	 * Sets the value of the collectionCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCollectionCode(String value) {
		this.collectionCode = value;
	}

	/**
	 * Gets the value of the collectionId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCollectionId() {
		return collectionId;
	}

	/**
	 * Sets the value of the collectionId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCollectionId(Integer value) {
		this.collectionId = value;
	}

	/**
	 * Gets the value of the collectionInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectionInfo() {
		return collectionInfo;
	}

	/**
	 * Sets the value of the collectionInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCollectionInfo(String value) {
		this.collectionInfo = value;
	}

	/**
	 * Gets the value of the institute property.
	 * 
	 * @return possible object is {@link Institute }
	 * 
	 */
	public Institute getInstitute() {
		return institute;
	}

	/**
	 * Sets the value of the institute property.
	 * 
	 * @param value
	 *            allowed object is {@link Institute }
	 * 
	 */
	public void setInstitute(Institute value) {
		this.institute = value;
	}

}
