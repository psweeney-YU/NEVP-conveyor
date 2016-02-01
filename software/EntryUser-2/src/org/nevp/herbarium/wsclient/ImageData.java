package org.nevp.herbarium.wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for imageData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="imageData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collectors" type="{http://main.herbarium.ou.edu/}collector" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="imageDataId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="imageDataPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageDataType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specimens" type="{http://main.herbarium.ou.edu/}specimen" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imageData", propOrder = { "collectors", "imageDataId",
		"imageDataPath", "imageDataType", "specimens" })
public class ImageData {

	@XmlElement(nillable = true)
	protected List<Collector> collectors;
	protected Integer imageDataId;
	protected String imageDataPath;
	protected Integer imageDataType;
	@XmlElement(nillable = true)
	protected List<Specimen> specimens;

	/**
	 * Gets the value of the collectors property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the collectors property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCollectors().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Collector }
	 * 
	 * 
	 */
	public List<Collector> getCollectors() {
		if (collectors == null) {
			collectors = new ArrayList<Collector>();
		}
		return this.collectors;
	}

	/**
	 * Gets the value of the imageDataId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getImageDataId() {
		return imageDataId;
	}

	/**
	 * Sets the value of the imageDataId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setImageDataId(Integer value) {
		this.imageDataId = value;
	}

	/**
	 * Gets the value of the imageDataPath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImageDataPath() {
		return imageDataPath;
	}

	/**
	 * Sets the value of the imageDataPath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImageDataPath(String value) {
		this.imageDataPath = value;
	}

	/**
	 * Gets the value of the imageDataType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getImageDataType() {
		return imageDataType;
	}

	/**
	 * Sets the value of the imageDataType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setImageDataType(Integer value) {
		this.imageDataType = value;
	}

	/**
	 * Gets the value of the specimens property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the specimens property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSpecimens().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Specimen }
	 * 
	 * 
	 */
	public List<Specimen> getSpecimens() {
		if (specimens == null) {
			specimens = new ArrayList<Specimen>();
		}
		return this.specimens;
	}

}
