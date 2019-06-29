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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *         &lt;element name="FROM"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="NODE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TO"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="NODE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="VIA" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="NODE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;choice maxOccurs="2"&gt;
 *           &lt;element name="DISTANCECOST" type="{http://www.opentrafficsim.org/ots}DISTANCECOSTTYPE"/&gt;
 *           &lt;element name="TIMECOST" type="{http://www.opentrafficsim.org/ots}TIMECOSTTYPE"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="GTUTYPE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
    "from",
    "to",
    "via",
    "distancecostOrTIMECOST"
})
@XmlRootElement(name = "SHORTESTROUTE")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
public class SHORTESTROUTE
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    private final static long serialVersionUID = 10102L;
    @XmlElement(name = "FROM", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected SHORTESTROUTE.FROM from;
    @XmlElement(name = "TO", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected SHORTESTROUTE.TO to;
    @XmlElement(name = "VIA")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected List<SHORTESTROUTE.VIA> via;
    @XmlElementRefs({
        @XmlElementRef(name = "DISTANCECOST", namespace = "http://www.opentrafficsim.org/ots", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "TIMECOST", namespace = "http://www.opentrafficsim.org/ots", type = JAXBElement.class, required = false)
    })
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected List<JAXBElement<String>> distancecostOrTIMECOST;
    @XmlAttribute(name = "GTUTYPE", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected String gtutype;
    @XmlAttribute(name = "ID", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected String id;
    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    protected String base;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link SHORTESTROUTE.FROM }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public SHORTESTROUTE.FROM getFROM() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link SHORTESTROUTE.FROM }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setFROM(SHORTESTROUTE.FROM value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link SHORTESTROUTE.TO }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public SHORTESTROUTE.TO getTO() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link SHORTESTROUTE.TO }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setTO(SHORTESTROUTE.TO value) {
        this.to = value;
    }

    /**
     * Gets the value of the via property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the via property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVIA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SHORTESTROUTE.VIA }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public List<SHORTESTROUTE.VIA> getVIA() {
        if (via == null) {
            via = new ArrayList<SHORTESTROUTE.VIA>();
        }
        return this.via;
    }

    /**
     * Gets the value of the distancecostOrTIMECOST property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distancecostOrTIMECOST property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDISTANCECOSTOrTIMECOST().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public List<JAXBElement<String>> getDISTANCECOSTOrTIMECOST() {
        if (distancecostOrTIMECOST == null) {
            distancecostOrTIMECOST = new ArrayList<JAXBElement<String>>();
        }
        return this.distancecostOrTIMECOST;
    }

    /**
     * Gets the value of the gtutype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public String getGTUTYPE() {
        return gtutype;
    }

    /**
     * Sets the value of the gtutype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public void setGTUTYPE(String value) {
        this.gtutype = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="NODE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public static class FROM
        implements Serializable
    {

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        private final static long serialVersionUID = 10102L;
        @XmlAttribute(name = "NODE", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        protected String node;

        /**
         * Gets the value of the node property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        public String getNODE() {
            return node;
        }

        /**
         * Sets the value of the node property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        public void setNODE(String value) {
            this.node = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="NODE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public static class TO
        implements Serializable
    {

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        private final static long serialVersionUID = 10102L;
        @XmlAttribute(name = "NODE", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        protected String node;

        /**
         * Gets the value of the node property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        public String getNODE() {
            return node;
        }

        /**
         * Sets the value of the node property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        public void setNODE(String value) {
            this.node = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="NODE" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
    public static class VIA
        implements Serializable
    {

        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        private final static long serialVersionUID = 10102L;
        @XmlAttribute(name = "NODE", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        protected String node;

        /**
         * Gets the value of the node property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        public String getNODE() {
            return node;
        }

        /**
         * Sets the value of the node property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-28T03:03:00+02:00", comments = "JAXB RI v2.3.0")
        public void setNODE(String value) {
            this.node = value;
        }

    }

}
