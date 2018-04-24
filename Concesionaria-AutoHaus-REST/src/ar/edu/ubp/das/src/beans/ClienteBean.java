package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class ClienteBean implements Bean{

	//Atributos
	private String dniCliente;
	private String nomCliente;
	private String emailCliente;
	private String fechaSorteo;
	private String idPlan;

	@Override
	public String toString() {
		return "ClienteBean [dniCliente=" + dniCliente + ", nomCliente=" + nomCliente + ", emailCliente=" + emailCliente + 
		", fechaSorteo=" + fechaSorteo + "]";
	}

	//Constructor publico
	public ClienteBean(){}
	
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
	public String getFechaSorteo() {
		return fechaSorteo;
	}

	public void setFechaSorteo(String fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}

	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
}
