//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.16 at 04:54:19 PM CET 
//


package org.opentrafficsim.xml.generated;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SIGNALGROUPTYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIGNALGROUPTYPE"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TRAFFICLIGHT" type="{http://www.opentrafficsim.org/ots}SIGNALGROUPTRAFFICLIGHTTYPE" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIGNALGROUPTYPE", propOrder = {
    "trafficlight"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-03-16T04:54:19+01:00", comments = "JAXB RI v2.3.0")
public class SIGNALGROUPTYPE {

    @XmlElement(name = "TRAFFICLIGHT", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-03-16T04:54:19+01:00", comments = "JAXB RI v2.3.0")
    protected List<SIGNALGROUPTRAFFICLIGHTTYPE> trafficlight;
    @XmlAttribute(name = "ID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-03-16T04:54:19+01:00", comments = "JAXB RI v2.3.0")
    protected String id;

    /**
     * Gets the value of the trafficlight property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trafficlight property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTRAFFICLIGHT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SIGNALGROUPTRAFFICLIGHTTYPE }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-03-16T04:54:19+01:00", comments = "JAXB RI v2.3.0")
    public List<SIGNALGROUPTRAFFICLIGHTTYPE> getTRAFFICLIGHT() {
        if (trafficlight == null) {
            trafficlight = new ArrayList<SIGNALGROUPTRAFFICLIGHTTYPE>();
        }
        return this.trafficlight;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-03-16T04:54:19+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-03-16T04:54:19+01:00", comments = "JAXB RI v2.3.0")
    public void setID(String value) {
        this.id = value;
    }

}
