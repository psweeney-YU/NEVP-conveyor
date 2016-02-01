
package edu.ou.herbarium.main.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "insertSystemSetting", namespace = "http://main.herbarium.ou.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertSystemSetting", namespace = "http://main.herbarium.ou.edu/", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "arg4",
    "arg5",
    "arg6",
    "arg7",
    "arg8",
    "arg9"
})
public class InsertSystemSetting {

    @XmlElement(name = "arg0", namespace = "")
    private Integer arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;
    @XmlElement(name = "arg2", namespace = "")
    private String arg2;
    @XmlElement(name = "arg3", namespace = "")
    private Integer arg3;
    @XmlElement(name = "arg4", namespace = "")
    private Integer arg4;
    @XmlElement(name = "arg5", namespace = "")
    private Integer arg5;
    @XmlElement(name = "arg6", namespace = "")
    private Integer arg6;
    @XmlElement(name = "arg7", namespace = "")
    private Integer arg7;
    @XmlElement(name = "arg8", namespace = "")
    private Integer arg8;
    @XmlElement(name = "arg9", namespace = "")
    private String arg9;

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Integer arg0) {
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
     *     returns String
     */
    public String getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg3() {
        return this.arg3;
    }

    /**
     * 
     * @param arg3
     *     the value for the arg3 property
     */
    public void setArg3(Integer arg3) {
        this.arg3 = arg3;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg4() {
        return this.arg4;
    }

    /**
     * 
     * @param arg4
     *     the value for the arg4 property
     */
    public void setArg4(Integer arg4) {
        this.arg4 = arg4;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg5() {
        return this.arg5;
    }

    /**
     * 
     * @param arg5
     *     the value for the arg5 property
     */
    public void setArg5(Integer arg5) {
        this.arg5 = arg5;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg6() {
        return this.arg6;
    }

    /**
     * 
     * @param arg6
     *     the value for the arg6 property
     */
    public void setArg6(Integer arg6) {
        this.arg6 = arg6;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg7() {
        return this.arg7;
    }

    /**
     * 
     * @param arg7
     *     the value for the arg7 property
     */
    public void setArg7(Integer arg7) {
        this.arg7 = arg7;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg8() {
        return this.arg8;
    }

    /**
     * 
     * @param arg8
     *     the value for the arg8 property
     */
    public void setArg8(Integer arg8) {
        this.arg8 = arg8;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg9() {
        return this.arg9;
    }

    /**
     * 
     * @param arg9
     *     the value for the arg9 property
     */
    public void setArg9(String arg9) {
        this.arg9 = arg9;
    }

}
