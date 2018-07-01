package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;

public class ConcesionariaTagleWS {

	public String ejemplo (){
		String ret = "Axis Funciona";
		return ret;
	}
	
	public String getClientes(String idPortal) throws Exception {
		String idConcesionaria = "Tagle";
		Date horaFechaTransaccion = new Date();
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		String idTransaccion = "GC-"+horaFechaTransaccion.hashCode(); 
        
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        String stringRespuesta = "";
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
			
		try
		{
			
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
			List<List<Bean>> lista = dao.selectListBeans();
			String jsonClientes = gson.toJson(lista.get(0));
			gson = new Gson();
			String jsonAdquiridos = gson.toJson(lista.get(1));
			gson = new Gson();
			String jsonPlanes = gson.toJson(lista.get(2));
			gson = new Gson();
			String jsonCuotas = gson.toJson(lista.get(3));
			
			stringRespuesta = jsonClientes + jsonPlanes + jsonAdquiridos + jsonCuotas;
			
			transaccion.setEstado_transaccion("Success");
	        transaccion.setMensajeRespuesta(stringRespuesta);
	        respuestaServicio = gson.toJson(transaccion);
	        System.out.println(respuestaServicio);

		} 
		catch ( SQLException ex ) {
			transaccion.setEstado_transaccion("Failed");
	       	transaccion.setMensajeRespuesta(ex.getMessage());
	       	respuestaServicio = gson.toJson(transaccion);
		}
		
		return respuestaServicio;
	}
	
	
	public String notificarGanador(String idPortal,
								   String idConcesionaria, 
			   					   String dniCliente, 
			   					   String nombreApellido,
			   					   String idPlan,
			   					   String fechaSorteo) throws Exception {
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		Date horaFechaTransaccion = new Date();
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		String idTransaccion = "NG-"+horaFechaTransaccion.hashCode();
    	String mensajeRespuesta = "";
        
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
		
		try {
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ClienteBean cliente = new ClienteBean();
        	AdquiridoBean adquirido = new AdquiridoBean();
        	cliente.setDniCliente(dniCliente);
        	adquirido.setFechaSorteado(fechaSorteo);
        	adquirido.setIdPlan(idPlan);
        	
        	/*Si el ganador es un cliente de esta concesionaria, actualiza valores en la tabla Clientes*/
        	if (idConcesionaria.equals("Tagle")){
        		dao.update(cliente, adquirido);
        		mensajeRespuesta="Se ha cancelado la cuenta del cliente ganador del sorteo";
        	}
        	else{ /*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
        		
        		String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
        		dao.insert(novedad);
        		mensajeRespuesta = "Se ha insertado una entrada en la tabla novedades";
        	}
        	
        	transaccion.setEstado_transaccion("Success");
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        	respuestaServicio = gson.toJson(transaccion);
        	System.out.println(respuestaServicio);
        	
		}
		catch(SQLException ex) {
			transaccion.setEstado_transaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        	respuestaServicio = gson.toJson(transaccion);
		}
		
		return respuestaServicio;
	}
	
	public String verificarCancelado(String idPortal,
									 String dniCliente, 
			 						 String idPlan) throws Exception {
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		Date horaFechaTransaccion = new Date();
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		String idTransaccion = "VC-"+horaFechaTransaccion.hashCode();
    	String mensajeRespuesta = "";
    	String idConcesionaria = "Tagle";
        
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
		
		try {
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ClienteBean cliente = new ClienteBean();
        	PlanBean plan = new PlanBean();
        	cliente.setDniCliente(dniCliente);
        	//chequear por que un cliente puede tener mas de un plan
        	plan.setIdPlan(idPlan);
        	mensajeRespuesta = ((dao.valid2Beans(cliente, plan) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
        	transaccion.setEstado_transaccion("Success");
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        	respuestaServicio = gson.toJson(transaccion);
		}
		catch(SQLException ex) {
			transaccion.setEstado_transaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        	respuestaServicio = gson.toJson(transaccion);
		}
		
		return respuestaServicio;
	}
}
