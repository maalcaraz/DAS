package ar.edu.ubp.das.src.main;

import java.sql.SQLException;
import java.util.ArrayList;
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
	 */
	public boolean verificarCancelado(){
		
		//Accion de verificar cancelado
				
				String restResp = "";
				boolean response = false;
				//idportal harcodeado por ahora 
				String idPortal = "PORTALGOB";
				
				try {
					MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
					/*
					 *  Ganadores.select devuelve una lista con solo el primer elemento 
					 */
					LinkedList<Bean> listaUltimoGanador = (LinkedList<Bean>) Ganadores.select(null);
					
					/*
					String dniCliente = listaUltimoGanador.get(0).getItem("dni_cliente");
					String idPlan = listaUltimoGanador.get(0).getItem("id_plan");
					String idConcesionaria = listaUltimoGanador.get(0).getItem("id_concesionaria");
					*/
					
					AdquiridoBean adqBean = (AdquiridoBean) listaUltimoGanador.get(0);
					
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
							System.out.println("Verificando cancelado en la concesionaria " + concAdqBean);
							restResp = concesionaria.getWebService().Consumir("verificarCancelado", parameters);
						}

					}
					
					if(restResp.equals("{Cancelado: SI}")){
						response = true;
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}

		
		return response;
	}
	
	/*
	 * Chequea ultima fecha de actualizacion de informacion de concesionarias
	 * en caso negativo obtiene los datos y actualiza BD de sorteo.
	 * Devuelve lista con de participantes para sorteo
	 */
	public List<Bean> consultaConcesionarias(){
		
		List<Bean> participantesC = null;
		
try {
			
			System.out.println("Arranco el programa...");
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			List<Bean> listadoConcesionarias = Concesionaria.select(null);
			List<ParticipanteBean> participantesSorteo = new LinkedList<ParticipanteBean>();
			Gson gson = new Gson();
			System.out.println("Entrando al for de concesionarias...");
			for (Bean c : listadoConcesionarias ){
				ConcesionariaBean concesionaria = (ConcesionariaBean) c;
				int ultimaActualizacion = Integer.parseInt(concesionaria.getUltimaActualizacion());
				
				LinkedList<ClienteBean> clientes;
				LinkedList<PlanBean> planes;
				LinkedList<AdquiridoBean> adquiridos;
				LinkedList<CuotaBean> cuotas;
				System.out.println("Entrando al if de actualizacion de datos");
				if ( concesionaria.getUltimaActualizacion() == null ||  ultimaActualizacion > 15){
					System.out.println("Hay que actualizar los datos");
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
		 				System.out.println("El presente sorteo se guarda como pendiente");
		 			}
				}
				else{
					System.out.println("No hay que actualizar los datos");
					/*No hace falta actualizar los datos*/
					
				}
				participantesC = Concesionaria.select(c);
				for (Bean b : participantesC){
					ParticipanteBean p = (ParticipanteBean) b;
					participantesSorteo.add(p);
				}
			}
		}
		catch(Exception ex){
			System.out.println("Fallando!");
			System.out.println(ex.getMessage());
		}
		
		return participantesC;
		
	}

	
	public SorteoBean consultarPendientes(){
		
		List<Bean> pendientes = null;
		SorteoBean sorteoPorEjecutar =  null;
		
		try {
			
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			pendientes = sorteo.select(null);
			
			/*
			 * Devuelve el sorteo mas viejo para el cual pendiente se encuentra como S y tomamos el primero del array.
			 */	
			if(pendientes != null && !pendientes.isEmpty()){
				sorteoPorEjecutar =  (SorteoBean) pendientes.get(0);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		}

		return sorteoPorEjecutar;
		
	}
	
	

}
