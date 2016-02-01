
package edu.ou.herbarium.main.jaxws;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "insertSpecimen", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertSpecimen", namespace = "http://main.herbarium.ou.edu/", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "arg4",
    "arg5",
    "arg6"
})
public class InsertSpecimen {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;
    @XmlElement(name = "arg2", namespace = "")
    private edu.ou.herbarium.service.Specimen arg2;
    @XmlElement(name = "arg3", namespace = "")
    private DataHandler arg3;
    @XmlElement(name = "arg4", namespace = "")
    private DataHandler arg4;
    @XmlElement(name = "arg5", namespace = "")
    private DataHandler arg5;
    @XmlElement(name = "arg6", namespace = "")
    private DataHandler arg6;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns Specimen
     */
    public edu.ou.herbarium.service.Specimen getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(edu.ou.herbarium.service.Specimen arg2) {
        this.arg2 = arg2;
    }

    /**
     * 
     * @return
     *     returns DataHandler
     */
    public DataHandler getArg3() {
        return this.arg3;
    }

    /**
     * 
     * @param arg3
     *     the value for the arg3 property
     */
    public void setArg3(DataHandler arg3) {
        this.arg3 = arg3;
    }

    /**
     * 
     * @return
     *     returns DataHandler
     */
    public DataHandler getArg4() {
        return this.arg4;
    }

    /**
     * 
     * @param arg4
     *     the value for the arg4 property
     */
    public void setArg4(DataHandler arg4) {
        this.arg4 = arg4;
    }

    /**
     * 
     * @return
     *     returns DataHandler
     */
    public DataHandler getArg5() {
        return this.arg5;
    }

    /**
     * 
     * @param arg5
     *     the value for the arg5 property
     */
    public void setArg5(DataHandler arg5) {
        this.arg5 = arg5;
    }

    /**
     * 
     * @return
     *     returns DataHandler
     */
    public DataHandler getArg6() {
        return this.arg6;
    }

    /**
     * 
     * @param arg6
     *     the value for the arg6 property
     */
    public void setArg6(DataHandler arg6) {
        this.arg6 = arg6;
    }

}
