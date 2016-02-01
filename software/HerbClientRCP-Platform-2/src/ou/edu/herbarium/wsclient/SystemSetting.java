package ou.edu.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for systemSetting complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="systemSetting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cameraAperture" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cameraExpoTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cameraIso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cameraName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colorTargetPositionX" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="colorTargetPositionY" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="conveyorVoltage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="installationUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorVoltage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="settingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="settingId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="workspacePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "systemSetting", propOrder = { "cameraAperture",
		"cameraExpoTime", "cameraIso", "cameraName", "colorTargetPositionX",
		"colorTargetPositionY", "conveyorVoltage", "deleted",
		"installationUrl", "sensorVoltage", "settingDate", "settingId",
		"workspacePath" })
public class SystemSetting {

	protected Integer cameraAperture;
	protected String cameraExpoTime;
	protected Integer cameraIso;
	protected String cameraName;
	protected Integer colorTargetPositionX;
	protected Integer colorTargetPositionY;
	protected Integer conveyorVoltage;
	protected Integer deleted;
	protected String installationUrl;
	protected Integer sensorVoltage;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar settingDate;
	protected Integer settingId;
	protected String workspacePath;

	/**
	 * Gets the value of the cameraAperture property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCameraAperture() {
		return cameraAperture;
	}

	/**
	 * Sets the value of the cameraAperture property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCameraAperture(Integer value) {
		this.cameraAperture = value;
	}

	/**
	 * Gets the value of the cameraExpoTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCameraExpoTime() {
		return cameraExpoTime;
	}

	/**
	 * Sets the value of the cameraExpoTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCameraExpoTime(String value) {
		this.cameraExpoTime = value;
	}

	/**
	 * Gets the value of the cameraIso property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCameraIso() {
		return cameraIso;
	}

	/**
	 * Sets the value of the cameraIso property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCameraIso(Integer value) {
		this.cameraIso = value;
	}

	/**
	 * Gets the value of the cameraName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCameraName() {
		return cameraName;
	}

	/**
	 * Sets the value of the cameraName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCameraName(String value) {
		this.cameraName = value;
	}

	/**
	 * Gets the value of the colorTargetPositionX property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getColorTargetPositionX() {
		return colorTargetPositionX;
	}

	/**
	 * Sets the value of the colorTargetPositionX property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setColorTargetPositionX(Integer value) {
		this.colorTargetPositionX = value;
	}

	/**
	 * Gets the value of the colorTargetPositionY property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getColorTargetPositionY() {
		return colorTargetPositionY;
	}

	/**
	 * Sets the value of the colorTargetPositionY property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setColorTargetPositionY(Integer value) {
		this.colorTargetPositionY = value;
	}

	/**
	 * Gets the value of the conveyorVoltage property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getConveyorVoltage() {
		return conveyorVoltage;
	}

	/**
	 * Sets the value of the conveyorVoltage property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setConveyorVoltage(Integer value) {
		this.conveyorVoltage = value;
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
	 * Gets the value of the installationUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInstallationUrl() {
		return installationUrl;
	}

	/**
	 * Sets the value of the installationUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInstallationUrl(String value) {
		this.installationUrl = value;
	}

	/**
	 * Gets the value of the sensorVoltage property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSensorVoltage() {
		return sensorVoltage;
	}

	/**
	 * Sets the value of the sensorVoltage property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSensorVoltage(Integer value) {
		this.sensorVoltage = value;
	}

	/**
	 * Gets the value of the settingDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSettingDate() {
		return settingDate;
	}

	/**
	 * Sets the value of the settingDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSettingDate(XMLGregorianCalendar value) {
		this.settingDate = value;
	}

	/**
	 * Gets the value of the settingId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSettingId() {
		return settingId;
	}

	/**
	 * Sets the value of the settingId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSettingId(Integer value) {
		this.settingId = value;
	}

	/**
	 * Gets the value of the workspacePath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWorkspacePath() {
		return workspacePath;
	}

	/**
	 * Sets the value of the workspacePath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWorkspacePath(String value) {
		this.workspacePath = value;
	}

}
