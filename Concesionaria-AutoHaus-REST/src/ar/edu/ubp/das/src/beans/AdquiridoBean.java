package ar.edu.ubp.das.src.beans;

import java.util.LinkedList;

import ar.edu.ubp.das.db.Bean;

public class AdquiridoBean implements Bean{
	
	private String dniCliente;
	private String idPlan;
	private String cancelado;
	private String ganadorSorteo;
	private String fechaEntrega;
	private String fechaSorteado;
	private String nroChasis;
	private String sucursalSuscripcion;
	private LinkedList<CuotaBean> cuotas = new LinkedList<>();
	
	@Override
	public String toString() {
		return "AdquiridoBean [dniCliente=" + dniCliente + ", idPlan=" + idPlan + ", cancelado=" + cancelado
				+ ", ganadorSorteo=" + ganadorSorteo + ", fechaEntrega=" + fechaEntrega + ", fechaSorteado="
				+ fechaSorteado + ", nroChasis=" + nroChasis + ", sucursalSuscripcion=" + sucursalSuscripcion + "]";
	}
	
	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
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

}
