package ar.edu.ubp.das.src.concesionarias.forms;

import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.portal.forms.ClienteForm;
import ar.edu.ubp.das.portal.forms.CuotaForm;
import ar.edu.ubp.das.portal.forms.PlanForm;
import ar.edu.ubp.das.src.servicios.Servicio;
import ar.edu.ubp.das.src.servicios.ServicioFactory;

public class ConcesionariaForm extends DynaActionForm{

	public ConcesionariaForm(String tipoServicio) throws IllegalAccessException, ClassNotFoundException, Exception {
		
		webService = ServicioFactory.getServicio(tipoServicio);
		
	}
	
	private String idConcesionaria;
	private String nomConcesionaria;
	private String cuit;
	private String email;
	private String direccion;
	private String telefono;
	private String ultimaActualizacion;
	private String cantDiasCaducidad;
	private Servicio webService;
	private String codTecnologia;
	private String aprobada;
	private List<ClienteForm> clientes;
	private List<AdquiridoForm> adquiridos;
	private List<CuotaForm> cuotas;
	private List<PlanForm> planes;
	private String novedad;


	void Consumir() {
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
