package ar.edu.ubp.das.src.sorteos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ParticipanteForm extends DynaActionForm{

	private String dniCliente;
	private String idConcesionaria;
	private String idSorteo;
	private String apellidoNombre;
	private String idPlan;
	private String fechaSorteo;
	private String email;
	private String nombreConcesionaria;
	
	public String getNombreConcesionaria() {
		return nombreConcesionaria;
	}
	public void setNombreConcesionaria(String nombreConcesionaria) {
		this.nombreConcesionaria = nombreConcesionaria;
	}
	public String getApellidoNombre() {
		return apellidoNombre;
	}
	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}
	public String getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
	
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
	
	public String getFechaSorteo() {
		return fechaSorteo;
	}
	public void setFechaSorteo(String fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
