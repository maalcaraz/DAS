package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class PlanBean implements Bean {
	
	private String idPlan;
	private String descripcion;
	private String nom_plan;
	private String cant_cuotas;
	private String entrega_pactada;
	private String financiacion;
	private String duenoPlan;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPlan == null) ? 0 : idPlan.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanBean other = (PlanBean) obj;
		if (idPlan == null) {
			if (other.idPlan != null)
				return false;
		} else if (!idPlan.equals(other.idPlan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanBean [idPlan=" + idPlan + ", descripcion=" + descripcion + ", nom_plan=" + nom_plan
				+ ", cant_cuotas=" + cant_cuotas + ", entrega_pactada=" + entrega_pactada + ", financiacion=" + financiacion
				+ ", duenoPlan=" + duenoPlan + "]";
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
	public void setFinanciacion(String financion) {
		this.financiacion = financion;
	}
	public String getDuenoPlan() {
		return duenoPlan;
	}
	public void setDuenoPlan(String duenoPlan) {
		this.duenoPlan = duenoPlan;
	}


}
