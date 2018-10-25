package ar.edu.ubp.das.src.concesionarias.forms;

import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.portal.forms.ClienteForm;
import ar.edu.ubp.das.portal.forms.CuotaForm;
import ar.edu.ubp.das.portal.forms.PlanForm;
import ar.edu.ubp.das.portal.forms.TransaccionForm;
import ar.edu.ubp.das.src.servicios.Servicio;
import ar.edu.ubp.das.src.servicios.ServicioFactory;

public class ConcesionariaForm extends DynaActionForm{

	public ConcesionariaForm(String tipoServicio) throws IllegalAccessException, ClassNotFoundException, Exception {
		
		webService = ServicioFactory.getServicio(tipoServicio);
	}
	
	String idConcesionaria;
	String nomConcesionaria;
	String cuit;
	String email;
	String direccion;
	String telefono;
	String ultimaActualizacion;
	String cantDiasCaducidad;
	Servicio webService;
	String urlServicio;
	String codTecnologia;
	private String aprobada;
	TransaccionForm transacForm;
	
	List<ClienteForm> clientes;
	List<AdquiridoForm> adquiridos;
	List<CuotaForm> cuotas;
	List<PlanForm> planes;
	String novedad;

	public TransaccionForm getTransacForm() {
		return transacForm;
	}


	public void setTransacForm(TransaccionForm transacForm) {
		this.transacForm = transacForm;
	}

	void Consumir() {
	}
	
	public String getUrlServicio() {
		return this.getWebService().getUrl();
	}

	public void setUrlServicio(String urlServicio) {
		this.getWebService().setUrl(urlServicio);
		this.urlServicio = urlServicio;
	}
	
	public Servicio getWebService() {
		
		return webService;
	}

	public void setWebService(Servicio webService) {
		this.webService = webService;
	}

	public String getIdConcesionaria() {
		return idConcesionaria;
	}
	public void setIdConcesionaria(String idConcesionaria) {
		this.idConcesionaria = idConcesionaria;
	}
	public String getNomConcesionaria() {
		return nomConcesionaria;
	}
	public void setNomConcesionaria(String nomConcesionaria) {
		this.getWebService().setNomConcesionaria(nomConcesionaria);
		this.nomConcesionaria = nomConcesionaria;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getUltimaActualizacion() {
		return ultimaActualizacion;
	}
	public void setUltimaActualizacion(String ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	public String getCantDiasCaducidad() {
		return cantDiasCaducidad;
	}
	public void setCantDiasCaducidad(String cantDiasCaducidad) {
		this.cantDiasCaducidad = cantDiasCaducidad;
	}
	public String getCodTecnologia() {
		return codTecnologia;
	}
	public void setCodTecnologia(String codTecnologia) {
		this.codTecnologia = codTecnologia;
	}

	@Override
	public String toString() {
		return "ConcesionariaForm [idConcesionaria=" + idConcesionaria + ", nomConcesionaria=" + nomConcesionaria
				+ ", cuit=" + cuit + ", email=" + email + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", ultimaActualizacion=" + ultimaActualizacion + ", cantDiasCaducidad=" + cantDiasCaducidad
				+ ", webService=" + webService + ", urlServicio=" + urlServicio + ", codTecnologia=" + codTecnologia
				+ ", transacForm=" + transacForm + ", clientes=" + clientes + ", adquiridos=" + adquiridos + ", cuotas="
				+ cuotas + ", planes=" + planes + ", novedad=" + novedad + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adquiridos == null) ? 0 : adquiridos.hashCode());
		result = prime * result + ((cantDiasCaducidad == null) ? 0 : cantDiasCaducidad.hashCode());
		result = prime * result + ((clientes == null) ? 0 : clientes.hashCode());
		result = prime * result + ((codTecnologia == null) ? 0 : codTecnologia.hashCode());
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
		result = prime * result + ((cuotas == null) ? 0 : cuotas.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idConcesionaria == null) ? 0 : idConcesionaria.hashCode());
		result = prime * result + ((nomConcesionaria == null) ? 0 : nomConcesionaria.hashCode());
		result = prime * result + ((novedad == null) ? 0 : novedad.hashCode());
		result = prime * result + ((planes == null) ? 0 : planes.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((transacForm == null) ? 0 : transacForm.hashCode());
		result = prime * result + ((ultimaActualizacion == null) ? 0 : ultimaActualizacion.hashCode());
		result = prime * result + ((urlServicio == null) ? 0 : urlServicio.hashCode());
		result = prime * result + ((webService == null) ? 0 : webService.hashCode());
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
		ConcesionariaForm other = (ConcesionariaForm) obj;
		if (adquiridos == null) {
			if (other.adquiridos != null)
				return false;
		} else if (!adquiridos.equals(other.adquiridos))
			return false;
		if (cantDiasCaducidad == null) {
			if (other.cantDiasCaducidad != null)
				return false;
		} else if (!cantDiasCaducidad.equals(other.cantDiasCaducidad))
			return false;
		if (clientes == null) {
			if (other.clientes != null)
				return false;
		} else if (!clientes.equals(other.clientes))
			return false;
		if (codTecnologia == null) {
			if (other.codTecnologia != null)
				return false;
		} else if (!codTecnologia.equals(other.codTecnologia))
			return false;
		if (cuit == null) {
			if (other.cuit != null)
				return false;
		} else if (!cuit.equals(other.cuit))
			return false;
		if (cuotas == null) {
			if (other.cuotas != null)
				return false;
		} else if (!cuotas.equals(other.cuotas))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idConcesionaria == null) {
			if (other.idConcesionaria != null)
				return false;
		} else if (!idConcesionaria.equals(other.idConcesionaria))
			return false;
		if (nomConcesionaria == null) {
			if (other.nomConcesionaria != null)
				return false;
		} else if (!nomConcesionaria.equals(other.nomConcesionaria))
			return false;
		if (novedad == null) {
			if (other.novedad != null)
				return false;
		} else if (!novedad.equals(other.novedad))
			return false;
		if (planes == null) {
			if (other.planes != null)
				return false;
		} else if (!planes.equals(other.planes))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (transacForm == null) {
			if (other.transacForm != null)
				return false;
		} else if (!transacForm.equals(other.transacForm))
			return false;
		if (ultimaActualizacion == null) {
			if (other.ultimaActualizacion != null)
				return false;
		} else if (!ultimaActualizacion.equals(other.ultimaActualizacion))
			return false;
		if (urlServicio == null) {
			if (other.urlServicio != null)
				return false;
		} else if (!urlServicio.equals(other.urlServicio))
			return false;
		if (webService == null) {
			if (other.webService != null)
				return false;
		} else if (!webService.equals(other.webService))
			return false;
		return true;
	}

	public List<ClienteForm> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteForm> clientes) {
		this.clientes = clientes;
	}
	public List<AdquiridoForm> getAdquiridos() {
		return adquiridos;
	}
	public void setAdquiridos(List<AdquiridoForm> adquiridos) {
		this.adquiridos = adquiridos;
	}
	public List<CuotaForm> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<CuotaForm> cuotas) {
		this.cuotas = cuotas;
	}
	public List<PlanForm> getPlanes() {
		return planes;
	}
	public void setPlanes(List<PlanForm> planes) {
		this.planes = planes;
	}
	public String getNovedad() {
		return novedad;
	}
	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}
	
	public String getAprobada() {
		return aprobada;
	}

	public void setAprobada(String aprobada) {
		this.aprobada = aprobada;
	}
	
}
