package ar.edu.ubp.das.src.beans;
import ar.edu.ubp.das.db.Bean;

public class TransaccionBean implements Bean {

	public TransaccionBean() {
	
	}
	
	private String idTransaccion;
	private String estadoTransaccion;
	private String mensajeRespuesta;
	private String horaFechaTransaccion;
	private String retorno;
	private String idConcesionaria;
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransaccion == null) ? 0 : idTransaccion.hashCode());
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
		TransaccionBean other = (TransaccionBean) obj;
		if (idTransaccion == null) {
			if (other.idTransaccion != null)
				return false;
		} else if (!idTransaccion.equals(other.idTransaccion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransaccionBean [idTransaccion=" + idTransaccion + ", estadoTransaccion=" + estadoTransaccion
				+ ", mensajeRespuesta=" + mensajeRespuesta + ", horaFechaTransaccion=" + horaFechaTransaccion
				+ ", retorno=" + retorno + ", idConcesionaria=" + idConcesionaria + "]";
	}
	
	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getIdConcesionaria() {
		return idConcesionaria;
	}

	public void setIdConcesionaria(String idConcesionaria) {
		this.idConcesionaria = idConcesionaria;
	}
	
	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}
	public void setEstado_transaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	public String getHoraFechaTransaccion() {
		return horaFechaTransaccion;
	}
	public void setHoraFechaTransaccion(String horaFechaTransaccion) {
		this.horaFechaTransaccion = horaFechaTransaccion;
	}

	public String getId_transaccion() {
		return idTransaccion;
	}
	public void setId_transaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

}
