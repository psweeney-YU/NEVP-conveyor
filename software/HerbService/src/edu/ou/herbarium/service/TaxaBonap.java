package edu.ou.herbarium.service;

/**
 * TaxaBonap entity. @author MyEclipse Persistence Tools
 */

public class TaxaBonap implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String family;
	private String genus;
	private String specificEpithet;
	private String subspecificEpithet;
	private String infraspecificEpithet;
	private String infraspecificRank;
	private String authorship;
	private String bonapId;
	private String uuid;

	// Constructors

	/** default constructor */
	public TaxaBonap() {
	}

	/** full constructor */
	public TaxaBonap(String family, String genus, String specificEpithet,
			String subspecificEpithet, String infraspecificEpithet,
			String infraspecificRank, String authorship, String bonapId,
			String uuid) {
		this.family = family;
		this.genus = genus;
		this.specificEpithet = specificEpithet;
		this.subspecificEpithet = subspecificEpithet;
		this.infraspecificEpithet = infraspecificEpithet;
		this.infraspecificRank = infraspecificRank;
		this.authorship = authorship;
		this.bonapId = bonapId;
		this.uuid = uuid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSpecificEpithet() {
		return this.specificEpithet;
	}

	public void setSpecificEpithet(String specificEpithet) {
		this.specificEpithet = specificEpithet;
	}

	public String getSubspecificEpithet() {
		return this.subspecificEpithet;
	}

	public void setSubspecificEpithet(String subspecificEpithet) {
		this.subspecificEpithet = subspecificEpithet;
	}

	public String getInfraspecificEpithet() {
		return this.infraspecificEpithet;
	}

	public void setInfraspecificEpithet(String infraspecificEpithet) {
		this.infraspecificEpithet = infraspecificEpithet;
	}

	public String getInfraspecificRank() {
		return this.infraspecificRank;
	}

	public void setInfraspecificRank(String infraspecificRank) {
		this.infraspecificRank = infraspecificRank;
	}

	public String getAuthorship() {
		return this.authorship;
	}

	public void setAuthorship(String authorship) {
		this.authorship = authorship;
	}

	public String getBonapId() {
		return this.bonapId;
	}

	public void setBonapId(String bonapId) {
		this.bonapId = bonapId;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}