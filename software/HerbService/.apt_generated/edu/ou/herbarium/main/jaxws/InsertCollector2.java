
package edu.ou.herbarium.main.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "insertCollector2", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertCollector2", namespace = "http://main.herbarium.ou.edu/")
public class InsertCollector2 {

    @XmlElement(name = "arg0", namespace = "")
    private edu.ou.herbarium.service.Collector arg0;

    /**
     * 
     * @return
     *     returns Collector
     */
    public edu.ou.herbarium.service.Collector getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(edu.ou.herbarium.service.Collector arg0) {
        this.arg0 = arg0;
    }

}
