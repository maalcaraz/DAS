
package ar.edu.ubp.das.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para verificarCancelado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="verificarCancelado"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dni_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id_plan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificarCancelado", propOrder = {
    "dniCliente",
    "idPlan"
})
public class VerificarCancelado {

    @XmlElement(name = "dni_cliente")
    protected String dniCliente;
    @XmlElement(name = "id_plan")
    protected String idPlan;

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

}
