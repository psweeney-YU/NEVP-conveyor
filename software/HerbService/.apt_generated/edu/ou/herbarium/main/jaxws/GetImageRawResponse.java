
package edu.ou.herbarium.main.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getImageRawResponse", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getImageRawResponse", namespace = "http://main.herbarium.ou.edu/")
public class GetImageRawResponse {

    @XmlElement(name = "return", namespace = "")
    private edu.ou.herbarium.service.ImageRaw _return;

    /**
     * 
     * @return
     *     returns ImageRaw
     */
    public edu.ou.herbarium.service.ImageRaw getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(edu.ou.herbarium.service.ImageRaw _return) {
        this._return = _return;
    }

}
