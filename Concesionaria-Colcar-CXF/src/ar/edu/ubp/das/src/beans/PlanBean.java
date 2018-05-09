package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class PlanBean implements Bean {
	
	@Override
	public String toString() {
		return "PlanBean [idPlan=" + idPlan + ", cantCuotas=" + cantCuotas + "]";
	}

	private String idPlan;
	private String dniCliente;
	
	private String cantCuotas;
	
	public String getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(String cantCuotas) {
		this.cantCuotas = cantCuotas;
	}
	
	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	
	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

}
