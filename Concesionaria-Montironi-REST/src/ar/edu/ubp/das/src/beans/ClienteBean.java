package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class ClienteBean implements Bean{

	//Atributos
	private String dniCliente;
	private String nomCliente;
	private String edad;
	private String domicilio;
	private String emailCliente;
	private String telefono;
	private String idLocalidad;
	private String codProvincia;

	@Override
	public String toString() {
		return "ClienteBean [dniCliente=" + dniCliente + ", nomCliente=" + nomCliente + ", edad=" + edad
				+ ", domicilio=" + domicilio + ", emailCliente=" + emailCliente + ", telefono=" + telefono
				+ ", idLocalidad=" + idLocalidad + ", codProvincia=" + codProvincia + "]";
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
	
	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}


}
