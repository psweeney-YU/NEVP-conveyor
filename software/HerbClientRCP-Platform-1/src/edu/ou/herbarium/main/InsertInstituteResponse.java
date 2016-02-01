package edu.ou.herbarium.main;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for insertInstituteResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="insertInstituteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://main.herbarium.ou.edu/}institute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertInstituteResponse", propOrder = { "_return" })
public class InsertInstituteResponse {

	@XmlElement(name = "return")
	protected Institute _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link Institute }
	 * 
	 */
	public Institute getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link Institute }
	 * 
	 */
	public void setReturn(Institute value) {
		this._return = value;
	}

}
