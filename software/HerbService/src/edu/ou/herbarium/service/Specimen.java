package edu.ou.herbarium.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 * Specimen entity. @author MyEclipse Persistence Tools
 */

public class Specimen implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer specimenId;
	private ImageData imageData;
	private System system;
	private Folder folder;
	private ImageRaw imageRaw;
	private User user;
	private Collection collection;
	private String barcode;
	private Integer specimenType;
	private String scientificName;
	private String family;
	private String genus;
	private String infraspecificRank;
	private String infraspecificEpithet;
	private String specificEpithet;
	private Timestamp recordDate;
	private String recordEnterBy;
	private String scientificNameAuthorship;
	private String country;
	private String darwinCountry;
	private String county;
	private String stateProvince;
	private String town;
	private String verbatimEventDate;
	private Timestamp beginEventDate;
	private Timestamp endEventDate;
	private String watermark;
	private String watermarkDate;
	private String sheetNotes;
	private String generalNotes;
	private String collectorNotes;
	private Integer writtenNumber;
	private Integer folderNumber;
	private String herbarium;
	private String identificationQualifier;
	private String recordNumber;
	private Timestamp creationDate;
	private Timestamp modificationDate;
	private Integer missingInfo;
	private Integer imageError;
	private Timestamp exportDate;
	private String checksum;
	private String storageLocation;
	private String recordedByGuid;
	private Set<Ownership> ownerships = new HashSet<Ownership>(0);
	private Set<SpecCollectorMap> specCollectorMaps = new HashSet<SpecCollectorMap>(0);
	private Short idropSync;
	
	
	private String imagePath;
	private DataHandler handler;
	private String collectionCode;
	private String folderName;
	// Constructors

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	/** default constructor */
	public Specimen() {
	}

	/** full constructor */
	public Specimen(ImageData imageData, System system, Folder folder,
			ImageRaw imageRaw, User user, Collection collection,
			String barcode, Integer specimenType, String scientificName,
			String family, String genus, String infraspecificRank,
			String infraspecificEpithet, String specificEpithet,
			Timestamp recordDate, String recordEnterBy,
			String scientificNameAuthorship, String country,
			String darwinCountry, String county, String stateProvince,
			String town, String verbatimEventDate, Timestamp beginEventDate,
			Timestamp endEventDate, String watermark, String watermarkDate,
			String sheetNotes, String generalNotes, String collectorNotes,
			Integer writtenNumber, Integer folderNumber, String herbarium,
			String identificationQualifier, String recordNumber,
			Timestamp creationDate, Timestamp modificationDate,
			Integer missingInfo, Integer imageError, Timestamp exportDate,
			String checksum, String storageLocation, String recordedByGuid,
			Short idropSync, Set<SpecCollectorMap> specCollectorMaps) {
		this.imageData = imageData;
		this.system = system;
		this.folder = folder;
		this.imageRaw = imageRaw;
		this.user = user;
		this.collection = collection;
		this.barcode = barcode;
		this.specimenType = specimenType;
		this.scientificName = scientificName;
		this.family = family;
		this.genus = genus;
		this.infraspecificRank = infraspecificRank;
		this.infraspecificEpithet = infraspecificEpithet;
		this.specificEpithet = specificEpithet;
		this.recordDate = recordDate;
		this.recordEnterBy = recordEnterBy;
		this.scientificNameAuthorship = scientificNameAuthorship;
		this.country = country;
		this.darwinCountry = darwinCountry;
		this.county = county;
		this.stateProvince = stateProvince;
		this.town = town;
		this.verbatimEventDate = verbatimEventDate;
		this.beginEventDate = beginEventDate;
		this.endEventDate = endEventDate;
		this.watermark = watermark;
		this.watermarkDate = watermarkDate;
		this.sheetNotes = sheetNotes;
		this.generalNotes = generalNotes;
		this.collectorNotes = collectorNotes;
		this.writtenNumber = writtenNumber;
		this.folderNumber = folderNumber;
		this.herbarium = herbarium;
		this.identificationQualifier = identificationQualifier;
		this.recordNumber = recordNumber;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.missingInfo = missingInfo;
		this.imageError = imageError;
		this.exportDate = exportDate;
		this.checksum = checksum;
		this.storageLocation = storageLocation;
		this.recordedByGuid = recordedByGuid;
		this.idropSync = idropSync;
		this.specCollectorMaps = specCollectorMaps;
	}

	// Property accessors

	public Integer getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public ImageData getImageData() {
		return this.imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public System getSystem() {
		return this.system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	@XmlTransient
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public ImageRaw getImageRaw() {
		return this.imageRaw;
	}

	public void setImageRaw(ImageRaw imageRaw) {
		this.imageRaw = imageRaw;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection getCollection() {
		return this.collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getSpecimenType() {
		return this.specimenType;
	}

	public void setSpecimenType(Integer specimenType) {
		this.specimenType = specimenType;
	}

	public String getScientificName() {
		return this.scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getGenus() {
		return this.genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getInfraspecificRank() {
		return this.infraspecificRank;
	}

	public void setInfraspecificRank(String infraspecificRank) {
		this.infraspecificRank = infraspecificRank;
	}

	public String getInfraspecificEpithet() {
		return this.infraspecificEpithet;
	}

	public void setInfraspecificEpithet(String infraspecificEpithet) {
		this.infraspecificEpithet = infraspecificEpithet;
	}

	public String getSpecificEpithet() {
		return this.specificEpithet;
	}

	public void setSpecificEpithet(String specificEpithet) {
		this.specificEpithet = specificEpithet;
	}
	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Timestamp recordDate) {
		this.recordDate = recordDate;
	}

	public String getRecordEnterBy() {
		return this.recordEnterBy;
	}

	public void setRecordEnterBy(String recordEnterBy) {
		this.recordEnterBy = recordEnterBy;
	}

	public String getScientificNameAuthorship() {
		return this.scientificNameAuthorship;
	}

	public void setScientificNameAuthorship(String scientificNameAuthorship) {
		this.scientificNameAuthorship = scientificNameAuthorship;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDarwinCountry() {
		return this.darwinCountry;
	}

	public void setDarwinCountry(String darwinCountry) {
		this.darwinCountry = darwinCountry;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getVerbatimEventDate() {
		return this.verbatimEventDate;
	}

	public void setVerbatimEventDate(String verbatimEventDate) {
		this.verbatimEventDate = verbatimEventDate;
	}
	
	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getBeginEventDate() {
		return this.beginEventDate;
	}

	public void setBeginEventDate(Timestamp beginEventDate) {
		this.beginEventDate = beginEventDate;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getEndEventDate() {
		return this.endEventDate;
	}

	public void setEndEventDate(Timestamp endEventDate) {
		this.endEventDate = endEventDate;
	}

	public String getWatermark() {
		return this.watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public String getWatermarkDate() {
		return this.watermarkDate;
	}

	public void setWatermarkDate(String watermarkDate) {
		this.watermarkDate = watermarkDate;
	}

	public String getSheetNotes() {
		return this.sheetNotes;
	}

	public void setSheetNotes(String sheetNotes) {
		this.sheetNotes = sheetNotes;
	}

	public String getGeneralNotes() {
		return this.generalNotes;
	}

	public void setGeneralNotes(String generalNotes) {
		this.generalNotes = generalNotes;
	}

	public String getCollectorNotes() {
		return this.collectorNotes;
	}

	public void setCollectorNotes(String collectorNotes) {
		this.collectorNotes = collectorNotes;
	}

	public Integer getWrittenNumber() {
		return this.writtenNumber;
	}

	public void setWrittenNumber(Integer writtenNumber) {
		this.writtenNumber = writtenNumber;
	}

	public Integer getFolderNumber() {
		return this.folderNumber;
	}

	public void setFolderNumber(Integer folderNumber) {
		this.folderNumber = folderNumber;
	}

	public String getHerbarium() {
		return this.herbarium;
	}

	public void setHerbarium(String herbarium) {
		this.herbarium = herbarium;
	}

	public String getIdentificationQualifier() {
		return this.identificationQualifier;
	}

	public void setIdentificationQualifier(String identificationQualifier) {
		this.identificationQualifier = identificationQualifier;
	}

	public String getRecordNumber() {
		return this.recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class) 
	public Timestamp getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Integer getMissingInfo() {
		return this.missingInfo;
	}

	public void setMissingInfo(Integer missingInfo) {
		this.missingInfo = missingInfo;
	}

	public Integer getImageError() {
		return this.imageError;
	}

	public void setImageError(Integer imageError) {
		this.imageError = imageError;
	}
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getExportDate() {
		return this.exportDate;
	}

	public void setExportDate(Timestamp exportDate) {
		this.exportDate = exportDate;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getStorageLocation() {
		return this.storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getRecordedByGuid() {
		return this.recordedByGuid;
	}

	public void setRecordedByGuid(String recordedByGuid) {
		this.recordedByGuid = recordedByGuid;
	}

	public Short getIdropSync() {
		return this.idropSync;
	}

	public void setIdropSync(Short idropSync) {
		this.idropSync = idropSync;
	}

	public Set<SpecCollectorMap> getSpecCollectorMaps() {
		return this.specCollectorMaps;
	}

	public void setSpecCollectorMaps(Set<SpecCollectorMap> specCollectorMaps) {
		this.specCollectorMaps = specCollectorMaps;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public DataHandler getHandler() {
		return handler;
	}

	public void setHandler(DataHandler handler) {
		this.handler = handler;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

}