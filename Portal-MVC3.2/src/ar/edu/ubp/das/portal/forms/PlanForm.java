package ar.edu.ubp.das.portal.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class PlanForm extends DynaActionForm {
	
	private String idPlan;
	private String descripcion;
	private String nom_plan;
	private String cant_cuotas;
	private String entrega_pactada;
	private String financiacion;
	private String duenoPlan;
	private String idConcesionaria;
	
	

	@Override
	public String toString() {
		return "PlanForm [idPlan=" + idPlan + ", descripcion=" + descripcion + ", nom_plan=" + nom_plan
				+ ", cant_cuotas=" + cant_cuotas + ", entrega_pactada=" + entrega_pactada + ", financiacion="
				+ financiacion + ", duenoPlan=" + duenoPlan + ", idConcesionaria=" + idConcesionaria + "]";
	}

	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNom_plan() {
		return nom_plan;
	}
	public void setNom_plan(String nom_plan) {
		this.nom_plan = nom_plan;
	}
	public String getCant_cuotas() {
		return cant_cuotas;
	}
	public void setCant_cuotas(String cant_cuotas) {
		this.cant_cuotas = cant_cuotas;
	}
	public String getEntrega_pactada() {
		return entrega_pactada;
	}
	public void setEntrega_pactada(String entrega_pactada) {
		this.entrega_pactada = entrega_pactada;
	}
	public String getFinanciacion() {
		return financiacion;
	}
	public void setFinanciacion(String financiacion) {
		this.financiacion = financiacion;
	}
	public String getIdConcesionaria() {
		return idConcesionaria;
	}

	public void setIdConcesionaria(String idConcesionaria) {
		this.idConcesionaria = idConcesionaria;
	}

	public String getDuenoPlan() {
		return duenoPlan;
	}
	public void setDuenoPlan(String duenoPlan) {
		this.duenoPlan = duenoPlan;
	}


}
