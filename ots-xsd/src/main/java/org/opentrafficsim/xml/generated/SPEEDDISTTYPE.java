//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.20 at 03:00:13 AM CEST 
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
 * <p>Java class for SPEEDDISTTYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SPEEDDISTTYPE"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opentrafficsim.org/ots}CONTDISTTYPE"&gt;
 *       &lt;attribute name="SPEEDUNIT" type="{http://www.opentrafficsim.org/ots}SPEEDUNITTYPE" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPEEDDISTTYPE")
@XmlSeeAlso({
    PARAMETERSPEEDDIST.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
public class SPEEDDISTTYPE
    extends CONTDISTTYPE
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    private final static long serialVersionUID = 10102L;
    @XmlAttribute(name = "SPEEDUNIT")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected String speedunit;

    /**
     * Gets the value of the speedunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public String getSPEEDUNIT() {
        return speedunit;
    }

    /**
     * Sets the value of the speedunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setSPEEDUNIT(String value) {
        this.speedunit = value;
    }

}
