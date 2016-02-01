package org.nevp.herbarium.wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for specimen complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="specimen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beginEventDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="checksum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collection" type="{http://main.herbarium.ou.edu/}collection" minOccurs="0"/>
 *         &lt;element name="collectorNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="county" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="darwinCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endEventDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="exportDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="family" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folderNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="generalNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handler" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="herbarium" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idropSync" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="imageData" type="{http://main.herbarium.ou.edu/}imageData" minOccurs="0"/>
 *         &lt;element name="imageError" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="imagePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageRaw" type="{http://main.herbarium.ou.edu/}imageRaw" minOccurs="0"/>
 *         &lt;element name="infraspecificEpithet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="infraspecificRank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="missingInfo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modificationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="recordDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="recordEnterBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordedByGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scientificName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scientificNameAuthorship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sheetNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specCollectorMaps" type="{http://main.herbarium.ou.edu/}specCollectorMap" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specificEpithet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specimenId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specimenType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="stateProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storageLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="system" type="{http://main.herbarium.ou.edu/}system" minOccurs="0"/>
 *         &lt;element name="town" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user" type="{http://main.herbarium.ou.edu/}user" minOccurs="0"/>
 *         &lt;element name="verbatimEventDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="watermark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="watermarkDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="writtenNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "specimen", propOrder = { "barcode", "beginEventDate",
		"checksum", "collection", "collectorNotes", "country", "county",
		"creationDate", "darwinCountry", "endEventDate", "exportDate",
		"family", "folderNumber", "generalNotes", "genus", "handler",
		"herbarium", "identificationQualifier", "idropSync", "imageData",
		"imageError", "imagePath", "collectionCode","imageRaw", "infraspecificEpithet",
		"infraspecificRank", "missingInfo", "modificationDate", "recordDate",
		"recordEnterBy", "recordNumber", "recordedByGuid", "scientificName",
		"scientificNameAuthorship", "sheetNotes", "specCollectorMaps",
		"specificEpithet", "specimenId", "specimenType", "stateProvince",
		"storageLocation", "system", "town", "user", "verbatimEventDate",
		"watermark", "watermarkDate", "writtenNumber",
		"folderPath", "tempData","isFolder","workspace","url","folderName"})
public class Specimen {

	protected String barcode;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar beginEventDate;
	protected String checksum;
	protected Collection collection;
	protected String collectorNotes;
	protected String country;
	protected String county;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar creationDate;
	protected String darwinCountry;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar endEventDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar exportDate;
	protected String family;
	protected Integer folderNumber;
	protected String generalNotes;
	protected String genus;
	protected byte[] handler;
	protected String herbarium;
	protected String identificationQualifier;
	protected Short idropSync;
	protected ImageData imageData;
	protected Integer imageError;
	protected String imagePath;
	protected String collectionCode;
	protected ImageRaw imageRaw;
	protected String infraspecificEpithet;
	protected String infraspecificRank;
	protected Integer missingInfo;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modificationDate;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar recordDate;
	protected String recordEnterBy;
	protected String recordNumber;
	protected String recordedByGuid;
	protected String scientificName;
	protected String scientificNameAuthorship;
	protected String sheetNotes;
	@XmlElement(nillable = true)
	protected List<SpecCollectorMap> specCollectorMaps;
	protected String specificEpithet;
	protected Integer specimenId;
	protected Integer specimenType;
	protected String stateProvince;
	protected String storageLocation;
	protected System system;
	protected String town;
	protected User user;
	protected String verbatimEventDate;
	protected String watermark;
	protected String watermarkDate;
	protected Integer writtenNumber;
	private String folderPath;
	private String folderName;
	private String tempData;
	private Boolean isFolder;
	private String workspace;
	private String url;

	/**
	 * Gets the value of the barcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Sets the value of the barcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBarcode(String value) {
		this.barcode = value;
	}

	/**
	 * Gets the value of the beginEventDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getBeginEventDate() {
		return beginEventDate;
	}

	/**
	 * Sets the value of the beginEventDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setBeginEventDate(XMLGregorianCalendar value) {
		this.beginEventDate = value;
	}

	/**
	 * Gets the value of the checksum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChecksum() {
		return checksum;
	}

	/**
	 * Sets the value of the checksum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChecksum(String value) {
		this.checksum = value;
	}

	/**
	 * Gets the value of the collection property.
	 * 
	 * @return possible object is {@link Collection }
	 * 
	 */
	public Collection getCollection() {
		return collection;
	}

	/**
	 * Sets the value of the collection property.
	 * 
	 * @param value
	 *            allowed object is {@link Collection }
	 * 
	 */
	public void setCollection(Collection value) {
		this.collection = value;
	}

	/**
	 * Gets the value of the collectorNotes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectorNotes() {
		return collectorNotes;
	}

	/**
	 * Sets the value of the collectorNotes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCollectorNotes(String value) {
		this.collectorNotes = value;
	}

	/**
	 * Gets the value of the country property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the value of the country property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountry(String value) {
		this.country = value;
	}

	/**
	 * Gets the value of the county property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * Sets the value of the county property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCounty(String value) {
		this.county = value;
	}

	/**
	 * Gets the value of the creationDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the value of the creationDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreationDate(XMLGregorianCalendar value) {
		this.creationDate = value;
	}

	/**
	 * Gets the value of the darwinCountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDarwinCountry() {
		return darwinCountry;
	}

	/**
	 * Sets the value of the darwinCountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDarwinCountry(String value) {
		this.darwinCountry = value;
	}

	/**
	 * Gets the value of the endEventDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getEndEventDate() {
		return endEventDate;
	}

	/**
	 * Sets the value of the endEventDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setEndEventDate(XMLGregorianCalendar value) {
		this.endEventDate = value;
	}

	/**
	 * Gets the value of the exportDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getExportDate() {
		return exportDate;
	}

	/**
	 * Sets the value of the exportDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setExportDate(XMLGregorianCalendar value) {
		this.exportDate = value;
	}

	/**
	 * Gets the value of the family property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * Sets the value of the family property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFamily(String value) {
		this.family = value;
	}

	/**
	 * Gets the value of the folderNumber property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getFolderNumber() {
		return folderNumber;
	}

	/**
	 * Sets the value of the folderNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setFolderNumber(Integer value) {
		this.folderNumber = value;
	}

	/**
	 * Gets the value of the generalNotes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGeneralNotes() {
		return generalNotes;
	}

	/**
	 * Sets the value of the generalNotes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGeneralNotes(String value) {
		this.generalNotes = value;
	}

	/**
	 * Gets the value of the genus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGenus() {
		return genus;
	}

	/**
	 * Sets the value of the genus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGenus(String value) {
		this.genus = value;
	}

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
	 * Gets the value of the herbarium property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHerbarium() {
		return herbarium;
	}

	/**
	 * Sets the value of the herbarium property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHerbarium(String value) {
		this.herbarium = value;
	}

	/**
	 * Gets the value of the identificationQualifier property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdentificationQualifier() {
		return identificationQualifier;
	}

	/**
	 * Sets the value of the identificationQualifier property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdentificationQualifier(String value) {
		this.identificationQualifier = value;
	}

	/**
	 * Gets the value of the idropSync property.
	 * 
	 * @return possible object is {@link Short }
	 * 
	 */
	public Short getIdropSync() {
		return idropSync;
	}

	/**
	 * Sets the value of the idropSync property.
	 * 
	 * @param value
	 *            allowed object is {@link Short }
	 * 
	 */
	public void setIdropSync(Short value) {
		this.idropSync = value;
	}

	/**
	 * Gets the value of the imageData property.
	 * 
	 * @return possible object is {@link ImageData }
	 * 
	 */
	public ImageData getImageData() {
		return imageData;
	}

	/**
	 * Sets the value of the imageData property.
	 * 
	 * @param value
	 *            allowed object is {@link ImageData }
	 * 
	 */
	public void setImageData(ImageData value) {
		this.imageData = value;
	}

	/**
	 * Gets the value of the imageError property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getImageError() {
		return imageError;
	}

	/**
	 * Sets the value of the imageError property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setImageError(Integer value) {
		this.imageError = value;
	}

	/**
	 * Gets the value of the imagePath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the value of the imagePath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImagePath(String value) {
		this.imagePath = value;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	/**
	 * Gets the value of the imageRaw property.
	 * 
	 * @return possible object is {@link ImageRaw }
	 * 
	 */
	public ImageRaw getImageRaw() {
		return imageRaw;
	}

	/**
	 * Sets the value of the imageRaw property.
	 * 
	 * @param value
	 *            allowed object is {@link ImageRaw }
	 * 
	 */
	public void setImageRaw(ImageRaw value) {
		this.imageRaw = value;
	}

	/**
	 * Gets the value of the infraspecificEpithet property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInfraspecificEpithet() {
		return infraspecificEpithet;
	}

	/**
	 * Sets the value of the infraspecificEpithet property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInfraspecificEpithet(String value) {
		this.infraspecificEpithet = value;
	}

	/**
	 * Gets the value of the infraspecificRank property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInfraspecificRank() {
		return infraspecificRank;
	}

	/**
	 * Sets the value of the infraspecificRank property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInfraspecificRank(String value) {
		this.infraspecificRank = value;
	}

	/**
	 * Gets the value of the missingInfo property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getMissingInfo() {
		return missingInfo;
	}

	/**
	 * Sets the value of the missingInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setMissingInfo(Integer value) {
		this.missingInfo = value;
	}

	/**
	 * Gets the value of the modificationDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getModificationDate() {
		return modificationDate;
	}

	/**
	 * Sets the value of the modificationDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setModificationDate(XMLGregorianCalendar value) {
		this.modificationDate = value;
	}

	/**
	 * Gets the value of the recordDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getRecordDate() {
		return recordDate;
	}

	/**
	 * Sets the value of the recordDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setRecordDate(XMLGregorianCalendar value) {
		this.recordDate = value;
	}

	/**
	 * Gets the value of the recordEnterBy property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRecordEnterBy() {
		return recordEnterBy;
	}

	/**
	 * Sets the value of the recordEnterBy property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRecordEnterBy(String value) {
		this.recordEnterBy = value;
	}

	/**
	 * Gets the value of the recordNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRecordNumber() {
		return recordNumber;
	}

	/**
	 * Sets the value of the recordNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRecordNumber(String value) {
		this.recordNumber = value;
	}

	/**
	 * Gets the value of the recordedByGuid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRecordedByGuid() {
		return recordedByGuid;
	}

	/**
	 * Sets the value of the recordedByGuid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRecordedByGuid(String value) {
		this.recordedByGuid = value;
	}

	/**
	 * Gets the value of the scientificName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getScientificName() {
		return scientificName;
	}

	/**
	 * Sets the value of the scientificName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setScientificName(String value) {
		this.scientificName = value;
	}

	/**
	 * Gets the value of the scientificNameAuthorship property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getScientificNameAuthorship() {
		return scientificNameAuthorship;
	}

	/**
	 * Sets the value of the scientificNameAuthorship property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setScientificNameAuthorship(String value) {
		this.scientificNameAuthorship = value;
	}

	/**
	 * Gets the value of the sheetNotes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSheetNotes() {
		return sheetNotes;
	}

	/**
	 * Sets the value of the sheetNotes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSheetNotes(String value) {
		this.sheetNotes = value;
	}

	/**
	 * Gets the value of the specCollectorMaps property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the specCollectorMaps property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSpecCollectorMaps().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SpecCollectorMap }
	 * 
	 * 
	 */
	public List<SpecCollectorMap> getSpecCollectorMaps() {
		if (specCollectorMaps == null) {
			specCollectorMaps = new ArrayList<SpecCollectorMap>();
		}
		return this.specCollectorMaps;
	}

	/**
	 * Gets the value of the specificEpithet property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSpecificEpithet() {
		return specificEpithet;
	}

	/**
	 * Sets the value of the specificEpithet property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSpecificEpithet(String value) {
		this.specificEpithet = value;
	}

	/**
	 * Gets the value of the specimenId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSpecimenId() {
		return specimenId;
	}

	/**
	 * Sets the value of the specimenId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSpecimenId(Integer value) {
		this.specimenId = value;
	}

	/**
	 * Gets the value of the specimenType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSpecimenType() {
		return specimenType;
	}

	/**
	 * Sets the value of the specimenType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSpecimenType(Integer value) {
		this.specimenType = value;
	}

	/**
	 * Gets the value of the stateProvince property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStateProvince() {
		return stateProvince;
	}

	/**
	 * Sets the value of the stateProvince property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStateProvince(String value) {
		this.stateProvince = value;
	}

	/**
	 * Gets the value of the storageLocation property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStorageLocation() {
		return storageLocation;
	}

	/**
	 * Sets the value of the storageLocation property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStorageLocation(String value) {
		this.storageLocation = value;
	}

	/**
	 * Gets the value of the system property.
	 * 
	 * @return possible object is {@link System }
	 * 
	 */
	public System getSystem() {
		return system;
	}

	/**
	 * Sets the value of the system property.
	 * 
	 * @param value
	 *            allowed object is {@link System }
	 * 
	 */
	public void setSystem(System value) {
		this.system = value;
	}

	/**
	 * Gets the value of the town property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTown() {
		return town;
	}

	/**
	 * Sets the value of the town property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTown(String value) {
		this.town = value;
	}

	/**
	 * Gets the value of the user property.
	 * 
	 * @return possible object is {@link User }
	 * 
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the value of the user property.
	 * 
	 * @param value
	 *            allowed object is {@link User }
	 * 
	 */
	public void setUser(User value) {
		this.user = value;
	}

	/**
	 * Gets the value of the verbatimEventDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVerbatimEventDate() {
		return verbatimEventDate;
	}

	/**
	 * Sets the value of the verbatimEventDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVerbatimEventDate(String value) {
		this.verbatimEventDate = value;
	}

	/**
	 * Gets the value of the watermark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWatermark() {
		return watermark;
	}

	/**
	 * Sets the value of the watermark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWatermark(String value) {
		this.watermark = value;
	}

	/**
	 * Gets the value of the watermarkDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWatermarkDate() {
		return watermarkDate;
	}

	/**
	 * Sets the value of the watermarkDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWatermarkDate(String value) {
		this.watermarkDate = value;
	}

	/**
	 * Gets the value of the writtenNumber property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getWrittenNumber() {
		return writtenNumber;
	}

	/**
	 * Sets the value of the writtenNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setWrittenNumber(Integer value) {
		this.writtenNumber = value;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getTempData() {
		return tempData;
	}

	public void setTempData(String tempData) {
		this.tempData = tempData;
	}

	public Boolean getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
