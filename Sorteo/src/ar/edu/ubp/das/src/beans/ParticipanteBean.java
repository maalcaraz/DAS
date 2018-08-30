package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.src.db.Bean;

public class ParticipanteBean implements Bean{

	private String dniCliente;
	private String idConcesionaria;
	private String idSorteo;
	
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public String getIdConcesionaria() {
		return idConcesionaria;
	}
	public void setIdConcesionaria(String idConcesionaria) {
		this.idConcesionaria = idConcesionaria;
	}
	public String getIdSorteo() {
		return idSorteo;
	}
	public void setIdSorteo(String idSorteo) {
		this.idSorteo = idSorteo;
	}
}
