package ou.edu.herbarium.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for specCollectorMap complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="specCollectorMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collector" type="{http://main.herbarium.ou.edu/}collector" minOccurs="0"/>
 *         &lt;element name="specCollectorMapId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "specCollectorMap", propOrder = { "collector",
		"specCollectorMapId" })
public class SpecCollectorMap {

	protected Collector collector;
	protected Integer specCollectorMapId;

	/**
	 * Gets the value of the collector property.
	 * 
	 * @return possible object is {@link Collector }
	 * 
	 */
	public Collector getCollector() {
		return collector;
	}

	/**
	 * Sets the value of the collector property.
	 * 
	 * @param value
	 *            allowed object is {@link Collector }
	 * 
	 */
	public void setCollector(Collector value) {
		this.collector = value;
	}

	/**
	 * Gets the value of the specCollectorMapId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSpecCollectorMapId() {
		return specCollectorMapId;
	}

	/**
	 * Sets the value of the specCollectorMapId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSpecCollectorMapId(Integer value) {
		this.specCollectorMapId = value;
	}

}
