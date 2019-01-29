package ar.edu.ubp.das.src.main;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.CuotaBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.sorteos.daos.MSGanadoresDao;
import ar.edu.ubp.das.src.sorteos.daos.MSParticipantesDao;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;

public class OperacionesSorteo {
	public static String idPortal = "PORTALGOB";
	public OperacionesSorteo(){
		
	}
	/*
	 * Obtiene ganador de el ultimo sorteo y pregunta en concesionaria
	 * correspondiente si se cancelo.
	 * 
	 */
	public String verificarCancelado(ParticipanteBean ganador){ /* Por que esta operacion retorna un adquirido? */
		System.out.println("[OpsSorteo]--------------->VERIFICAR CANCELADO");
				String restResp = "";
				String idPortal = "PORTALGOB";
				/*
				 * Por ahora, idPortal esta hardcodeado
				 */
				
				Gson gson = new Gson();
				List<ConcesionariaBean> concesionarias = obtenerConcesionarias(null);
				
				System.out.println("\t[OpsSorteo]El ganador a verificar es: "+ganador.toString());
				List <NameValuePair> parameters = new ArrayList <NameValuePair>();
				parameters.add(new BasicNameValuePair("id_portal" , idPortal));
				parameters.add(new BasicNameValuePair("dni_cliente" , ganador.getDniCliente()));
		      	parameters.add(new BasicNameValuePair("id_plan" , ganador.getIdPlan()));
		      	
				String concAdqBean = ganador.getIdConcesionaria();
				
				if ( concesionarias != null){ 
					for (ConcesionariaBean c : concesionarias ){
						if (c.getIdConcesionaria().equals(concAdqBean)){
							System.out.println("\t[Ops Sorteo]Verificando cancelado en la concesionaria " + concAdqBean);
							try{
								restResp = c.getWebService().Consumir("verificarCancelado", parameters);
								TransaccionBean transaccion = gson.fromJson(restResp, new TypeToken<TransaccionBean>(){}.getType());
								String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
								restResp = listaRetorno[0];
							}
							catch(Exception ex){
								System.out.println("[OpsSorteo]No se pudo realizar la conexion con la concesionaria para ejecutar la operacion. Error: "+ ex.getMessage());
								
								restResp = null;
							}
						}
					}
				}
				else{
					System.out.println("\t[OpsSorteo]Aun no hay ganadores registrados. No hay que verificar cancelacion");
				}
		return restResp;
	}
	
	
	public boolean notificarGanador(ParticipanteBean ganador){
		String respuesta = "";
		String fechaParametro;
		MailSender mailSender = new MailSender();
		List<ConcesionariaBean> concesionarias = obtenerConcesionarias(null);
		System.out.println("\t[Ops Sorteo]Los datos que vienen del sorteo son: ");
		System.out.println("\n\tDni ganador: " + ganador.getDniCliente() +"- Fecha Sorteo: "+ ganador.getFechaSorteo());
		try {
			List <NameValuePair> parameters = new ArrayList <NameValuePair>();
			parameters.add(new BasicNameValuePair("id_portal" , idPortal));
			parameters.add(new BasicNameValuePair("id_concesionaria" , ganador.getIdConcesionaria()));
			parameters.add(new BasicNameValuePair("dni_cliente" , ganador.getDniCliente()));
			parameters.add(new BasicNameValuePair("id_plan" , ganador.getIdPlan()));

			parameters.add(new BasicNameValuePair("fecha_sorteo" , ganador.getFechaSorteo()));
	      	
	      	/*
	      	 * Obtencion desde la BD local de la lista de concesionarias registradas
	      	 */
	      	
	      	if (concesionarias.isEmpty()){
	      		System.out.println("\t[OpsSorteo]Aun no hay concesionarias registradas...");
	      	}
	      	else {
	      		for (ConcesionariaBean c : concesionarias ){
	      			
					if (c.getAprobada().equals("S")){
						System.out.println("\t[OpsSorteo]Notificando concesionaria: "+c.getNomConcesionaria());
						respuesta = c.getWebService().Consumir("notificarGanador", parameters);
						Gson gson = new Gson();
						TransaccionBean t = gson.fromJson(respuesta, new TypeToken<TransaccionBean>(){}.getType());						
						if (t.getEstadoTransaccion().equals("Failed")){
							/*
							 * Se setea el sorteo como pendiente
							 */
							System.out.println("\t[OpsSorteo]Fallo la notificacion de la concesionaria");
							return false;
						}
						else{
							
							System.out.println("\t[OpsSorteo]Exito la notificacion de la concesionaria. Deberiamos mandar mail al cliente");
							//Comento por hora el envio de mail. Seba.
							/*
							MSGanadoresDao GanadoresDao;
							try {
								GanadoresDao = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
								
								
								 // Envio un adquirido Bean aunque es Ganadores Dao por necesito
								 //enviar el id de concesionaria
								 //
								List<Bean> clientes = GanadoresDao.select(ganador);
								ClienteBean clienteGanador = (ClienteBean)clientes.get(0);
								
								
								
								
								//mailSender.envioMailNotificacion(clienteGanador.getDniCliente(), clienteGanador.getNomCliente(), clienteGanador.getEmailCliente());
							} catch (SQLException e) {
								e.printStackTrace();
								System.out.println("\t[OpsSorteo]Fallo la obtencion de datos para enviar mail");
							}
							*/
							
						}
					}
				}
	      	}
		}
		catch(RuntimeException ex ){
			System.out.println("\t[Ops Sorteo]No se pudo notificar a todas las concesionarias. Mensaje: " + ex.getMessage());
			return false;
		}
		return true;
	}
	
	/*
	 * Chequea ultima fecha de actualizacion de informacion de concesionarias
	 * en caso negativo realiza el consumo pertinente y actualiza BD de sorteo.
	 */
	public boolean consultaQuincenal(){
		/*
		 * Intenta actualizar los datos de las concesionarias mediante el consumo de la operacion getClientes()
		 * retorna false si quedaron concesionarias pendientes
		 */
		Gson gson = new Gson();
		System.out.println("\t[Ops Sorteo]Consulta de Concesionarias");
		boolean status = true;
		try {
			List<ConcesionariaBean> concesionarias = obtenerConcesionarias(null);
			LinkedList<ClienteBean> clientes;
			LinkedList<PlanBean> planes;
			LinkedList<AdquiridoBean> adquiridos;
			LinkedList<CuotaBean> cuotas;
			
			//List<ParticipanteBean> participantesSorteo = new LinkedList<ParticipanteBean>();
			
			for (ConcesionariaBean concesionaria : concesionarias ){
				
				if (concesionaria.getAprobada().equals("S") && (concesionaria.isConsultaPendiente())){
					System.out.println("\t[OpsSorteo] Ultima actualizacion de la concesionaria: " + concesionaria.getUltimaActualizacion());
					
					int dias = diferenciasDeFechas(concesionaria.getUltimaActualizacion());
					System.out.println("\t[Ops Sorteo]Dias desde la ultima actualizacion: "+dias);
					
					
					int diasCaducidad = Integer.parseInt(concesionaria.getCantDiasCaducidad());
					System.out.println("\t[Ops Sorteo]Entrando al if de actualizacion de datos");
					
					if ( (concesionaria.getUltimaActualizacion() == null) ||  (dias > diasCaducidad) || (concesionaria.isConsultaPendiente())){
						
						System.out.println("\t[Ops Sorteo]Consulta Quincenal ==> Hay que actualizar los datos de "+ concesionaria.getNomConcesionaria());
						List <NameValuePair> parameters = new ArrayList <NameValuePair>();
						parameters.add(new BasicNameValuePair("id_portal" , "PORTALGOB"));
						
						try {
						
							String restResp = concesionaria.getWebService().Consumir("getClientes", parameters);
							TransaccionBean transaccion = gson.fromJson(restResp, new TypeToken<TransaccionBean>(){}.getType());
							
							String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
							/*Listado de Clientes*/
							String strClientes = listaRetorno[0] + "]";
							clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteBean>>(){}.getType() );
							/*Listado de Planes*/
							String strPlanes = listaRetorno[1] + "]";
							planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanBean>>(){}.getType() );
							/*Listado de Aquiridos*/
							String strAdquiridos = listaRetorno[2] + "]";
							adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoBean>>(){}.getType() );
							/*Listado de Cuotas*/
							String strCuotas = listaRetorno[3];
							cuotas = gson.fromJson(strCuotas, new TypeToken<LinkedList<CuotaBean>>(){}.getType() );
														
							Date fechaHoy = new Date();
							SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");

							concesionaria.setClientes(clientes);
							concesionaria.setAdquiridos(adquiridos);
							concesionaria.setCuotas(cuotas);
							concesionaria.setPlanes(planes);
							concesionaria.setTransacForm(transaccion);
							concesionaria.setUltimaActualizacion(parser.format(fechaHoy));
							concesionaria.setConsultaPendiente(false);
							insertarConcesionarias(concesionaria); 
							
						 }
			 			catch (Exception ex){
			 				System.out.println("\t[Ops Sorteo]No se pudo realizar el consumo. El presente sorteo se guarda como pendiente");
			 				// setear consultapendiente de concesionaria como false.
							concesionaria.setConsultaPendiente(true);
			 				status = false;
			 			}
						
						updateConsumosPendientes(concesionaria);
					}
					else{
						System.out.println("\t[Ops Sorteo]No hay que actualizar los datos");
					}
				}
			}
		}
		catch(RuntimeException ex ){
			System.out.println("\t[Ops Sorteo]No se pudo realizar la consulta en la BD. Mensaje: "+ ex.getMessage());
			status = false;
		}
		return status;
	}
	
	public SorteoBean consultarSorteosPendientes(){
		List<Bean> pendientes = null;
		SorteoBean sorteoPorEjecutar =  null;
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			pendientes = sorteo.select(null);
			
			if (pendientes != null && pendientes.isEmpty()){
				System.out.println("\t[Ops Sorteo]No existen sorteos pendientes registrados");
			}
			/*
			 * Devuelve el sorteo mas viejo para el cual pendiente se encuentra como S y tomamos el primero del array.
			 */	
			if(pendientes != null && !pendientes.isEmpty()){
				sorteoPorEjecutar =  (SorteoBean) pendientes.get(0);
			}
		} 
		catch (SQLException e) {
			System.out.println("\t[Ops Sorteo]Error en consulta de sorteos pendientes: "+e.getMessage());
		}
		return sorteoPorEjecutar;
	}
	
	public SorteoBean hoyEsSorteo(){
		
		List<Bean> sorteosHoy = null;
		SorteoBean sorteoPorEjecutar =  null;
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			sorteosHoy = sorteo.obtenerSorteoActual();
			
			/*
			 * Devuelve el sorteo mas viejo para el cual pendiente se encuentra como S y tomamos el primero del array.
			 */	
			if(sorteosHoy != null && !sorteosHoy.isEmpty()){
				sorteoPorEjecutar =  (SorteoBean) sorteosHoy.get(0);
			}
		} 
		catch (SQLException e) {
			e.getMessage();
			System.out.println("\t[Ops Sorteo] Error en checkeo si hoy es sorteo: " + e.getMessage());
		}
		System.out.println("\t[OpsSorteo]Sorteo Por ejecutar: " + sorteoPorEjecutar);
		return sorteoPorEjecutar;
	}
	
	public void cambiarValorPendienteSorteo(SorteoBean pendiente, String idRazon, boolean marcarPendiente){
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			
			if(marcarPendiente == true){
				if (pendiente.getFechaEjecucion()!=null){
					if (pendiente.getFechaNotificacion() != null){
						System.out.println("[OpsSorteo]Se pudo notificar con exito");
					}
					else{
						System.out.println("[OpsSorteo]No se pudo realizar la notificacion con exito");
					}
					// si la de ejecucion es nula, obligatoriamente la de notificacion es nula.
					
				}
				else{
					System.out.println("[OpsSorteo]No se pudo ejecutar con exito");
					pendiente.setFechaEjecucion(null);
				}
				
				System.out.println("\t[OpsSorteo]Entrando a marcar como pendiente");
				pendiente.setPendiente("S");
				pendiente.setRazon(idRazon);
			}
			else{
				pendiente.setPendiente("N");
				System.out.println("\t[OpsSorteo]Entrando a marcar como NO pendiente");
				System.out.println("\t[OpsSorteo]formato de la fecha de sorteo: "+pendiente.getFechaSorteado());
				pendiente.setRazon(idRazon);
			}
			
			System.out.println("\t[Ops Sorteo]Sorteo antes del update: "+ pendiente.getIdSorteo() + pendiente.getFechaSorteado());
			sorteo.update(pendiente);
			// hay que terminar de verificar con este try catch que hacer cuando las cosas salen mal
		}
		catch (SQLException ex){
			System.out.println("\t[Ops Sorteo]Hubo un error al cambiar el valor pendiente del sorteo. Mensaje: "+ex.getMessage());
		}
	}
	
	public static synchronized int diferenciasDeFechas(String fechaUltimaActualizacion) {

		Date fechaHoy = new Date();
		Date ultimaActualizacion = null;
		
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date;
			date = parser.parse(fechaUltimaActualizacion);
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		    String formattedDate = formatter.format(date);
		    System.out.println("\t[Ops Sorteo]Fecha actualizacion formateada: "+ formattedDate);
		    ultimaActualizacion = formatter.parse(formattedDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
        long fechaInicialMs = ultimaActualizacion.getTime();
        long fechaFinalMs = fechaHoy.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
	
	
	public List<ParticipanteBean> obtenerGanadores (){
		try{
			/*
			 *  Ganadores.select() devuelve una lista de ganadores del sorteo 
			 */
			MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
			List<Bean> g = Ganadores.select(null);
			List<ParticipanteBean> ganadores = new LinkedList<ParticipanteBean>();
			
			for (Bean b : g){
				ParticipanteBean ad = (ParticipanteBean) b;
				ganadores.add(ad);
			}
			return ganadores;
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron obtener ganadores. Error: "+ ex.getMessage());
			return null;
		}
	}
	public List<ConcesionariaBean> obtenerConcesionarias(ConcesionariaBean c){
		List<ConcesionariaBean> concesionarias = new LinkedList<ConcesionariaBean>();
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			List<Bean> aux = Concesionaria.select(c);
			
			
			for (Bean conc : aux){
				ConcesionariaBean concesionaria = (ConcesionariaBean) conc;
				concesionarias.add(concesionaria);
			}
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron obtener las concesionarias");
			return null;
		}
		return concesionarias;
	}
	
	public void insertarConcesionarias(ConcesionariaBean c){
		
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			Concesionaria.update(c);
			
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron insertar los datos de la concesionaria "+ c.getNomConcesionaria());
		}
	}
	
	
	public String setearRazon (String operacion, int intentos){

		LinkedList<NameValuePair> nvp = new LinkedList<NameValuePair>();
		nvp.add(new BasicNameValuePair("operacion", operacion));
		nvp.add(new BasicNameValuePair("intentos", Integer.toString(intentos)));
		System.out.println("[Main]Razon de dejar el sorteo como pendiente: "+nvp);
		Gson gson = new Gson();
		
		return gson.toJson(nvp);
	}

	public LinkedList<ParticipanteBean> seleccionarParticipantes(){
		
		try {
			MSParticipantesDao Participantes = (MSParticipantesDao)DaoFactory.getDao("Participantes", "ar.edu.ubp.das.src.sorteos");
			
			LinkedList<ParticipanteBean> p = new LinkedList<ParticipanteBean>();
			List<Bean> aux = Participantes.select(null);
			for(int i = 0; i < aux.size(); i++){
				ParticipanteBean participante = (ParticipanteBean) aux.get(i);
				p.add(participante);
			}	
			return p;
		} 
		catch (SQLException e) {
			System.out.println("[OpsSorteo]No se pudieron obtener los participantes del sorteo");
			return null;
		}
	}
	
	public void setearParticipantes(SorteoBean sorteo){
		try {
			MSParticipantesDao Participantes = (MSParticipantesDao)DaoFactory.getDao("Participantes", "ar.edu.ubp.das.src.sorteos");
			Participantes.insert(sorteo);
		}
		catch(Exception ex){
			System.out.println("\t[OpsSorteo]No se pudieron insertar los participantes para el sorteo");
		}
	}
	
	boolean registrarGanador (ParticipanteBean g){
		try{
			MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
			
			Ganadores.insert(g);
		}
		catch(SQLException ex){
			return false;
		}
		return true;
	}

	public void updateConsumosPendientes(ConcesionariaBean c){
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			Concesionaria.updateConsumosPendientes(c);
			
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron insertar los datos de la concesionaria");
		}
	}

}	
