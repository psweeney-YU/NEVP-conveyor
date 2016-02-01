package org.nevp.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for imageRaw complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="imageRaw">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="handler" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="imageRawId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="imageRawName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageRawPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imageRaw", propOrder = { "handler", "imageRawId",
		"imageRawName", "imageRawPath" })
public class ImageRaw {

	protected byte[] handler;
	protected Integer imageRawId;
	protected String imageRawName;
	protected String imageRawPath;

	/**
	 * Gets the value of the handler property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getHandler() {
		return handler;
	}

	/**
	 * Sets the value of the handler property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setHandler(byte[] value) {
		this.handler = ((byte[]) value);
	}

	/**
	 * Gets the value of the imageRawId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getImageRawId() {
		return imageRawId;
	}

	/**
	 * Sets the value of the imageRawId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setImageRawId(Integer value) {
		this.imageRawId = value;
	}

	/**
	 * Gets the value of the imageRawName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImageRawName() {
		return imageRawName;
	}

	/**
	 * Sets the value of the imageRawName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImageRawName(String value) {
		this.imageRawName = value;
	}

	/**
	 * Gets the value of the imageRawPath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImageRawPath() {
		return imageRawPath;
	}

	/**
	 * Sets the value of the imageRawPath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImageRawPath(String value) {
		this.imageRawPath = value;
	}

}
