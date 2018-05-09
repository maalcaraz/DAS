
package ar.edu.ubp.das.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para notificarGanador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="notificarGanador"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id_portal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id_concesionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dni_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombre_apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id_plan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fecha_sorteo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarGanador", propOrder = {
    "idPortal",
    "idConcesionaria",
    "dniCliente",
    "nombreApellido",
    "idPlan",
    "fechaSorteo"
})
public class NotificarGanador {

    @XmlElement(name = "id_portal")
    protected String idPortal;
    @XmlElement(name = "id_concesionaria")
    protected String idConcesionaria;
    @XmlElement(name = "dni_cliente")
    protected String dniCliente;
    @XmlElement(name = "nombre_apellido")
    protected String nombreApellido;
    @XmlElement(name = "id_plan")
    protected String idPlan;
    @XmlElement(name = "fecha_sorteo")
    protected String fechaSorteo;

    /**
     * Obtiene el valor de la propiedad idPortal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPortal() {
        return idPortal;
    }

    /**
     * Define el valor de la propiedad idPortal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPortal(String value) {
        this.idPortal = value;
    }

    /**
     * Obtiene el valor de la propiedad idConcesionaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdConcesionaria() {
        return idConcesionaria;
    }

    /**
     * Define el valor de la propiedad idConcesionaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdConcesionaria(String value) {
        this.idConcesionaria = value;
    }

    /**
     * Obtiene el valor de la propiedad dniCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDniCliente() {
        return dniCliente;
    }

    /**
     * Define el valor de la propiedad dniCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDniCliente(String value) {
        this.dniCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreApellido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreApellido() {
        return nombreApellido;
    }

    /**
     * Define el valor de la propiedad nombreApellido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreApellido(String value) {
        this.nombreApellido = value;
    }

    /**
     * Obtiene el valor de la propiedad idPlan.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPlan() {
        return idPlan;
    }

    /**
     * Define el valor de la propiedad idPlan.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPlan(String value) {
        this.idPlan = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSorteo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSorteo() {
        return fechaSorteo;
    }

    /**
     * Define el valor de la propiedad fechaSorteo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSorteo(String value) {
        this.fechaSorteo = value;
    }

}
