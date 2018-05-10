package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class TransaccionBean implements Bean {

	public TransaccionBean() {
	
	}

	@Override
	public String toString() {
		return "TransaccionBean [estado_transaccion=" + estado_transaccion + ", mensajeRespuesta=" + mensajeRespuesta
				+ ", horaFechaTransaccion=" + horaFechaTransaccion + ", id_transaccion=" + id_transaccion + "]";
	}

	private String estado_transaccion;
	private String mensajeRespuesta;
	private String horaFechaTransaccion;
	private String retorno;
	
	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	private String id_transaccion;
	public String getId_transaccion() {
		return id_transaccion;
	}
	public void setId_transaccion(String id_transaccion) {
		this.id_transaccion = id_transaccion;
	}
	public String getEstado_transaccion() {
		return estado_transaccion;
	}
	public void setEstado_transaccion(String estado_transaccion) {
		this.estado_transaccion = estado_transaccion;
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
	

}
