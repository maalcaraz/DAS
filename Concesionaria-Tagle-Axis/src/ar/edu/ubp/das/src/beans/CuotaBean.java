package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.db.Bean;

public class CuotaBean implements Bean{

	private String dniCliente;
	private String idPlan;
	private String idCuota;
	private String importe;
	private String fechaVencimiento;
	private String pagada;
	

	public CuotaBean (){
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dniCliente == null) ? 0 : dniCliente.hashCode());
		result = prime * result + ((idCuota == null) ? 0 : idCuota.hashCode());
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
		CuotaBean other = (CuotaBean) obj;
		if (dniCliente == null) {
			if (other.dniCliente != null)
				return false;
		} else if (!dniCliente.equals(other.dniCliente))
			return false;
		if (idCuota == null) {
			if (other.idCuota != null)
				return false;
		} else if (!idCuota.equals(other.idCuota))
			return false;
		if (idPlan == null) {
			if (other.idPlan != null)
				return false;
		} else if (!idPlan.equals(other.idPlan))
			return false;
		return true;
	}



	public String getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(String idCuota) {
		this.idCuota = idCuota;
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
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	public String getPagada() {
		return pagada;
	}
	public void setPagada(String pagada) {
		this.pagada = pagada;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}
