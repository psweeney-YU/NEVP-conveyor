
package edu.ou.herbarium.main.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteSystem", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteSystem", namespace = "http://main.herbarium.ou.edu/", propOrder = {
    "arg0",
    "arg1"
})
public class DeleteSystem {

    @XmlElement(name = "arg0", namespace = "")
    private edu.ou.herbarium.service.System arg0;
    @XmlElement(name = "arg1", namespace = "")
    private Integer arg1;

    /**
     * 
     * @return
     *     returns System
     */
    public edu.ou.herbarium.service.System getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(edu.ou.herbarium.service.System arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(Integer arg1) {
        this.arg1 = arg1;
    }

}
