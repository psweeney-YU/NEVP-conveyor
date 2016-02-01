package ou.edu.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for insertSpecimen complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="insertSpecimen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arg2" type="{http://main.herbarium.ou.edu/}specimen" minOccurs="0"/>
 *         &lt;element name="arg3" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="arg4" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="arg5" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="arg6" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertSpecimen", propOrder = { "arg0", "arg1", "arg2", "arg3",
		"arg4", "arg5", "arg6" })
public class InsertSpecimen {

	protected String arg0;
	protected String arg1;
	protected Specimen arg2;
	protected byte[] arg3;
	protected byte[] arg4;
	protected byte[] arg5;
	protected byte[] arg6;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArg0(String value) {
		this.arg0 = value;
	}

	/**
	 * Gets the value of the arg1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArg1() {
		return arg1;
	}

	/**
	 * Sets the value of the arg1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArg1(String value) {
		this.arg1 = value;
	}

	/**
	 * Gets the value of the arg2 property.
	 * 
	 * @return possible object is {@link Specimen }
	 * 
	 */
	public Specimen getArg2() {
		return arg2;
	}

	/**
	 * Sets the value of the arg2 property.
	 * 
	 * @param value
	 *            allowed object is {@link Specimen }
	 * 
	 */
	public void setArg2(Specimen value) {
		this.arg2 = value;
	}

	/**
	 * Gets the value of the arg3 property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getArg3() {
		return arg3;
	}

	/**
	 * Sets the value of the arg3 property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setArg3(byte[] value) {
		this.arg3 = ((byte[]) value);
	}

	/**
	 * Gets the value of the arg4 property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getArg4() {
		return arg4;
	}

	/**
	 * Sets the value of the arg4 property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setArg4(byte[] value) {
		this.arg4 = ((byte[]) value);
	}

	/**
	 * Gets the value of the arg5 property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getArg5() {
		return arg5;
	}

	/**
	 * Sets the value of the arg5 property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setArg5(byte[] value) {
		this.arg5 = ((byte[]) value);
	}

	/**
	 * Gets the value of the arg6 property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getArg6() {
		return arg6;
	}

	/**
	 * Sets the value of the arg6 property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setArg6(byte[] value) {
		this.arg6 = ((byte[]) value);
	}

}
