package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.src.db.Bean;

public class SorteoBean implements Bean{
	private String idSorteo;
	private String fechaSorteado;
	private String fechaEjecucion;
	private String fechaNotificacion;
	private String pendiente;
	private String razon;
	

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public String getIdSorteo() {
		return idSorteo;
	}

	public void setIdSorteo(String idSorteo) {
		this.idSorteo = idSorteo;
	}

	public String getFechaSorteado() {
		return fechaSorteado;
	}

	public void setFechaSorteado(String fechaSorteado) {
		this.fechaSorteado = fechaSorteado;
	}

	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public String getPendiente() {
		return pendiente;
	}
	public void setPendiente(String pendiente) {
		this.pendiente = pendiente;
	}
	
	public String getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
}
