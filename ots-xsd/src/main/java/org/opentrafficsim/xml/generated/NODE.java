//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.28 at 01:56:20 AM CET 
//


package org.opentrafficsim.xml.generated;

import javax.annotation.Generated;
import javax.vecmath.Point3d;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.djunits.value.vdouble.scalar.Direction;
import org.opentrafficsim.xml.bindings.CoordinateAdapter;
import org.opentrafficsim.xml.bindings.DirectionAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="NAME" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="COORDINATE" use="required" type="{http://www.opentrafficsim.org/ots}COORDINATETYPE" /&gt;
 *       &lt;attribute name="DIRECTION" type="{http://www.opentrafficsim.org/ots}DIRECTIONTYPE" /&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}base"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "NODE")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
public class NODE {

    @XmlAttribute(name = "NAME", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected String name;
    @XmlAttribute(name = "COORDINATE", required = true)
    @XmlJavaTypeAdapter(CoordinateAdapter.class)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected Point3d coordinate;
    @XmlAttribute(name = "DIRECTION")
    @XmlJavaTypeAdapter(DirectionAdapter.class)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected Direction direction;
    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    protected String base;

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
     * Gets the value of the coordinate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public Point3d getCOORDINATE() {
        return coordinate;
    }

    /**
     * Sets the value of the coordinate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setCOORDINATE(Point3d value) {
        this.coordinate = value;
    }

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public Direction getDIRECTION() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setDIRECTION(Direction value) {
        this.direction = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-02-28T01:56:20+01:00", comments = "JAXB RI v2.3.0")
    public void setBase(String value) {
        this.base = value;
    }

}
