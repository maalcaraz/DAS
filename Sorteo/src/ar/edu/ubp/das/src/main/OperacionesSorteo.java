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
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;

public class OperacionesSorteo {
	
	public OperacionesSorteo(){
		
	}
	/*
	 * Obtiene ganador de el ultimo sorteo y pregunta en concesionaria
	 * correspondiente si se cancelo.
	 * 
	 */
	public AdquiridoBean verificarCancelado(){
		
		//Accion de verificar cancelado
				
				String restResp = "";
				boolean response = false;
				//idportal harcodeado por ahora 
				String idPortal = "PORTALGOB";
				AdquiridoBean adqBean = null;
				
				try {
					MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
					/*
					 *  Ganadores.select devuelve una lista con solo el primer elemento 
					 */
					List<Bean> listaUltimoGanador =  Ganadores.select(null);
					
					if (listaUltimoGanador != null){ // asi pasa la primer vuelta
						adqBean = (AdquiridoBean) listaUltimoGanador.get(0);
						
						List <NameValuePair> parameters = new ArrayList <NameValuePair>();
						parameters.add(new BasicNameValuePair("id_portal" , idPortal));
						parameters.add(new BasicNameValuePair("dni_cliente" , adqBean.getDniCliente()));
				      	parameters.add(new BasicNameValuePair("id_plan" , adqBean.getIdPlan()));
						
						MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
						List<Bean> listadoConcesionarias = Concesionaria.select(null);
						
						String concAdqBean = adqBean.getIdConcesionaria();
						
						for (Bean c : listadoConcesionarias ){
							ConcesionariaBean concesionaria = (ConcesionariaBean) c;
							
							// aca tengo que preguntar por idconcesionaria en el if pero adq bean no lo tiene!!!
							
							if (concesionaria.getIdConcesionaria().equals(concAdqBean)){
								System.out.println("[Ops Sorteo]Verificando cancelado en la concesionaria " + concAdqBean);
								restResp = concesionaria.getWebService().Consumir("verificarCancelado", parameters);
							}

						}
						
						if(restResp.equals("{Cancelado: SI}")){
							response = true;
							adqBean.setCancelado("true");
						}
						else
						{
							adqBean.setCancelado("false");
						}
					}
					else{
						System.out.println("[Conc]Aun no hay ganadores registrados. No hay que verificar cancelacion");
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("[Ops Sorteo]Catch clause: "+e.getMessage());
				}
		return adqBean;
	}
	
	
	public void NotificarGanador(AdquiridoBean ganador){
		
		
		String idPortal = "PORTALGOB";
		System.out.println("[Ops Sorteo]Entrando en Informar cancelado pendiente");
		
		try {
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			List<Bean> listadoConcesionarias = Concesionaria.select(null);
			Gson gson = new Gson();
			
			System.out.println("[Ops Sorteo]Entrando a recorrer concesionarias...");
			List <NameValuePair> parameters = new ArrayList <NameValuePair>();
			parameters.add(new BasicNameValuePair("id_portal" , idPortal));
			parameters.add(new BasicNameValuePair("id_concesionaria" , ganador.getIdConcesionaria()));
			//Ver como poner estos dos datos que faltan
			parameters.add(new BasicNameValuePair("nombre_apellido" , ""));
			parameters.add(new BasicNameValuePair("fecha_sorteo" , ""));
	      	parameters.add(new BasicNameValuePair("id_plan" , ganador.getIdPlan()));
	      	
	      	if (listadoConcesionarias.isEmpty()){
	      		System.out.println("[OpsSorteo]Aun no hay concesionarias registradas...");
	      	}
	      	else {
	      		for (Bean c : listadoConcesionarias ){
					ConcesionariaBean concesionaria = (ConcesionariaBean) c;
					System.out.println("[OpsSorteo]Concesionaria: "+concesionaria.getNomConcesionaria());
					String restResp = concesionaria.getWebService().Consumir("notificarGanador", parameters);
					if (restResp.equals("")){
						
					}
				}
	      	}
		}
		catch(RuntimeException | SQLException ex ){
			System.out.println("[Ops Sorteo]No se pudo realizar la consulta en la BD. Mensaje: "+ex.getMessage());
		}
		
	}
	
	/*
	 * Chequea ultima fecha de actualizacion de informacion de concesionarias
	 * en caso negativo obtiene los datos y actualiza BD de sorteo.
	 * Devuelve lista con de participantes para sorteo
	 */
	public List<Bean> consultaConcesionarias(){
		System.out.println("[Ops Sorteo]Entrando en Consulta de Concesionarias");
		List<Bean> participantesC = null;
		
		try {
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			List<Bean> listadoConcesionarias = Concesionaria.select(null);
			List<ParticipanteBean> participantesSorteo = new LinkedList<ParticipanteBean>();
			Gson gson = new Gson();
			
			System.out.println("[Ops Sorteo]En la Consulta Quincenal - Entrando a recorrer concesionarias...");
			for (Bean c : listadoConcesionarias ){
				ConcesionariaBean concesionaria = (ConcesionariaBean) c;
				System.out.println("[OpsSorteo] Ultima actualizacion de la concesionaria: " + concesionaria.getUltimaActualizacion());
				
				int dias = diferenciasDeFechas(concesionaria.getUltimaActualizacion());
				System.out.println("[Ops Sorteo]Dias desde la ultima actualizacion: "+dias);
				
				LinkedList<ClienteBean> clientes;
				LinkedList<PlanBean> planes;
				LinkedList<AdquiridoBean> adquiridos;
				LinkedList<CuotaBean> cuotas;
				System.out.println("[Ops Sorteo]Entrando al if de actualizacion de datos");
				if ( (concesionaria.getUltimaActualizacion() == null) ||  (dias > 15) ){
					System.out.println("[Ops Sorteo]Consulta Quincenal ==> Hay que actualizar los datos");
					try {
						/* Esto hace falta a la hora de preguntar si el sorteo es hoy
						java.util.Date utilDate = new java.util.Date(); //fecha actual
						long lnMilisegundos = utilDate.getTime();
						java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
						concesionaria.setUltimaActualizacion(sqlTimestamp.toString());
						System.out.println("Ultima actualizacion: " + sqlTimestamp.toString() );
						*/
						
						// llamar a consultaQuincenal
						String restResp = concesionaria.getWebService().Consumir("getClientes", null);
					
						
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
						concesionaria.setClientes(clientes);
						concesionaria.setAdquiridos(adquiridos);
						concesionaria.setCuotas(cuotas);
						concesionaria.setPlanes(planes);
						
						
						// Concesionaria.update(concesionaria); //ingresar los datos traidos del servicio a la bd local 
					 }
		 			catch (Exception ex)
		 			{
		 			 	//Guardar sorteo como pendiente (ex.getMessage());
		 			   //return;
		 				System.out.println("[Ops Sorteo]El presente sorteo se guarda como pendiente");
		 			}
				}
				else{
					System.out.println("[Ops Sorteo]No hay que actualizar los datos");
					/*No hace falta actualizar los datos*/
				}
				participantesC = Concesionaria.select(c);
				for (Bean b : participantesC){
					ParticipanteBean p = (ParticipanteBean) b;
					participantesSorteo.add(p);
				}
				if (participantesSorteo.isEmpty()){
					System.out.println("[Ops Sorteo]La concesionaria "+ concesionaria.getIdConcesionaria()+ "no tiene participantes para el sorteo");
				}
			}
		}
		catch(RuntimeException | SQLException ex ){
			System.out.println("[Ops Sorteo]No se pudo realizar la consulta en la BD. Mensaje: "+ ex.getMessage());
		}
		return participantesC;
	}
	
	public SorteoBean consultarPendientes(){
		List<Bean> pendientes = null;
		SorteoBean sorteoPorEjecutar =  null;
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			pendientes = sorteo.select(null);
			
			if (pendientes.isEmpty()){
				System.out.println("[Ops Sorteo]No existen sorteos pendientes registrados");
			}
			/*
			 * Devuelve el sorteo mas viejo para el cual pendiente se encuentra como S y tomamos el primero del array.
			 */	
			if(pendientes != null && !pendientes.isEmpty()){
				sorteoPorEjecutar =  (SorteoBean) pendientes.get(0);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[Ops Sorteo]Error en consulta de sorteos pendientes: "+e.getMessage());
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[Ops Sorteo] Error en checkeo si hoy es sorteo: "+e.getMessage());
		}
		return sorteoPorEjecutar;
		
	}
	
	public void cambiarValorPendienteSorteo(SorteoBean pendiente, String idRazon, boolean marcarPendiente){
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			
			if(marcarPendiente == true){
				pendiente.setPendiente("S");
			}
			else{
				pendiente.setPendiente("N");
			}
			
			System.out.println("[Ops Sorteo]Sorteo antes del update: "+ pendiente.getIdSorteo() + pendiente.getFechaSorteado());
			sorteo.update(pendiente);
			// hay que terminar de verificar con este try catch que hacer cuando las cosas salen mal
		}
		catch (SQLException ex){
			System.out.println("[Ops Sorteo]Hubo un error al cambiar el valor pendiente del sorteo. Mensaje: "+ex.getMessage());
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
		    System.out.println("[Ops Sorteo]Fecha actualizacion formateada: "+ formattedDate);
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
}	
