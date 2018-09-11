package ar.edu.ubp.das.src.beans;

import java.util.List;

import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.servicios.Servicio;
import ar.edu.ubp.das.src.servicios.ServicioFactory;

public class ConcesionariaBean implements Bean{
	
	public ConcesionariaBean(String tipoServicio) throws IllegalAccessException, ClassNotFoundException, Exception {
		
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
	TransaccionBean transacForm;
	List<ClienteBean> clientes;
	List<AdquiridoBean> adquiridos;
	List<CuotaBean> cuotas;
	List<PlanBean> planes;

	String novedad;
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
	public TransaccionBean getTransacForm() {
		return transacForm;
	}

	public void setTransacForm(TransaccionBean transacForm) {
		this.transacForm = transacForm;
	}

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

}
