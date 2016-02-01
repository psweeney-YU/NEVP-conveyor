package edu.ou.herbarium.service;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 * SystemSetting entity. @author MyEclipse Persistence Tools
 */

public class SystemSetting implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer settingId;
	private System system;
	private Timestamp settingDate;
	private String cameraName;
	private String cameraExpoTime;
	private Integer cameraAperture;
	private Integer cameraIso;
	private Integer colorTargetPositionX;
	private Integer colorTargetPositionY;
	private String workspacePath;
	private String installationUrl;
	private Integer conveyorVoltage;
	private Integer sensorVoltage;
	private Integer deleted;

	// Constructors

	/** default constructor */
	public SystemSetting() {
	}

	/** minimal constructor */
	public SystemSetting(System system, Timestamp settingDate) {
		this.system = system;
		this.settingDate = settingDate;
	}

	/** full constructor */
	public SystemSetting(System system, Timestamp settingDate,
			String cameraName, String cameraExpoTime, Integer cameraAperture,
			Integer cameraIso, Integer colorTargetPositionX,
			Integer colorTargetPositionY, String workspacePath,
			String installationUrl, Integer conveyorVoltage,
			Integer sensorVoltage, Integer deleted) {
		this.system = system;
		this.settingDate = settingDate;
		this.cameraName = cameraName;
		this.cameraExpoTime = cameraExpoTime;
		this.cameraAperture = cameraAperture;
		this.cameraIso = cameraIso;
		this.colorTargetPositionX = colorTargetPositionX;
		this.colorTargetPositionY = colorTargetPositionY;
		this.workspacePath = workspacePath;
		this.installationUrl = installationUrl;
		this.conveyorVoltage = conveyorVoltage;
		this.sensorVoltage = sensorVoltage;
		this.deleted = deleted;
	}

	// Property accessors

	public Integer getSettingId() {
		return this.settingId;
	}

	public void setSettingId(Integer settingId) {
		this.settingId = settingId;
	}
	@XmlTransient
	public System getSystem() {
		return this.system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getSettingDate() {
		return this.settingDate;
	}

	public void setSettingDate(Timestamp settingDate) {
		this.settingDate = settingDate;
	}

	public String getCameraName() {
		return this.cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	public String getCameraExpoTime() {
		return this.cameraExpoTime;
	}

	public void setCameraExpoTime(String cameraExpoTime) {
		this.cameraExpoTime = cameraExpoTime;
	}

	public Integer getCameraAperture() {
		return this.cameraAperture;
	}

	public void setCameraAperture(Integer cameraAperture) {
		this.cameraAperture = cameraAperture;
	}

	public Integer getCameraIso() {
		return this.cameraIso;
	}

	public void setCameraIso(Integer cameraIso) {
		this.cameraIso = cameraIso;
	}

	public Integer getColorTargetPositionX() {
		return this.colorTargetPositionX;
	}

	public void setColorTargetPositionX(Integer colorTargetPositionX) {
		this.colorTargetPositionX = colorTargetPositionX;
	}

	public Integer getColorTargetPositionY() {
		return this.colorTargetPositionY;
	}

	public void setColorTargetPositionY(Integer colorTargetPositionY) {
		this.colorTargetPositionY = colorTargetPositionY;
	}

	public String getWorkspacePath() {
		return this.workspacePath;
	}

	public void setWorkspacePath(String workspacePath) {
		this.workspacePath = workspacePath;
	}

	public String getInstallationUrl() {
		return this.installationUrl;
	}

	public void setInstallationUrl(String installationUrl) {
		this.installationUrl = installationUrl;
	}

	public Integer getConveyorVoltage() {
		return this.conveyorVoltage;
	}

	public void setConveyorVoltage(Integer conveyorVoltage) {
		this.conveyorVoltage = conveyorVoltage;
	}

	public Integer getSensorVoltage() {
		return this.sensorVoltage;
	}

	public void setSensorVoltage(Integer sensorVoltage) {
		this.sensorVoltage = sensorVoltage;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}