package ar.edu.ubp.das.src.sorteos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class SorteosForm extends DynaActionForm{
	
	public SorteosForm(){}
	
	private String idSorteo;
	private String fechaSorteado;
	private String fechaEjecucion;
	
	
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

	public String getfechaEjecucion() {
		return fechaEjecucion;
	}

	public void setfechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
}
