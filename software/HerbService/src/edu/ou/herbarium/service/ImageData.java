package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

/**
 * ImageData entity. @author MyEclipse Persistence Tools
 */

public class ImageData implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer imageDataId;
	private String imageDataPath;
	private Integer imageDataType;
	private Set<Specimen> specimens = new HashSet<Specimen>(0);
	private Set<Collector> collectors = new HashSet<Collector>(0);

	// Constructors

	/** default constructor */
	public ImageData() {
	}

	/** minimal constructor */
	public ImageData(String imageDataPath, Integer imageDataType) {
		this.imageDataPath = imageDataPath;
		this.imageDataType = imageDataType;
	}

	/** full constructor */
	public ImageData(String imageDataPath, Integer imageDataType,
			Set<Specimen> specimens, Set<Collector> collectors) {
		this.imageDataPath = imageDataPath;
		this.imageDataType = imageDataType;
		this.specimens = specimens;
		this.collectors = collectors;
	}

	// Property accessors

	public Integer getImageDataId() {
		return this.imageDataId;
	}

	public void setImageDataId(Integer imageDataId) {
		this.imageDataId = imageDataId;
	}

	public String getImageDataPath() {
		return this.imageDataPath;
	}

	public void setImageDataPath(String imageDataPath) {
		this.imageDataPath = imageDataPath;
	}

	public Integer getImageDataType() {
		return this.imageDataType;
	}

	public void setImageDataType(Integer imageDataType) {
		this.imageDataType = imageDataType;
	}

	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}

	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

	public Set<Collector> getCollectors() {
		return this.collectors;
	}

	public void setCollectors(Set<Collector> collectors) {
		this.collectors = collectors;
	}

}