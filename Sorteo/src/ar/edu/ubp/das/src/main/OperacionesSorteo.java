package ar.edu.ubp.das.src.main;

import java.sql.SQLException;
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
import ar.edu.ubp.das.src.sorteos.daos.MSParticipanteDao;
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
	public AdquiridoBean verificarCancelado(){ /* Por que esta operacion retorna un adquirido? */
		System.out.println("[OpsSorteo]--------------->VERIFICAR CANCELADO");
				String restResp = "";
				String idPortal = "PORTALGOB";
				/*
				 * Por ahora, idPortal esta hardcodeado
				 */
				AdquiridoBean adqBean = null;
				Gson gson = new Gson();
				List<Bean> ganadores = null;
				List<Bean> concesionarias = null;
				
					ganadores = obtenerGanadores();
					
					if (ganadores != null){ // asi pasa la primer vuelta
						adqBean = (AdquiridoBean) ganadores.get(0);
						System.out.println("\t[OpsSorteo]El ganador a verificar es: "+adqBean.toString());
						List <NameValuePair> parameters = new ArrayList <NameValuePair>();
						parameters.add(new BasicNameValuePair("id_portal" , idPortal));
						parameters.add(new BasicNameValuePair("dni_cliente" , adqBean.getDniCliente()));
				      	parameters.add(new BasicNameValuePair("id_plan" , adqBean.getIdPlan()));
						
						String concAdqBean = adqBean.getIdConcesionaria();
						concesionarias = obtenerConcesionarias(null);
						if ( concesionarias != null){
							for (Bean c : concesionarias ){
								ConcesionariaBean concesionaria = (ConcesionariaBean) c;
								// aca tengo que preguntar por idconcesionaria en el if pero adq bean no lo tiene!!!
								if (concesionaria.getAprobada().equals("S")){
									if (concesionaria.getIdConcesionaria().equals(concAdqBean)){
										System.out.println("\t[Ops Sorteo]Verificando cancelado en la concesionaria " + concAdqBean);
										
										try{
											restResp = concesionaria.getWebService().Consumir("verificarCancelado", parameters);
											TransaccionBean transaccion = gson.fromJson(restResp, new TypeToken<TransaccionBean>(){}.getType());
											String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
											if(listaRetorno[0].equals("{Cancelado: SI}")){
												System.out.println("\t[OpsSorteo]El ganador fue cancelado. VerificarCancelado = SI");
												adqBean.setCancelado("true");
											}
											else{
												adqBean.setCancelado("false");
											}
										}
										catch(Exception ex){
											System.out.println("[OpsSorteo]No se pudo realizar la conexion con la concesionaria para ejecutar la operacion. Error: "+ ex.getMessage());
											adqBean.setCancelado("false");
											return null;
										}
									}
								}
							}
						}
					}
					else{
						System.out.println("\t[OpsSorteo]Aun no hay ganadores registrados. No hay que verificar cancelacion");
					}
		return adqBean;
	}
	
	
	public boolean NotificarGanador(AdquiridoBean ganador){
		String respuesta = "";
		MailSender mailSender = new MailSender();
		List<Bean> concesionarias = obtenerConcesionarias(null);
		System.out.println("\t[Ops Sorteo]Los datos que vienen del sorteo son: ");
		System.out.println("\n\tDni ganador: " + ganador.getDniCliente() +"- Fecha Sorteo: "+ ganador.getFechaSorteado());
		try {
			List <NameValuePair> parameters = new ArrayList <NameValuePair>();
			parameters.add(new BasicNameValuePair("id_portal" , idPortal));
			parameters.add(new BasicNameValuePair("id_concesionaria" , ganador.getIdConcesionaria()));
			parameters.add(new BasicNameValuePair("dni_cliente" , ganador.getDniCliente()));
			parameters.add(new BasicNameValuePair("id_plan" , ganador.getIdPlan()));
			parameters.add(new BasicNameValuePair("fecha_sorteo" , ganador.getFechaSorteado()));
	      	
	      	/*
	      	 * Obtencion desde la BD local de la lista de concesionarias registradas
	      	 */
	      	
	      	if (concesionarias.isEmpty()){
	      		System.out.println("\t[OpsSorteo]Aun no hay concesionarias registradas...");
	      	}
	      	else {
	      		for (Bean c : concesionarias ){
					ConcesionariaBean concesionaria = (ConcesionariaBean) c;
					if (concesionaria.getAprobada().equals("S")){
						System.out.println("\t[OpsSorteo]Notificando concesionaria: "+concesionaria.getNomConcesionaria());
						respuesta = concesionaria.getWebService().Consumir("notificarGanador", parameters);
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
							
							MSGanadoresDao GanadoresDao;
							try {
								GanadoresDao = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
								
								/*
								 * Envio un adquirido Bean aunque es Ganadores Dao por necesito
								 * enviar el id de concesionaria
								 */
								List<Bean> clientes = GanadoresDao.select(ganador);
								ClienteBean clienteGanador = (ClienteBean)clientes.get(0);
								mailSender.envioMailNotificacion(clienteGanador.getDniCliente(), clienteGanador.getNomCliente(), clienteGanador.getEmailCliente());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
	      	}
		}
		catch(RuntimeException ex ){
			System.out.println("\t[Ops Sorteo]No se pudo notificar a todas las concesionarias. Mensaje: "+ex.getMessage());
			return false;
		}
		return true;
	}
	
	/*
	 * Chequea ultima fecha de actualizacion de informacion de concesionarias
	 * en caso negativo obtiene los datos y actualiza BD de sorteo.
	 * Devuelve lista con de participantes para sorteo
	 */
	public List<ConcesionariaBean> consultaConcesionarias(){
		System.out.println("\t[Ops Sorteo]Entrando en Consulta de Concesionarias");
		List<Bean> participantesC = null;
		List<Bean> concesionarias = null;
		List<ConcesionariaBean> resultados = new LinkedList<ConcesionariaBean>();
		
		
		try {
			concesionarias = obtenerConcesionarias(null);
			List<ParticipanteBean> participantesSorteo = new LinkedList<ParticipanteBean>();
			Gson gson = new Gson();
			
			System.out.println("\t[Ops Sorteo]En la Consulta Quincenal - Entrando a recorrer concesionarias...");
			for (Bean c : concesionarias ){
				ConcesionariaBean concesionaria = (ConcesionariaBean) c;
				
				if (concesionaria.getAprobada().equals("S")){
					System.out.println("\t[OpsSorteo] Ultima actualizacion de la concesionaria: " + concesionaria.getUltimaActualizacion());
					
					int dias = diferenciasDeFechas(concesionaria.getUltimaActualizacion());
					System.out.println("\t[Ops Sorteo]Dias desde la ultima actualizacion: "+dias);
					
					LinkedList<ClienteBean> clientes;
					LinkedList<PlanBean> planes;
					LinkedList<AdquiridoBean> adquiridos;
					LinkedList<CuotaBean> cuotas;
					int diasCaducidad = Integer.parseInt(concesionaria.getCantDiasCaducidad());
					System.out.println("\t[Ops Sorteo]Entrando al if de actualizacion de datos");
					if ( (concesionaria.getUltimaActualizacion() == null) ||  (dias > diasCaducidad) ){
						System.out.println("\t[Ops Sorteo]Consulta Quincenal ==> Hay que actualizar los datos");
						List <NameValuePair> parameters = new ArrayList <NameValuePair>();
						parameters.add(new BasicNameValuePair("id_portal" , "PORTALGOB"));
						try {
							/* Esto hace falta a la hora de preguntar si el sorteo es hoy
							java.util.Date utilDate = new java.util.Date(); //fecha actual
							long lnMilisegundos = utilDate.getTime();
							java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
							concesionaria.setUltimaActualizacion(sqlTimestamp.toString());
							System.out.println("Ultima actualizacion: " + sqlTimestamp.toString() );
							*/
							
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
							
							// tambien arrancar con la logica de la fechade ultima actualizacion
							
							Date fechaHoy = new Date();
							SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");

							concesionaria.setClientes(clientes);
							concesionaria.setAdquiridos(adquiridos);
							concesionaria.setCuotas(cuotas);
							concesionaria.setPlanes(planes);
							concesionaria.setTransacForm(transaccion);
							concesionaria.setUltimaActualizacion(parser.format(fechaHoy));		
							insertarConcesionarias(concesionaria);
							
							 
						 }
			 			catch (Exception ex){
			 				System.out.println("\t[Ops Sorteo]Pasar a la siguiente consulta");
			 				resultados.add(concesionaria);
			 				continue;
			 			}
					}
					else{
						System.out.println("\t[Ops Sorteo]No hay que actualizar los datos");			
					}
					participantesC = obtenerConcesionarias(concesionaria);
					for (Bean b : participantesC){
						ParticipanteBean p = (ParticipanteBean) b;
						participantesSorteo.add(p);
					}
					if (participantesSorteo.isEmpty()){
						System.out.println("\t[Ops Sorteo]La concesionaria "+ concesionaria.getIdConcesionaria()+ "no tiene participantes para el sorteo");
					}
					// aca habria que hacer un insert del dao participantes
				}
			}
		}
		catch(RuntimeException ex ){
			System.out.println("\t[Ops Sorteo]No se pudo realizar la consulta en la BD. Mensaje: "+ ex.getMessage());
		}
		return resultados;
	}
	
	public SorteoBean consultarPendientes(){
		List<Bean> pendientes = null;
		SorteoBean sorteoPorEjecutar =  null;
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			pendientes = sorteo.select(null);
			
			if (pendientes.isEmpty()){
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
	
	public SorteoBean obtenerSorteoHoy(){
		
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
			e.printStackTrace();
			System.out.println("\t[Ops Sorteo] Error en checkeo si hoy es sorteo: "+e.getMessage());
		}
		System.out.println("\t[OpsSorteo]Sorteo Por ejecutar:"+sorteoPorEjecutar);
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
	
	
	public List<Bean> obtenerGanadores (){
		try{
			/*
			 *  Ganadores.select() devuelve una lista de ganadores del sorteo 
			 */
			MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
			return Ganadores.select(null);
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron obtener ganadores. Error: "+ ex.getMessage());
			return null;
		}
	}
	public List<Bean> obtenerConcesionarias(ConcesionariaBean c){
		
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			return Concesionaria.select(c);
			
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron obtener las concesionarias");
		}
		return null;
	}
	
	public void insertarConcesionarias(ConcesionariaBean c){
		
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			Concesionaria.update(c);
			
		}
		catch(SQLException ex){
			System.out.println("\t[OpsSorteo]No se pudieron insertar los datos de las concesionarias");
		}
	}
	
	
	public String setearRazon (String operacion, int intentos, List<ConcesionariaBean> conPen){
		LinkedList<NameValuePair> nvp = new LinkedList<NameValuePair>();
		nvp.add(new BasicNameValuePair("operacion", operacion));
		nvp.add(new BasicNameValuePair("intentos", Integer.toString(intentos)));
		nvp.add(new BasicNameValuePair("concesionariasPendientes", conPen.toArray().toString()));
		System.out.println("[Main]Razon de dejar el sorteo como pendiente: "+nvp);
		Gson gson = new Gson();
		
		return gson.toJson(nvp);
	}
	
	public List<Bean> obtenerParticipantes(){
		try{
			MSParticipanteDao Participantes = (MSParticipanteDao)DaoFactory.getDao("Participante", "ar.edu.ubp.das.src.sorteos");
			return Participantes.select(null);
		}
		catch(Exception ex){
			return null;
		}
	}
}	
