
package ar.edu.ubp.das.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.7
 * Thu Apr 19 21:00:54 ART 2018
 * Generated source version: 3.1.7
 */

@XmlRootElement(name = "verificarCancelado", namespace = "http://ws.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificarCancelado", namespace = "http://ws.das.ubp.edu.ar/")

public class VerificarCancelado {

	@XmlElement(name = "dni_cliente")
    private java.lang.String dni_cliente;
    @XmlElement(name = "id_plan")
    private java.lang.String id_plan;
  

    public java.lang.String getDniCliente() {
        return this.dni_cliente;
    }

    public void setDniCliente(java.lang.String newArg0)  {
        this.dni_cliente = newArg0;
    }
    public java.lang.String getIdPlan() {
        return this.id_plan;
    }

    public void setIdPlan(java.lang.String newArg0)  {
        this.id_plan = newArg0;
    }
        
    

}

