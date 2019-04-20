//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.20 at 03:00:13 AM CEST 
//


package org.opentrafficsim.xml.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for includeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="includeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element ref="{http://www.w3.org/2001/XInclude}fallback"/&gt;
 *         &lt;any processContents='lax' namespace='##other'/&gt;
 *         &lt;any processContents='lax' namespace=''/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="parse" type="{http://www.w3.org/2001/XInclude}parseType" default="xml" /&gt;
 *       &lt;attribute name="xpointer" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="encoding" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="accept" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="accept-language" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "includeType", namespace = "http://www.w3.org/2001/XInclude", propOrder = {
    "content"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
public class IncludeType implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    private final static long serialVersionUID = 10102L;
    @XmlElementRef(name = "fallback", namespace = "http://www.w3.org/2001/XInclude", type = JAXBElement.class, required = false)
    @XmlMixed
    @XmlAnyElement(lax = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected List<Object> content;
    @XmlAttribute(name = "href")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected String href;
    @XmlAttribute(name = "parse")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected ParseType parse;
    @XmlAttribute(name = "xpointer")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected String xpointer;
    @XmlAttribute(name = "encoding")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected String encoding;
    @XmlAttribute(name = "accept")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected String accept;
    @XmlAttribute(name = "accept-language")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    protected String acceptLanguage;
    @XmlAnyAttribute
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link FallbackType }{@code >}
     * {@link Element }
     * {@link Object }
     * {@link String }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the parse property.
     * 
     * @return
     *     possible object is
     *     {@link ParseType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public ParseType getParse() {
        if (parse == null) {
            return ParseType.XML;
        } else {
            return parse;
        }
    }

    /**
     * Sets the value of the parse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParseType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setParse(ParseType value) {
        this.parse = value;
    }

    /**
     * Gets the value of the xpointer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public String getXpointer() {
        return xpointer;
    }

    /**
     * Sets the value of the xpointer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setXpointer(String value) {
        this.xpointer = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setEncoding(String value) {
        this.encoding = value;
    }

    /**
     * Gets the value of the accept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public String getAccept() {
        return accept;
    }

    /**
     * Sets the value of the accept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setAccept(String value) {
        this.accept = value;
    }

    /**
     * Gets the value of the acceptLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    /**
     * Sets the value of the acceptLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public void setAcceptLanguage(String value) {
        this.acceptLanguage = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-04-20T03:00:13+02:00", comments = "JAXB RI v2.3.0")
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
