
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

    @XmlElement(name = "arg0")
    private java.lang.String arg0;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

}

