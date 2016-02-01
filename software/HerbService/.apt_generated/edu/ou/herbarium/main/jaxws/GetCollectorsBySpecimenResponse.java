
package edu.ou.herbarium.main.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getCollectorsBySpecimenResponse", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCollectorsBySpecimenResponse", namespace = "http://main.herbarium.ou.edu/")
public class GetCollectorsBySpecimenResponse {

    @XmlElement(name = "return", namespace = "")
    private List<edu.ou.herbarium.service.Collector> _return;

    /**
     * 
     * @return
     *     returns List<Collector>
     */
    public List<edu.ou.herbarium.service.Collector> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<edu.ou.herbarium.service.Collector> _return) {
        this._return = _return;
    }

}
