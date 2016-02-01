package edu.ou.herbarium.service;

/**
 * Huhbotanistsrdf2013oct23Id entity. @author MyEclipse Persistence Tools
 */

public class Huhbotanistsrdf2013oct23Id implements java.io.Serializable {

	// Fields

	private Integer id;
	private String huhUri;
	private String collectorName;
	private String surname;
	private String firstname;
	private String type;
	private Integer agent;

	// Constructors

	/** default constructor */
	public Huhbotanistsrdf2013oct23Id() {
	}

	/** full constructor */
	public Huhbotanistsrdf2013oct23Id(Integer id, String huhUri,
			String collectorName, String surname, String firstname,
			String type, Integer agent) {
		this.id = id;
		this.huhUri = huhUri;
		this.collectorName = collectorName;
		this.surname = surname;
		this.firstname = firstname;
		this.type = type;
		this.agent = agent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHuhUri() {
		return this.huhUri;
	}

	public void setHuhUri(String huhUri) {
		this.huhUri = huhUri;
	}

	public String getCollectorName() {
		return this.collectorName;
	}

	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAgent() {
		return this.agent;
	}

	public void setAgent(Integer agent) {
		this.agent = agent;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Huhbotanistsrdf2013oct23Id))
			return false;
		Huhbotanistsrdf2013oct23Id castOther = (Huhbotanistsrdf2013oct23Id) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getHuhUri() == castOther.getHuhUri()) || (this
						.getHuhUri() != null && castOther.getHuhUri() != null && this
						.getHuhUri().equals(castOther.getHuhUri())))
				&& ((this.getCollectorName() == castOther.getCollectorName()) || (this
						.getCollectorName() != null
						&& castOther.getCollectorName() != null && this
						.getCollectorName()
						.equals(castOther.getCollectorName())))
				&& ((this.getSurname() == castOther.getSurname()) || (this
						.getSurname() != null && castOther.getSurname() != null && this
						.getSurname().equals(castOther.getSurname())))
				&& ((this.getFirstname() == castOther.getFirstname()) || (this
						.getFirstname() != null
						&& castOther.getFirstname() != null && this
						.getFirstname().equals(castOther.getFirstname())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getAgent() == castOther.getAgent()) || (this
						.getAgent() != null && castOther.getAgent() != null && this
						.getAgent().equals(castOther.getAgent())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getHuhUri() == null ? 0 : this.getHuhUri().hashCode());
		result = 37
				* result
				+ (getCollectorName() == null ? 0 : this.getCollectorName()
						.hashCode());
		result = 37 * result
				+ (getSurname() == null ? 0 : this.getSurname().hashCode());
		result = 37 * result
				+ (getFirstname() == null ? 0 : this.getFirstname().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getAgent() == null ? 0 : this.getAgent().hashCode());
		return result;
	}

}