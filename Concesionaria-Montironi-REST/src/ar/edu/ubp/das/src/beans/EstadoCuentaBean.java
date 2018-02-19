package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class EstadoCuentaBean implements Bean{

	//Atributos
	private String dniCliente;
	private String nomCliente;
	private String emailCliente;
	
	//Constructor publico
	public EstadoCuentaBean(){}
	
	//Metodos
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
}
