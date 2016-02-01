package ou.edu.herbarium.wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for folder complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="folder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folder" type="{http://main.herbarium.ou.edu/}folder" minOccurs="0"/>
 *         &lt;element name="folderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="folderInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folderPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folders" type="{http://main.herbarium.ou.edu/}folder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specimens" type="{http://main.herbarium.ou.edu/}specimen" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "folder", propOrder = { "folder", "folderId", "folderInfo",
		"folderName", "folderPath", "folders", "specimens" })
public class Folder {

	protected Folder folder;
	protected Integer folderId;
	protected String folderInfo;
	protected String folderName;
	protected String folderPath;
	@XmlElement(nillable = true)
	protected List<Folder> folders;
	@XmlElement(nillable = true)
	protected List<Specimen> specimens;

	/**
	 * Gets the value of the folder property.
	 * 
	 * @return possible object is {@link Folder }
	 * 
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * Sets the value of the folder property.
	 * 
	 * @param value
	 *            allowed object is {@link Folder }
	 * 
	 */
	public void setFolder(Folder value) {
		this.folder = value;
	}

	/**
	 * Gets the value of the folderId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getFolderId() {
		return folderId;
	}

	/**
	 * Sets the value of the folderId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setFolderId(Integer value) {
		this.folderId = value;
	}

	/**
	 * Gets the value of the folderInfo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFolderInfo() {
		return folderInfo;
	}

	/**
	 * Sets the value of the folderInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFolderInfo(String value) {
		this.folderInfo = value;
	}

	/**
	 * Gets the value of the folderName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * Sets the value of the folderName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFolderName(String value) {
		this.folderName = value;
	}

	/**
	 * Gets the value of the folderPath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFolderPath() {
		return folderPath;
	}

	/**
	 * Sets the value of the folderPath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFolderPath(String value) {
		this.folderPath = value;
	}

	/**
	 * Gets the value of the folders property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the folders property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFolders().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Folder }
	 * 
	 * 
	 */
	public List<Folder> getFolders() {
		if (folders == null) {
			folders = new ArrayList<Folder>();
		}
		return this.folders;
	}

	/**
	 * Gets the value of the specimens property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the specimens property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSpecimens().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Specimen }
	 * 
	 * 
	 */
	public List<Specimen> getSpecimens() {
		if (specimens == null) {
			specimens = new ArrayList<Specimen>();
		}
		return this.specimens;
	}

}
