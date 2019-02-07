package ar.edu.ubp.das.src.sorteos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class SorteosForm extends DynaActionForm{
	
	public SorteosForm(){}
	
	private String idSorteo;
	private String fechaDefinida;
	private String fechaEjecucion;
	private String fechaNotificacion;
	private String pendiente;
	
	
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

	public String getIdSorteo() {
		return idSorteo;
	}

	public void setIdSorteo(String idSorteo) {
		this.idSorteo = idSorteo;
	}

	public String getFechaDefinida() {
		return fechaDefinida;
	}

	public void setFechaDefinida(String fechaSorteado) {
		this.fechaDefinida = fechaSorteado;
	}

	public String getfechaEjecucion() {
		return fechaEjecucion;
	}

	public void setfechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
}
