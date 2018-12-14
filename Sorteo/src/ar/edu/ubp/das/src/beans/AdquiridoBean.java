package ar.edu.ubp.das.src.beans;

import java.util.LinkedList;

import ar.edu.ubp.das.src.db.Bean;

public class AdquiridoBean implements Bean{
	
	private String dniCliente;
	private String idConcesionaria;
	private String idPlan;
	private String cancelado;
	private String ganadorSorteo;
	private String fechaEntrega;
	private String fechaSorteado;
	private String nroChasis;
	private String sucursalSuscripcion;
	private String fechaCompraPlan;
	private LinkedList<CuotaBean> cuotas = new LinkedList<>();
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancelado == null) ? 0 : cancelado.hashCode());
		result = prime * result + ((cuotas == null) ? 0 : cuotas.hashCode());
		result = prime * result + ((dniCliente == null) ? 0 : dniCliente.hashCode());
		result = prime * result + ((fechaCompraPlan == null) ? 0 : fechaCompraPlan.hashCode());
		result = prime * result + ((fechaEntrega == null) ? 0 : fechaEntrega.hashCode());
		result = prime * result + ((fechaSorteado == null) ? 0 : fechaSorteado.hashCode());
		result = prime * result + ((ganadorSorteo == null) ? 0 : ganadorSorteo.hashCode());
		result = prime * result + ((idConcesionaria == null) ? 0 : idConcesionaria.hashCode());
		result = prime * result + ((idPlan == null) ? 0 : idPlan.hashCode());
		result = prime * result + ((nroChasis == null) ? 0 : nroChasis.hashCode());
		result = prime * result + ((sucursalSuscripcion == null) ? 0 : sucursalSuscripcion.hashCode());
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
		AdquiridoBean other = (AdquiridoBean) obj;
		if (cancelado == null) {
			if (other.cancelado != null)
				return false;
		} else if (!cancelado.equals(other.cancelado))
			return false;
		if (cuotas == null) {
			if (other.cuotas != null)
				return false;
		} else if (!cuotas.equals(other.cuotas))
			return false;
		if (dniCliente == null) {
			if (other.dniCliente != null)
				return false;
		} else if (!dniCliente.equals(other.dniCliente))
			return false;
		if (fechaCompraPlan == null) {
			if (other.fechaCompraPlan != null)
				return false;
		} else if (!fechaCompraPlan.equals(other.fechaCompraPlan))
			return false;
		if (fechaEntrega == null) {
			if (other.fechaEntrega != null)
				return false;
		} else if (!fechaEntrega.equals(other.fechaEntrega))
			return false;
		if (fechaSorteado == null) {
			if (other.fechaSorteado != null)
				return false;
		} else if (!fechaSorteado.equals(other.fechaSorteado))
			return false;
		if (ganadorSorteo == null) {
			if (other.ganadorSorteo != null)
				return false;
		} else if (!ganadorSorteo.equals(other.ganadorSorteo))
			return false;
		if (idConcesionaria == null) {
			if (other.idConcesionaria != null)
				return false;
		} else if (!idConcesionaria.equals(other.idConcesionaria))
			return false;
		if (idPlan == null) {
			if (other.idPlan != null)
				return false;
		} else if (!idPlan.equals(other.idPlan))
			return false;
		if (nroChasis == null) {
			if (other.nroChasis != null)
				return false;
		} else if (!nroChasis.equals(other.nroChasis))
			return false;
		if (sucursalSuscripcion == null) {
			if (other.sucursalSuscripcion != null)
				return false;
		} else if (!sucursalSuscripcion.equals(other.sucursalSuscripcion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdquiridoBean [dniCliente=" + dniCliente + ", idConcesionaria=" + idConcesionaria + ", idPlan=" + idPlan
				+ ", cancelado=" + cancelado + ", ganadorSorteo=" + ganadorSorteo + ", fechaEntrega=" + fechaEntrega
				+ ", fechaSorteado=" + fechaSorteado + ", nroChasis=" + nroChasis + ", sucursalSuscripcion="
				+ sucursalSuscripcion + ", fechaCompraPlan=" + fechaCompraPlan + ", cuotas=" + cuotas + "]";
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

	
	public String getSucursalSuscripcion() {
		return sucursalSuscripcion;
	}

	public void setSucursalSuscripcion(String sucursalSuscripcion) {
		this.sucursalSuscripcion = sucursalSuscripcion;
	}
	
	public String getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
	public String getCancelado() {
		return cancelado;
	}
	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}
	public String getGanadorSorteo() {
		return ganadorSorteo;
	}
	public void setGanadorSorteo(String ganadorSorteo) {
		this.ganadorSorteo = ganadorSorteo;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getFechaSorteado() {
		return fechaSorteado;
	}
	public void setFechaSorteado(String fechaSorteado) {
		this.fechaSorteado = fechaSorteado;
	}
	public String getNroChasis() {
		return nroChasis;
	}
	public void setNroChasis(String nroChasis) {
		this.nroChasis = nroChasis;
	}
	
	public LinkedList<CuotaBean> getCuotas() {
		return cuotas;
	}

	public void setCuotas(LinkedList<CuotaBean> cuotas) {
		this.cuotas = cuotas;
	}
	
	public String getFechaCompraPlan() {
		return fechaCompraPlan;
	}

	public void setFechaCompraPlan(String fechaCompraPlan) {
		this.fechaCompraPlan = fechaCompraPlan;
	}

}
