package ar.edu.ubp.das.src.concesionarias.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.src.portal.forms.ClienteForm;
import ar.edu.ubp.das.src.portal.forms.CuotaForm;
import ar.edu.ubp.das.src.portal.forms.PlanForm;
import ar.edu.ubp.das.src.portal.forms.TransaccionForm;

public class MSConcesionariaDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// Hacer un if, preguntar por el name e insertar transaccion o concesionaria.
		ConcesionariaForm c = (ConcesionariaForm) form;
		this.connect();
		this.setProcedure("dbo.insertar_concesionaria(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		this.setParameter(1, c.getIdConcesionaria());
		this.setParameter(2, c.getNomConcesionaria());
		this.setParameter(3, c.getCuit());
		this.setParameter(4, c.getEmail());
		this.setParameter(5, c.getDireccion());
		this.setParameter(6, c.getTelefono());
		this.setParameter(7, c.getCantDiasCaducidad());
		this.setParameter(8, c.getWebService().getUrl());
		this.setParameter(9, c.getCodTecnologia());
		this.setParameter(10, c.getAprobada());
		this.executeUpdate();
		this.disconnect();
	}
	
	@Override
	public void update(DynaActionForm form) throws SQLException {
		this.connect();
		
		/* Aprobacion de una concesionaria */ 
		if (form.getItem("operacion").equals("aprobar")){
			this.setProcedure("dbo.aprobar_concesionaria (?)");
			this.setParameter(1, form.getItem("idConcesionaria"));
			this.executeUpdate();
			this.disconnect();
			return;
		}
		if (form.getItem("operacion").equals("configurar")){
			ConcesionariaForm c = (ConcesionariaForm) form;
			this.setProcedure("dbo.reconfigurar_concesionaria(?,?,?,?,?,?,?,?)");
			this.setParameter(1, c.getIdConcesionaria());
			this.setParameter(2, c.getCodTecnologia());
			this.setParameter(3, c.getWebService().getUrl());
			this.setParameter(4, c.getCuit());
			this.setParameter(5, c.getEmail());
			this.setParameter(6, c.getDireccion());
			this.setParameter(7, c.getTelefono());
			this.setParameter(8, Integer.parseInt(c.getCantDiasCaducidad()));
			
			this.executeUpdate();
			this.disconnect();
			return;
		}
		else{
		//----------------Actualizar datos de concesionaria ---------------------
			ConcesionariaForm c = (ConcesionariaForm) form;
			List<PlanForm> planes = c.getPlanes();
			
			this.setProcedure("dbo.insertar_plan(?, ?, ?, ?, ?, ?, ?)");
			
			for (PlanForm p : planes){
				this.setParameter(1, p.getIdPlan());
				this.setParameter(2, p.getDescripcion());
				this.setParameter(3, p.getCant_cuotas());
				this.setParameter(4, p.getEntrega_pactada());
				this.setParameter(5, p.getFinanciacion());
				this.setParameter(6, p.getDuenoPlan());
				this.setParameter(7, c.getIdConcesionaria());
				this.executeUpdate();
			}
		
		//-----------------------------------------------------------------
	
			List<ClienteForm> clientes = c.getClientes();
			this.setProcedure("dbo.insertar_cliente(?, ?, ?, ?, ?, ?)");
			for (ClienteForm cli : clientes){
				
				this.setParameter(1, cli.getDniCliente());
				this.setParameter(2, c.getIdConcesionaria());
				this.setParameter(3, cli.getNomCliente());
				this.setParameter(4, cli.getEdad());
				this.setParameter(5, cli.getDomicilio());
				this.setParameter(6, cli.getEmailCliente());
				this.executeUpdate();
			}
		
		//-----------------------------------------------------------------
		
			List<AdquiridoForm> adquiridos = c.getAdquiridos();
			
			this.setProcedure("dbo.insertar_adquirido(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			for (AdquiridoForm a : adquiridos){
				
				this.setParameter(1, a.getIdPlan());
				this.setParameter(2, a.getDniCliente());
				this.setParameter(3, c.getIdConcesionaria());
				this.setParameter(4, a.getCancelado());
				this.setParameter(5, a.getGanadorSorteo());
				this.setParameter(6, a.getFechaSorteado());
				this.setParameter(7, a.getFechaEntrega());
				this.setParameter(8, a.getNroChasis());
				this.setParameter(9, a.getFechaCompraPlan());
				this.executeUpdate();
			}
		
		//-----------------------------------------------------------------
	
			List<CuotaForm> cuotas = c.getCuotas();
			this.setProcedure("dbo.insertar_cuota(?, ?, ?, ?, ?, ?, ?)");
			
			for (CuotaForm cuo : cuotas){
				
				this.setParameter(1, cuo.getIdCuota());
				this.setParameter(2, cuo.getDniCliente());
				this.setParameter(3, cuo.getIdPlan());
				this.setParameter(4, c.getIdConcesionaria());
				this.setParameter(5, cuo.getImporte());
				this.setParameter(6, cuo.getFechaVencimiento());
				this.setParameter(7, cuo.getPagada());
				this.executeUpdate();
			}
		//----------------------------------------------------------------
		
		
			TransaccionForm transForm = c.getTransacForm();
			
			this.setProcedure("dbo.insertar_transaccion(?, ?, ?, ?, ?)");
			
			this.setParameter(1, transForm.getId_transaccion());
			this.setParameter(2, transForm.getIdConcesionaria());
			this.setParameter(3, transForm.getEstadoTransaccion());
			//ver que hacemos aca por que mensaje respuesta no entra como viene no entra
			//trae todas las tablas
			this.setParameter(4, "Consulta Quincenal");
			this.setParameter(5, transForm.getHoraFechaTransaccion());		
			this.executeUpdate();
			
		
		}
		this.disconnect();
	}
		

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		
		this.connect();
		
		this.setProcedure("dbo.rechazar_concesionaria(?)");
		this.setParameter(1, form.getItem("id_concesionaria"));
		
		this.executeUpdate();
		this.disconnect();
		
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		List<DynaActionForm> ret = new LinkedList<DynaActionForm>();
		this.connect();
		
		if (form == null){
			// Devuelve una lista de concesionarias
			System.out.println("[MSConcesionariaDAO]Buscando lista de concesionarias en bd local");
			this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = this.getStatement().executeQuery();
			result.next();
			while(result.getRow() > 0) {
				try{
					ConcesionariaForm f = new ConcesionariaForm(result.getString("cod_tecnologia"));
					f.setIdConcesionaria(result.getString("id_concesionaria"));
					f.setNomConcesionaria(result.getString("nombre_concesionaria"));
					f.getWebService().setUrl(result.getString("url_servicio"));
					f.setCuit(result.getString("cuit"));
					f.setCodTecnologia(result.getString("cod_tecnologia"));
					f.setDireccion(result.getString("direccion"));
					f.setTelefono(result.getString("telefono"));
					f.setEmail(result.getString("email"));
					f.setCantDiasCaducidad(result.getString("cant_dias_caducidad"));
					f.setUltimaActualizacion(result.getString("ultima_actualizacion"));
					f.setAprobada(result.getString("aprobada"));
					ret.add(f);
				}
				catch(Exception ex){
					System.out.println("[Error en MSConcesionariaDAO]"+ex);
				}
				result.next();
			}
		}
		else {
			// Devuelve una lista de clientes
			System.out.println("[MSConcesionariaDAO]Buscando en bd local la lista de clientes");
			ConcesionariaForm con =  (ConcesionariaForm) form;
			
			this.setProcedure("dbo.get_datos_clientes(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			this.setParameter(1, con.getIdConcesionaria());
			ResultSet result = this.getStatement().executeQuery();
			result.next();

			//List<ClienteForm> clientes = new LinkedList<ClienteForm>(); // esto se usa?

			while(result.getRow() > 0) {
				ClienteForm c = new ClienteForm();
				c.setDniCliente(result.getString("dni_cliente"));
				c.setNomCliente(result.getString("apellido_nombre"));
				c.setEdad(result.getString("edad"));
				c.setDomicilio(result.getString("domicilio"));
				c.setEmailCliente(result.getString("email"));
				c.setItem("idPlan", result.getString("id_plan"));
				c.setItem("nombreConcesionaria", result.getString("nombre_concesionaria"));
				c.setItem("nroChasis", (result.getString("nro_chasis") == null) ? "-" : (result.getString("nro_chasis")));
				c.setItem("fechaEntrega", (result.getString("fecha_entrega") == null) ? "-" : (result.getString("fecha_entrega")));
				c.setItem("cancelado", result.getString("cancelado"));
				c.setItem("ganador", result.getString("ganador_sorteo"));
				c.setItem("cantCuotas", result.getString("cant_cuotas"));
				c.setItem("cuotasPagas", result.getString("cuotas_pagas"));
				c.setItem("cuotasSinPagar", result.getString("cuotas_sin_pagar"));

				//clientes.add(c);
				ret.add(c);
				result.next();
			}
			//con.setClientes(clientes);
		}
		
		this.disconnect();
		return ret;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		
		return false;
	}
}
