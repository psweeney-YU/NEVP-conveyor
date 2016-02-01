package edu.ou.herbarium.service;

import javax.xml.bind.annotation.XmlTransient;

/**
 * SpecCollectorMap entity. @author MyEclipse Persistence Tools
 */

public class SpecCollectorMap implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer specCollectorMapId;
	private Specimen specimen;
	private Collector collector;

	// Constructors

	/** default constructor */
	public SpecCollectorMap() {
	}

	/** full constructor */
	public SpecCollectorMap(Specimen specimen, Collector collector) {
		this.specimen = specimen;
		this.collector = collector;
	}

	// Property accessors

	public Integer getSpecCollectorMapId() {
		return this.specCollectorMapId;
	}

	public void setSpecCollectorMapId(Integer specCollectorMapId) {
		this.specCollectorMapId = specCollectorMapId;
	}
	@XmlTransient
	public Specimen getSpecimen() {
		return this.specimen;
	}

	public void setSpecimen(Specimen specimen) {
		this.specimen = specimen;
	}

	public Collector getCollector() {
		return this.collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

}