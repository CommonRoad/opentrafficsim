//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.28 at 03:03:00 AM CEST 
//


package org.opentrafficsim.xml.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opentrafficsim.org/ots}COMPATIBILITY" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{http://www.opentrafficsim.org/ots}SPEEDLIMIT" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="PARENT" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="DEFAULT" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}base"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "compatibility",
    "speedlimit"
})
@XmlRootElement(name = "LINKTYPE")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
public class LINKTYPE
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    private final static long serialVersionUID = 10102L;
    @XmlElement(name = "COMPATIBILITY", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected List<COMPATIBILITY> compatibility;
    @XmlElement(name = "SPEEDLIMIT")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected List<SPEEDLIMIT> speedlimit;
    @XmlAttribute(name = "ID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected String id;
    @XmlAttribute(name = "PARENT")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected String parent;
    @XmlAttribute(name = "DEFAULT")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected Boolean _default;
    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected String base;

    /**
     * Gets the value of the compatibility property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compatibility property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMPATIBILITY().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMPATIBILITY }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public List<COMPATIBILITY> getCOMPATIBILITY() {
        if (compatibility == null) {
            compatibility = new ArrayList<COMPATIBILITY>();
        }
        return this.compatibility;
    }

    /**
     * Gets the value of the speedlimit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the speedlimit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSPEEDLIMIT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SPEEDLIMIT }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public List<SPEEDLIMIT> getSPEEDLIMIT() {
        if (speedlimit == null) {
            speedlimit = new ArrayList<SPEEDLIMIT>();
        }
        return this.speedlimit;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public String getPARENT() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setPARENT(String value) {
        this.parent = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public boolean isDEFAULT() {
        if (_default == null) {
            return false;
        } else {
            return _default;
        }
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setDEFAULT(Boolean value) {
        this._default = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public String getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setBase(String value) {
        this.base = value;
    }

}
