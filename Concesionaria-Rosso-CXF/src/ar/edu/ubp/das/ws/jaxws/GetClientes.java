
package ar.edu.ubp.das.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.7
 * Sun Feb 18 14:00:32 ART 2018
 * Generated source version: 3.1.7
 */

@XmlRootElement(name = "getClientes", namespace = "http://ws.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClientes", namespace = "http://ws.das.ubp.edu.ar/", propOrder = {"id_portal"})

public class GetClientes {
	
	@XmlElement(name = "id_portal")
    private java.lang.String id_portal;
	
	public java.lang.String getId_portal() {
		return id_portal;
	}

	public void setId_portal(java.lang.String id_portal) {
		this.id_portal = id_portal;
	}

}

