//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.30 at 03:01:27 AM CEST 
//


package org.opentrafficsim.xml.generated;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LINEARDENSITYDISTTYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LINEARDENSITYDISTTYPE"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opentrafficsim.org/ots}CONTDISTTYPE"&gt;
 *       &lt;attribute name="LINEARDENSITYUNIT" type="{http://www.opentrafficsim.org/ots}LINEARDENSITYUNITTYPE" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LINEARDENSITYDISTTYPE")
@XmlSeeAlso({
    PARAMETERLINEARDENSITYDIST.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-30T03:01:27+02:00", comments = "JAXB RI v2.3.0")
public class LINEARDENSITYDISTTYPE
    extends CONTDISTTYPE
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-30T03:01:27+02:00", comments = "JAXB RI v2.3.0")
    private final static long serialVersionUID = 10102L;
    @XmlAttribute(name = "LINEARDENSITYUNIT")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-30T03:01:27+02:00", comments = "JAXB RI v2.3.0")
    protected String lineardensityunit;

    /**
     * Gets the value of the lineardensityunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-30T03:01:27+02:00", comments = "JAXB RI v2.3.0")
    public String getLINEARDENSITYUNIT() {
        return lineardensityunit;
    }

    /**
     * Sets the value of the lineardensityunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-30T03:01:27+02:00", comments = "JAXB RI v2.3.0")
    public void setLINEARDENSITYUNIT(String value) {
        this.lineardensityunit = value;
    }

}
