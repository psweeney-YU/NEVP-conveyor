
package edu.ou.herbarium.main.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateSpecimenResponse", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSpecimenResponse", namespace = "http://main.herbarium.ou.edu/")
public class UpdateSpecimenResponse {

    @XmlElement(name = "return", namespace = "")
    private edu.ou.herbarium.service.Specimen _return;

    /**
     * 
     * @return
     *     returns Specimen
     */
    public edu.ou.herbarium.service.Specimen getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(edu.ou.herbarium.service.Specimen _return) {
        this._return = _return;
    }

}
