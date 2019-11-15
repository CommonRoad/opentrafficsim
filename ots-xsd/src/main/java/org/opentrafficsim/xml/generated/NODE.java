//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.15 at 04:27:05 PM CET 
//


package org.opentrafficsim.xml.generated;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.vecmath.Point3d;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
public class NODE implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    private final static long serialVersionUID = 10102L;
    @XmlAttribute(name = "ID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    protected String id;
    @XmlAttribute(name = "COORDINATE", required = true)
    @XmlJavaTypeAdapter(CoordinateAdapter.class)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    protected Point3d coordinate;
    @XmlAttribute(name = "DIRECTION")
    @XmlJavaTypeAdapter(DirectionAdapter.class)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    protected Direction direction;
    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    protected String base;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the coordinate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-11-15T04:27:05+01:00", comments = "JAXB RI v2.3.0")
    public void setBase(String value) {
        this.base = value;
    }

}
