package ar.edu.ubp.das.src.chexa.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ClienteForm extends DynaActionForm {

	private String dniCliente;
	private String nomCliente;

	public ClienteForm() {
		
	}
	
	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void getNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
}
