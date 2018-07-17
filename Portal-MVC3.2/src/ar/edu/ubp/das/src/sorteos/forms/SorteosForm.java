package ar.edu.ubp.das.src.sorteos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class SorteosForm extends DynaActionForm{
	
	public SorteosForm(){}
	
	private String idSorteo;
	private String fechaSorteado;
	private String fechaProximo;
	
	
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

	public String getFechaProximo() {
		return fechaProximo;
	}

	public void setFechaProximo(String fechaProximo) {
		this.fechaProximo = fechaProximo;
	}
}
