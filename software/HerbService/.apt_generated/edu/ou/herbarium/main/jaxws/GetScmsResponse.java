
package edu.ou.herbarium.main.jaxws;

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getScmsResponse", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getScmsResponse", namespace = "http://main.herbarium.ou.edu/")
public class GetScmsResponse {

    @XmlElement(name = "return", namespace = "")
    private Set<edu.ou.herbarium.service.SpecCollectorMap> _return;

    /**
     * 
     * @return
     *     returns Set<SpecCollectorMap>
     */
    public Set<edu.ou.herbarium.service.SpecCollectorMap> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Set<edu.ou.herbarium.service.SpecCollectorMap> _return) {
        this._return = _return;
    }

}
