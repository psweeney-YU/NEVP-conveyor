package ou.edu.herbarium.wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for system complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="system">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="systemDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="systemSettings" type="{http://main.herbarium.ou.edu/}systemSetting" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "system", propOrder = { "deleted", "systemDescription",
		"systemId", "systemSettings" })
public class System {

	protected Integer deleted;
	protected String systemDescription;
	protected Integer systemId;
	@XmlElement(nillable = true)
	protected List<SystemSetting> systemSettings;

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
	 * Gets the value of the systemDescription property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSystemDescription() {
		return systemDescription;
	}

	/**
	 * Sets the value of the systemDescription property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSystemDescription(String value) {
		this.systemDescription = value;
	}

	/**
	 * Gets the value of the systemId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSystemId() {
		return systemId;
	}

	/**
	 * Sets the value of the systemId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSystemId(Integer value) {
		this.systemId = value;
	}

	/**
	 * Gets the value of the systemSettings property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the systemSettings property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSystemSettings().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SystemSetting }
	 * 
	 * 
	 */
	public List<SystemSetting> getSystemSettings() {
		if (systemSettings == null) {
			systemSettings = new ArrayList<SystemSetting>();
		}
		return this.systemSettings;
	}

}
