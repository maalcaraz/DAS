package ar.edu.ubp.das.src.beans;

import ar.edu.ubp.das.src.servicios.Servicio;
import ar.edu.ubp.das.src.servicios.ServicioFactory;

public class ConcesionariaBean {
	
	ConcesionariaBean(String tipoServicio) throws IllegalAccessException, ClassNotFoundException, Exception {
		
		webService = ServicioFactory.getServicio(tipoServicio);
		
	}
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
	//TransaccionForm transacForm;
	
	//List<ClienteForm> clientes;
	//List<AdquiridoForm> adquiridos;
	//List<CuotaForm> cuotas;
	//List<PlanForm> planes;
	String novedad;
	String idConcesionaria;
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

	public Servicio getWebService() {
		return webService;
	}

	public void setWebService(Servicio webService) {
		this.webService = webService;
	}

	public String getUrlServicio() {
		return urlServicio;
	}

	public void setUrlServicio(String urlServicio) {
		this.urlServicio = urlServicio;
	}

	public String getCodTecnologia() {
		return codTecnologia;
	}

	public void setCodTecnologia(String codTecnologia) {
		this.codTecnologia = codTecnologia;
	}

	public String getAprobada() {
		return aprobada;
	}

	public void setAprobada(String aprobada) {
		this.aprobada = aprobada;
	}

	public String getNovedad() {
		return novedad;
	}

	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}
}
