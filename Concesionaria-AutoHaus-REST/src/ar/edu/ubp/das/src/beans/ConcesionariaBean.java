package ar.edu.ubp.das.src.beans;

import java.util.List;

import ar.edu.ubp.das.db.Bean;

public class ConcesionariaBean implements Bean{

	List<ClienteBean> clientes;
	List<AdquiridoBean> adquiridos;
	List<CuotaBean> cuotas;
	List<PlanBean> planes;
	
	public List<ClienteBean> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteBean> clientes) {
		this.clientes = clientes;
	}
	public List<AdquiridoBean> getAdquiridos() {
		return adquiridos;
	}
	public void setAdquiridos(List<AdquiridoBean> adquiridos) {
		this.adquiridos = adquiridos;
	}
	public List<CuotaBean> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<CuotaBean> cuotas) {
		this.cuotas = cuotas;
	}
	public List<PlanBean> getPlanes() {
		return planes;
	}
	public void setPlanes(List<PlanBean> planes) {
		this.planes = planes;
	}
	@Override
	public String toString() {
		return "ConcesionariaBean [clientes=" + clientes + ", adquiridos=" + adquiridos + ", cuotas=" + cuotas
				+ ", planes=" + planes + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adquiridos == null) ? 0 : adquiridos.hashCode());
		result = prime * result + ((clientes == null) ? 0 : clientes.hashCode());
		result = prime * result + ((cuotas == null) ? 0 : cuotas.hashCode());
		result = prime * result + ((planes == null) ? 0 : planes.hashCode());
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
		ConcesionariaBean other = (ConcesionariaBean) obj;
		if (adquiridos == null) {
			if (other.adquiridos != null)
				return false;
		} else if (!adquiridos.equals(other.adquiridos))
			return false;
		if (clientes == null) {
			if (other.clientes != null)
				return false;
		} else if (!clientes.equals(other.clientes))
			return false;
		if (cuotas == null) {
			if (other.cuotas != null)
				return false;
		} else if (!cuotas.equals(other.cuotas))
			return false;
		if (planes == null) {
			if (other.planes != null)
				return false;
		} else if (!planes.equals(other.planes))
			return false;
		return true;
	}

}
