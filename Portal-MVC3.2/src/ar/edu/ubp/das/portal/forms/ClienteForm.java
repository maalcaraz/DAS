package ar.edu.ubp.das.portal.forms;

import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ClienteForm extends DynaActionForm {

	// Atributos
	private String dniCliente;
	private String idConcesionaria;
	private String nomCliente;
	private String edad;
	private String domicilio;
	private String emailCliente;
	private String idLocalidad;
	private String codProvincia;
	private List<AdquiridoForm> adquiridos;
	private List<PlanForm> planes;
	private TransaccionForm transaccion;
	
	public List<AdquiridoForm> getAdquiridos() {
		return adquiridos;
	}

	public void setAdquiridos(List<AdquiridoForm> adquiridos) {
		this.adquiridos = adquiridos;
	}

	public List<PlanForm> getPlanes() {
		return planes;
	}

	public void setPlanes(List<PlanForm> planes) {
		this.planes = planes;
	}

	public TransaccionForm getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(TransaccionForm transaccion) {
		this.transaccion = transaccion;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adquiridos == null) ? 0 : adquiridos.hashCode());
		result = prime * result + ((codProvincia == null) ? 0 : codProvincia.hashCode());
		result = prime * result + ((dniCliente == null) ? 0 : dniCliente.hashCode());
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((edad == null) ? 0 : edad.hashCode());
		result = prime * result + ((emailCliente == null) ? 0 : emailCliente.hashCode());
		result = prime * result + ((idConcesionaria == null) ? 0 : idConcesionaria.hashCode());
		result = prime * result + ((idLocalidad == null) ? 0 : idLocalidad.hashCode());
		result = prime * result + ((nomCliente == null) ? 0 : nomCliente.hashCode());
		result = prime * result + ((planes == null) ? 0 : planes.hashCode());
		result = prime * result + ((transaccion == null) ? 0 : transaccion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteForm other = (ClienteForm) obj;
		if (adquiridos == null) {
			if (other.adquiridos != null)
				return false;
		} else if (!adquiridos.equals(other.adquiridos))
			return false;
		if (codProvincia == null) {
			if (other.codProvincia != null)
				return false;
		} else if (!codProvincia.equals(other.codProvincia))
			return false;
		if (dniCliente == null) {
			if (other.dniCliente != null)
				return false;
		} else if (!dniCliente.equals(other.dniCliente))
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (edad == null) {
			if (other.edad != null)
				return false;
		} else if (!edad.equals(other.edad))
			return false;
		if (emailCliente == null) {
			if (other.emailCliente != null)
				return false;
		} else if (!emailCliente.equals(other.emailCliente))
			return false;
		if (idConcesionaria == null) {
			if (other.idConcesionaria != null)
				return false;
		} else if (!idConcesionaria.equals(other.idConcesionaria))
			return false;
		if (idLocalidad == null) {
			if (other.idLocalidad != null)
				return false;
		} else if (!idLocalidad.equals(other.idLocalidad))
			return false;
		if (nomCliente == null) {
			if (other.nomCliente != null)
				return false;
		} else if (!nomCliente.equals(other.nomCliente))
			return false;
		if (planes == null) {
			if (other.planes != null)
				return false;
		} else if (!planes.equals(other.planes))
			return false;
		if (transaccion == null) {
			if (other.transaccion != null)
				return false;
		} else if (!transaccion.equals(other.transaccion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteForm [dniCliente=" + dniCliente + ", idConcesionaria=" + idConcesionaria + ", nomCliente="
				+ nomCliente + ", edad=" + edad + ", domicilio=" + domicilio + ", emailCliente=" + emailCliente
				+ ", idLocalidad=" + idLocalidad + ", codProvincia=" + codProvincia + ", adquiridos=" + adquiridos
				+ ", planes=" + planes + ", transaccion=" + transaccion + "]";
	}

	// Constructor publico
	public ClienteForm() {
	}

	// Metodos
	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniComprador) {
		this.dniCliente = dniComprador;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getIdConcesionaria() {
		return idConcesionaria;
	}

	public void setIdConcesionaria(String idConcesionaria) {
		this.idConcesionaria = idConcesionaria;
	}

}
