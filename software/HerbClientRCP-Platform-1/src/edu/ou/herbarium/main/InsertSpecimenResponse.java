package edu.ou.herbarium.main;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for insertSpecimenResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="insertSpecimenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://main.herbarium.ou.edu/}specimen" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertSpecimenResponse", propOrder = { "_return" })
public class InsertSpecimenResponse {

	@XmlElement(name = "return")
	protected Specimen _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link Specimen }
	 * 
	 */
	public Specimen getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link Specimen }
	 * 
	 */
	public void setReturn(Specimen value) {
		this._return = value;
	}

}
