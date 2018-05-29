package ar.edu.ubp.das.src.beans;
import ar.edu.ubp.das.db.Bean;

public class TransaccionBean implements Bean {

	public TransaccionBean() {
	
	}
	
	private String idTransaccion;
	private String estadoTransaccion;
	private String mensajeRespuesta;
	private String horaFechaTransaccion;
	private String idConcesionaria;


	@Override
	public String toString() {
		return "TransaccionBean [idTransaccion=" + idTransaccion + ", estadoTransaccion=" + estadoTransaccion
				+ ", mensajeRespuesta=" + mensajeRespuesta + ", horaFechaTransaccion=" + horaFechaTransaccion
				+ ", idConcesionaria=" + idConcesionaria + "]";
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
