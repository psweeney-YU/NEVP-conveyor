package edu.ou.herbarium.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Folder entity. @author MyEclipse Persistence Tools
 */

public class Folder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer folderId;
	private Folder folder;
	private String folderName;
	private String folderPath;
	private String folderInfo;
	private Set<Specimen> specimens = new HashSet<Specimen>(0);
	private Set<Folder> folders = new HashSet<Folder>(0);

	// Constructors

	/** default constructor */
	public Folder() {
	}

	/** full constructor */
	public Folder(Folder folder, String folderName, String folderPath, String folderInfo,
			Set<Specimen> specimens, Set<Folder> folders) {
		this.folder = folder;
		this.folderName = folderName;
		this.folderPath = folderPath;
		this.folderInfo = folderInfo;
		this.specimens = specimens;
		this.folders = folders;
	}

	// Property accessors

	public Integer getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFolderPath() {
		return this.folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getFolderInfo() {
		return this.folderInfo;
	}

	public void setFolderInfo(String folderInfo) {
		this.folderInfo = folderInfo;
	}

	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}

	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

	public Set<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(Set<Folder> folders) {
		this.folders = folders;
	}

}