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

public class OpsSorteo {
	
	public OpsSorteo(){
		
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
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
			List<Bean> listadoConcesionarias = Concesionaria.select(null);
			List<ParticipanteBean> participantesSorteo = new LinkedList<ParticipanteBean>();
			Gson gson = new Gson();
			
			for (Bean c : listadoConcesionarias ){
				ConcesionariaBean concesionaria = (ConcesionariaBean) c;
				int ultimaActualizacion = Integer.parseInt(concesionaria.getUltimaActualizacion());
				
				LinkedList<ClienteBean> clientes;
				LinkedList<PlanBean> planes;
				LinkedList<AdquiridoBean> adquiridos;
				LinkedList<CuotaBean> cuotas;
				
				if ( ultimaActualizacion > 15){
					try {
						java.util.Date utilDate = new java.util.Date(); //fecha actual
						long lnMilisegundos = utilDate.getTime();
						java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
						concesionaria.setUltimaActualizacion(sqlTimestamp.toString());
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
						// Concesionaria.update(); //ingresar los datos traidos del servicio a la bd local 
					 }
		 			catch (Exception ex)
		 			{
		 			 	//Guardar sorteo como pendiente (ex.getMessage());
		 			   //return;
		 				System.out.println("El presente sorteo se guarda como pendiente");
		 			}
					
		 			
				}
				else{
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
			System.out.println(ex.getMessage());
		}
		
		return participantesC;
		
	}

	
	public List<Bean> consultarPendientes(){
		
		List<Bean> pendientes = null;
		
		try {
			MSSorteosDao sorteo = (MSSorteosDao)DaoFactory.getDao("Sorteos", "ar.edu.ubp.das.src.sorteos");
			pendientes = sorteo.select(null);
			
			/*
			 * TO DO: Ahora esta devolviendo todos los sorteos que estan como pendiente. En teoria no deberiamos setear mas
			 * de uno como pendiente, pero debemos tener esto en cuenta. Lo cambiamos al procedimiento en BD para devolver
			 * el mas viejo que este como pendiente o lo dejamos como esta y aca tomamos el primero del array?
			 */
			
			SorteoBean sorteoPorEjecutar =  (SorteoBean) pendientes.get(0);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	
		return pendientes;
		
	}
	

}
