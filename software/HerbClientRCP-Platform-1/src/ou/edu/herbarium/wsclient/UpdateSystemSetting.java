package ou.edu.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updateSystemSetting complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="updateSystemSetting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://main.herbarium.ou.edu/}systemSetting" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://main.herbarium.ou.edu/}system" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSystemSetting", propOrder = { "arg0", "arg1" })
public class UpdateSystemSetting {

	protected SystemSetting arg0;
	protected System arg1;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link SystemSetting }
	 * 
	 */
	public SystemSetting getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link SystemSetting }
	 * 
	 */
	public void setArg0(SystemSetting value) {
		this.arg0 = value;
	}

	/**
	 * Gets the value of the arg1 property.
	 * 
	 * @return possible object is {@link System }
	 * 
	 */
	public System getArg1() {
		return arg1;
	}

	/**
	 * Sets the value of the arg1 property.
	 * 
	 * @param value
	 *            allowed object is {@link System }
	 * 
	 */
	public void setArg1(System value) {
		this.arg1 = value;
	}

}
