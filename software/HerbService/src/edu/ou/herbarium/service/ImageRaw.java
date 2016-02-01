package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlTransient;
/**
 * ImageRaw entity. @author MyEclipse Persistence Tools
 */

public class ImageRaw implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer imageRawId;
	private String imageRawPath;
	private String imageRawName;
	private Set<Specimen> specimens = new HashSet<Specimen>(0);

	// Constructors
	private DataHandler handler;

	/** default constructor */
	public ImageRaw() {
	}

	/** minimal constructor */
	public ImageRaw(String imageRawPath) {
		this.imageRawPath = imageRawPath;
	}

	/** full constructor */
	public ImageRaw(String imageRawPath, String imageRawName, Set<Specimen> specimens) {
		this.imageRawPath = imageRawPath;
		this.imageRawName = imageRawName;
		this.specimens = specimens;
	}

	// Property accessors

	public Integer getImageRawId() {
		return this.imageRawId;
	}

	public void setImageRawId(Integer imageRawId) {
		this.imageRawId = imageRawId;
	}

	public String getImageRawPath() {
		return this.imageRawPath;
	}

	public void setImageRawPath(String imageRawPath) {
		this.imageRawPath = imageRawPath;
	}

	public String getImageRawName() {
		return this.imageRawName;
	}

	public void setImageRawName(String imageRawName) {
		this.imageRawName = imageRawName;
	}
	@XmlTransient
	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}

	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

	public DataHandler getHandler() {
		return handler;
	}

	public void setHandler(DataHandler handler) {
		this.handler = handler;
	}
}