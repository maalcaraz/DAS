package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.LocalDate;
import org.joda.time.Months;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.CuotaBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;


public class MSConcesionariaDao extends DaoImpl{

	private final static Logger LOGGER = Logger.getLogger("ar.edu.ubp.das.src.sorteos.daos.MSConcesionariaDao"); 

	@Override
	public Bean make(ResultSet result) throws SQLException {
		return null;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		// Hacer un if, preguntar por el name e insertar transaccion o concesionaria.
				ConcesionariaBean c = (ConcesionariaBean) bean;
				this.connect();
				if (c.isConsultaPendiente()){
					this.setProcedure("dbo.marcar_consulta_pendiente(?)");
				}
				if (c.isNotificacionPendiente()){
					this.setProcedure("dbo.marcar_notificacion_pendiente(?)");
				}
				this.setParameter(1, c.getIdConcesionaria());
				
				this.executeUpdate();
				this.disconnect();
	}

	@Override
	public void update(Bean bean) throws SQLException {
		/*Aca en el update, no olvidar que hay que insertar e nconcesionaria la fecha de actualizacion
		 * de datos qu viene en la transaccion*/
		
		this.connect();
		
		ConcesionariaBean c = (ConcesionariaBean) bean;
		
		//----------------Actualizar datos de concesionaria ---------------------
		
		
		
		List<PlanBean> planes = c.getPlanes();
		
		this.setProcedure("dbo.insertar_plan(?, ?, ?, ?, ?, ?, ?)");
		
		for (PlanBean p : planes){
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
	
			List<ClienteBean> clientes = c.getClientes();
			this.setProcedure("dbo.insertar_cliente(?, ?, ?, ?, ?, ?)");
			for (ClienteBean cli : clientes){
				
				this.setParameter(1, cli.getDniCliente());
				this.setParameter(2, c.getIdConcesionaria());
				this.setParameter(3, cli.getNomCliente());
				this.setParameter(4, cli.getEdad());
				this.setParameter(5, cli.getDomicilio());
				this.setParameter(6, cli.getEmailCliente());
				this.executeUpdate();
			}
		
		//-----------------------------------------------------------------
		
			List<AdquiridoBean> adquiridos = c.getAdquiridos();
			
			this.setProcedure("dbo.insertar_adquirido(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			for (AdquiridoBean a : adquiridos){
				
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
	
			List<CuotaBean> cuotas = c.getCuotas();
			this.setProcedure("dbo.insertar_cuota(?, ?, ?, ?, ?, ?, ?)");
			
			for (CuotaBean cuo : cuotas){
				
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
		
		
			TransaccionBean transForm = c.getTransacForm();
			
			this.setProcedure("dbo.insertar_transaccion(?, ?, ?, ?, ?)");
			
			this.setParameter(1, transForm.getId_transaccion());
			this.setParameter(2, transForm.getIdConcesionaria());
			this.setParameter(3, transForm.getEstadoTransaccion());
			//ver que hacemos aca por que mensaje respuesta no entra como viene no entra
			//trae todas las tablas
			this.setParameter(4, "Consulta Quincenal");
			this.setParameter(5, transForm.getHoraFechaTransaccion());		
			this.executeUpdate();
			
			
			/*
			 * Seteo Fecha Actualizacion			 * 
			 */
			
			this.setProcedure("dbo.actualizar_ultima_fecha_actualizacion(?, ?)");
			this.setParameter(1, c.getIdConcesionaria());
			
			if ((c.getUltimaActualizacion() != null) ) {
				SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
				Date fechaActualizacionAux = null;
				try {
					fechaActualizacionAux = parser.parse(c.getUltimaActualizacion());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					LOGGER.log(Level.INFO,"[MSConcesionariaDAO] " + e.getMessage());
				}
				java.sql.Date fechaActualizacion = new java.sql.Date(fechaActualizacionAux.getTime());
				
				this.setParameter(2, fechaActualizacion);
			}
			else{
				this.setNull(1, java.sql.Types.DATE);
			}

			this.executeUpdate();
			
		
		
		this.disconnect();
	}
	
	public void updateConsumosPendientes(Bean bean) throws SQLException {
		
		ConcesionariaBean c = (ConcesionariaBean) bean;
		String consulta = (c.isConsultaPendiente() == true) ? "S" : "N";
		String notificacion = (c.isNotificacionPendiente() == true) ? "S" : "N";
		
		
		this.connect();
		
		this.setProcedure("dbo.update_consumos_pendientes(?, ?, ?)");
		this.setParameter(1, c.getIdConcesionaria());
		this.setParameter(2, consulta);
		this.setParameter(3, notificacion);
		
		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public void delete(Bean bean) throws SQLException {
		
		
	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		List<Bean> ret = new LinkedList<Bean>();
		this.connect();
		/* En este caso no las vamos a querer separar. 
		 * Hay que poner en cada una de las concesionarias la lista de clientes*/
		
		if (bean == null){
			// Devuelve una lista de concesionarias
			LOGGER.log(Level.INFO,"[ConcDAO]Buscando en la base la lista de concesionarias registradas");
			this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = this.getStatement().executeQuery();
			result.next();
			boolean consultaPendiente = false;
			boolean notificacionPendiente = false;
			while(result.getRow() > 0) {
				try{
					ConcesionariaBean f = new ConcesionariaBean(result.getString("cod_tecnologia"));
					f.setIdConcesionaria(result.getString("id_concesionaria"));
					f.setNomConcesionaria(result.getString("nombre_concesionaria"));
					f.setCuit(result.getString("cuit"));
					f.setEmail(result.getString("email"));
					f.setDireccion(result.getString("direccion"));
					f.setTelefono(result.getString("telefono"));
					f.setCantDiasCaducidad(result.getString("cant_dias_caducidad"));
					f.getWebService().setUrl(result.getString("url_servicio"));
					f.setCodTecnologia(result.getString("cod_tecnologia"));
					f.setUltimaActualizacion(result.getString("ultima_actualizacion"));
					f.setAprobada(result.getString("aprobada"));
					
					consultaPendiente = (result.getString("consulta_pendiente").equals("S")) ? true : false;
					notificacionPendiente = (result.getString("notificacion_pendiente").equals("S")) ? true : false;
					f.setConsultaPendiente(consultaPendiente);
					f.setNotificacionPendiente(notificacionPendiente);
					ret.add(f);
				}
				catch(Exception ex){
					LOGGER.log(Level.INFO,"[ConcDAO]Error en la consulta de concesionarias registradas. Mensaje: "+ex.getMessage());
				}
				result.next();
			}
		}
		else  {
			
			LOGGER.log(Level.INFO,"[ConcDAO]Buscando en la base la lista de participantes del sorteo");
			ConcesionariaBean concesionaria = (ConcesionariaBean) bean;
			this.setProcedure("dbo.get_participantes(?, ?, ?)");
			this.setParameter(1, concesionaria.getIdConcesionaria());
			this.setParameter(2, 36); // Maximo
			this.setParameter(3, 24); // Minimo
			ResultSet result = this.getStatement().executeQuery();
			result.next();
			while(result.getRow() > 0) {
				try{
					ParticipanteBean cli = new ParticipanteBean();
					cli.setIdConcesionaria(result.getString("id_concesionaria"));
					cli.setDniCliente(result.getString("dni_cliente"));
					/*
					 * Calculamos cuantos meses pasaron desde que el cliente compro el plan con la diferencia
					 * entre fecha_compra_plan y la fecha actual. Si la cantidad de meses que pasaron es mayor
					 * a las cuotas pagas entonces el cliente no esta al dia
					 */
					
					LocalDate fechaCompraPlan = LocalDate.parse(result.getString("fecha_compra_plan"));
					LocalDate fechaActual = LocalDate.now();

					int mesesDesdeQueComproPlan = Months.monthsBetween(fechaCompraPlan, fechaActual).getMonths();
					int cuotasPagas = Integer.parseInt(result.getString("cuotas_pagas"));

					if(mesesDesdeQueComproPlan <= cuotasPagas){
						ret.add(cli);
					}
					cli.setApellidoNombre(result.getString("apellido_nombre"));
					cli.setIdPlan(result.getString("id_plan"));
					ret.add(cli);
				}
				catch(Exception ex){
					LOGGER.log(Level.INFO, ex.getMessage());
				}
				result.next();
			}
		}
		this.disconnect();
		return ret;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		
		return false;
	}

}
