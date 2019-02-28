//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.28 at 01:56:20 AM CET 
//


package org.opentrafficsim.xml.generated;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.opentrafficsim.xml.bindings.LengthAdapter;
import org.opentrafficsim.xml.bindings.SignedLengthAdapter;


/**
 * <p>Java class for CROSSSECTIONELEMENT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CROSSSECTIONELEMENT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="OFFSET" use="required" type="{http://www.opentrafficsim.org/ots}SIGNEDLENGTHTYPE" /&gt;
 *       &lt;attribute name="WIDTH" type="{http://www.opentrafficsim.org/ots}LENGTHTYPE" /&gt;
 *       &lt;attribute name="COLOR" type="{http://www.opentrafficsim.org/ots}COLORTYPE" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CROSSSECTIONELEMENT")
@XmlSeeAlso({
    CSELANE.class,
    CSENOTRAFFICLANE.class,
    CSESHOULDER.class,
    CSESTRIPE.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
public class CROSSSECTIONELEMENT {

    @XmlAttribute(name = "NAME")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected String name;
    @XmlAttribute(name = "OFFSET", required = true)
    @XmlJavaTypeAdapter(SignedLengthAdapter.class)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected org.djunits.value.vdouble.scalar.Length offset;
    @XmlAttribute(name = "WIDTH")
    @XmlJavaTypeAdapter(LengthAdapter.class)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected org.djunits.value.vdouble.scalar.Length width;
    @XmlAttribute(name = "COLOR")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected String color;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public String getNAME() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public org.djunits.value.vdouble.scalar.Length getOFFSET() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setOFFSET(org.djunits.value.vdouble.scalar.Length value) {
        this.offset = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public org.djunits.value.vdouble.scalar.Length getWIDTH() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setWIDTH(org.djunits.value.vdouble.scalar.Length value) {
        this.width = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public String getCOLOR() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setCOLOR(String value) {
        this.color = value;
    }

}
