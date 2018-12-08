package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;

public class ConcesionariaTagleWS {

	public String ejemplo (){
		System.out.println("----------------------------------------\n\n\t POST \n");
		System.out.println("\n -->  Operacion de ejemplo para probar servicio ");
		System.out.println("\n\n----------------------------------------\n\n");		
		String ret = "";
		try {
			ret = "Axis funciona";
		}
		catch (Exception ex){
			ret = "[ConcesionariaTagleWS]Error en el consumo del servicio: "+ex.getMessage();
		}
        System.out.println("\n\n----------------------------------------");
		System.out.println("\n -->  Mensaje Respuesta: "+ ret);
        System.out.println("\n\n----------------------------------------");
		return ret;
	}
	
	public String getClientes(String idPortal) throws Exception {
		
		String idPortalAprobado = "PORTALGOB";
		String respuestaServicio = null;
		TransaccionBean transaccion = new TransaccionBean();
		String mensajeRespuesta = "";
		Gson gson = new Gson();
		
		if(idPortal.equals(idPortalAprobado)){
			System.out.println("----------------------------------------\n\n\t POST \n");
			System.out.println("\n -->  Obtener clientes no envia parametros");
			System.out.println("\n\n----------------------------------------\n\n");		
			
			String idConcesionaria = "Tagle";
			Date horaFechaTransaccion = new Date();
			Date utilDate = new java.util.Date(); //fecha actual
			long lnMilisegundos = utilDate.getTime();
			Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
			System.out.println(sqlTimestamp);
			String idTransaccion = "GC-"+horaFechaTransaccion.hashCode();
			System.out.println("----------------------------------------\n\n\t OBTENER DATOS DE CLIENTES\n");
			System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
			System.out.println("\n -->  IdTransaccion: "+ idTransaccion);

	        String stringRespuesta = "";
	        
	        transaccion.setIdTransaccion(idTransaccion);
			transaccion.setIdConcesionaria(idConcesionaria);
			transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
				
			try
			{
				PlanBean plan = new PlanBean();
				plan.setDuenoPlan("GOB");
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				ConcesionariaBean concesionaria = (ConcesionariaBean) dao.select(plan).get(0);
			
				String jsonClientes = gson.toJson(concesionaria.getClientes());
				gson = new Gson();
				String jsonAdquiridos = gson.toJson(concesionaria.getAdquiridos());
				gson = new Gson();
				String jsonPlanes = gson.toJson(concesionaria.getPlanes());
				gson = new Gson();
				String jsonCuotas = gson.toJson(concesionaria.getCuotas());
				
				stringRespuesta = jsonClientes +","+ jsonPlanes +","+ jsonAdquiridos +","+ jsonCuotas;

				transaccion.setEstadoTransaccion("Success");
	        	transaccion.setMensajeRespuesta(stringRespuesta);

			} 
			catch ( SQLException ex ) {
				transaccion.setEstadoTransaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
			}
			
			System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
	        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
	        System.out.println("\n\n----------------------------------------");
			
			respuestaServicio = gson.toJson(transaccion);
			
		}else{
			transaccion.setEstadoTransaccion("Failed");
	        transaccion.setMensajeRespuesta("El id provisto no esta aprobado para consumir este servicio. Comunicarse con la concesionaria");
	        respuestaServicio = gson.toJson(transaccion);
		}
		
		return respuestaServicio;
	}
	
	
	public String notificarGanador(String idPortal,
								   String idConcesionaria, 
			   					   String dniCliente, 
			   					   String idPlan,
			   					   String fechaSorteo) throws Exception {
		
		String idPortalAprobado = "PORTALGOB";
		String respuestaServicio = null;
		TransaccionBean transaccion = new TransaccionBean();
		String mensajeRespuesta = "";
		Gson gson = new Gson();
					
		if(idPortal.equals(idPortalAprobado)){
			System.out.println("----------------\n\n\t POST -> NOTIFICAR GANADOR \n");
			System.out.println("\n -->  IdPortal: "+idPortal);
			System.out.println("\n -->  IdConcesionaria: "+idConcesionaria);
			System.out.println("\n -->  DniCliente: "+dniCliente);
			System.out.println("\n -->  IdPlan: "+idPlan);
			System.out.println("\n -->  Fecha de Sorteo: "+ fechaSorteo);
			System.out.println("\n\n----------------------------------------\n\n");
			
			Date horaFechaTransaccion = new Date();
			long lnMilisegundos = horaFechaTransaccion.getTime();
			Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
			
			String idTransaccion = "NG-"+horaFechaTransaccion.hashCode(); 
			System.out.println("----------------------------------------\n\n\t NOTIFICAR GANADOR\n");
			System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
			System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
			
	        transaccion.setIdTransaccion(idTransaccion);
	        transaccion.setIdConcesionaria(idConcesionaria);
	        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
			
			try {
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
	        	AdquiridoBean adquirido = new AdquiridoBean();
	        	adquirido.setDniCliente(dniCliente);
	        	adquirido.setFechaSorteado(fechaSorteo);
	        	adquirido.setIdPlan(idPlan);
	        	
	        	/*
				 * Si el ganador es un cliente de esta concesionaria, 
				 * actualiza valores en la tabla Clientes
				 * */
				if (idConcesionaria.contains("Tagle")){
					dao.update(adquirido);
					mensajeRespuesta = "Se ha cancelado la cuenta del cliente ganador del sorteo";
				}
				else{
					/*
					 * Si el ganador es un cliente de otra concesionaria, 
					 * crea una entrada en la tabla Novedades
					 * */
	        		String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ dniCliente + " de la concesionaria "+ idConcesionaria;
	        		ConcesionariaBean concesionaria = new ConcesionariaBean();
	        		concesionaria.setNovedad(novedad);
	        		dao.insert(concesionaria);
	        		mensajeRespuesta = "Se ha insertado una entrada en la tabla novedades";
				}
				
				transaccion.setEstadoTransaccion("Success");
	        	transaccion.setMensajeRespuesta(mensajeRespuesta);
	        	
			}
			catch(SQLException ex) {
				transaccion.setEstadoTransaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
			}
			
			System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
	        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
	        System.out.println("\n\n----------------------------------------");
	        
	        respuestaServicio = gson.toJson(transaccion);
		}
		else{
			transaccion.setEstadoTransaccion("Failed");
	        transaccion.setMensajeRespuesta("El id provisto no esta aprobado para consumir este servicio. Comunicarse con la concesionaria");
	        respuestaServicio = gson.toJson(transaccion);
		}
		
        return respuestaServicio;
	}
	
	public String verificarCancelado(String idPortal,
									 String dniCliente, 
			 						 String idPlan) throws Exception {
		
		String idPortalAprobado = "PORTALGOB";
		String respuestaServicio = null;
		TransaccionBean transaccion = new TransaccionBean();
		String mensajeRespuesta = "";
		Gson gson = new Gson();
					
		if(idPortal.equals(idPortalAprobado)){
			System.out.println("----------------------------------------\n\n\t POST \n");
			System.out.println("\n -->  IdPortal: "+idPortal);
			System.out.println("\n -->  DniCliente: "+dniCliente);
			System.out.println("\n -->  IdPlan: "+idPlan);
			System.out.println("\n\n----------------------------------------\n\n");
			
			Date horaFechaTransaccion = new Date();
			long lnMilisegundos = horaFechaTransaccion.getTime();
			Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
			String idTransaccion = "VC-"+horaFechaTransaccion.hashCode(); 
			System.out.println("----------------------------------------\n\n\t NOTIFICAR GANADOR\n");
			System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
			System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
	    	String idConcesionaria = "Tagle";
	    	
	        transaccion.setIdTransaccion(idTransaccion);
	        transaccion.setIdConcesionaria(idConcesionaria);
	        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
			
			try {
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
	        	AdquiridoBean adquirido = new AdquiridoBean();

	        	adquirido.setDniCliente(dniCliente);
	        	adquirido.setIdPlan(idPlan);
	        	
	        	mensajeRespuesta = ((dao.valid(adquirido) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}");
	        	
	        	transaccion.setEstadoTransaccion("Success");
	        	transaccion.setMensajeRespuesta(mensajeRespuesta);
	        	
			}
			catch(SQLException ex) {
	        	transaccion.setEstadoTransaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
			}
			
			System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
	        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
	        System.out.println("\n\n----------------------------------------");
	        
	        respuestaServicio = gson.toJson(transaccion);
		}
		else{
			transaccion.setEstadoTransaccion("Failed");
	        transaccion.setMensajeRespuesta("El id provisto no esta aprobado para consumir este servicio. Comunicarse con la concesionaria");
	        respuestaServicio = gson.toJson(transaccion);
		}
		
    	return respuestaServicio;
	}
}
