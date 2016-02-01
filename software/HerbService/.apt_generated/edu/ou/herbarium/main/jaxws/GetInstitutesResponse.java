
package edu.ou.herbarium.main.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getInstitutesResponse", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getInstitutesResponse", namespace = "http://main.herbarium.ou.edu/")
public class GetInstitutesResponse {

    @XmlElement(name = "return", namespace = "")
    private List<edu.ou.herbarium.service.Institute> _return;

    /**
     * 
     * @return
     *     returns List<Institute>
     */
    public List<edu.ou.herbarium.service.Institute> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<edu.ou.herbarium.service.Institute> _return) {
        this._return = _return;
    }

}
