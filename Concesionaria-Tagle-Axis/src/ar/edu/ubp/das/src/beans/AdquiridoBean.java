package ar.edu.ubp.das.src.beans;

public class AdquiridoBean {
	
	private String idPlan;
	private String cancelado;
	private String ganadorSorteo;
	private String fechaEntrega;
	private String fechaSorteado;
	private String nroChasis;
	
	@Override
	public String toString() {
		return "AdquiridoBean [idPlan=" + idPlan + ", cancelado=" + cancelado + ", ganadorSorteo=" + ganadorSorteo
				+ ", fechaEntrega=" + fechaEntrega + ", fechaSorteado=" + fechaSorteado + ", nroChasis=" + nroChasis
				+ "]";
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
	
}
