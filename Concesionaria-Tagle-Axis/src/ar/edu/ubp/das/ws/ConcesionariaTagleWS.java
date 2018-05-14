package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
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

	public String getClientes() throws Exception {
		String idConcesionaria = "Tagle";
		String idTransaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String mensajeRespuesta = "";
        Date horaFechaTransaccion = new Date();
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        String stringRespuesta = "";
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(horaFechaTransaccion.toString());
			
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
	        transaccion.setMensajeRespuesta(mensajeRespuesta);
	        transaccion.setRetorno(stringRespuesta);
	        respuestaServicio = gson.toJson(transaccion);
	        System.out.println(respuestaServicio);

		} 
		catch ( SQLException ex ) {
			transaccion.setEstado_transaccion("Failed");
	       	transaccion.setMensajeRespuesta(ex.getMessage());
	       	transaccion.setRetorno("Failed");
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
		String id_transaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String mensajeRespuesta = "";
        Date horaFechaTransaccion = new Date();
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(id_transaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(horaFechaTransaccion.toString());
		
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
		
		return mensajeRespuesta;
	}
	
	public String verificarCancelado(String dniCliente, 
			 						 String idPlan) throws Exception {
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		String id_transaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String mensajeRespuesta = "";
    	String idConcesionaria = "Tagle";
        Date horaFechaTransaccion = new Date();
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(id_transaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(horaFechaTransaccion.toString());
		
		try {
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ClienteBean cliente = new ClienteBean();
        	PlanBean plan = new PlanBean();
        	cliente.setDniCliente(dniCliente);
        	//chequear por que un cliente puede tener mas de un plan
        	plan.setIdPlan(idPlan);
			
        	transaccion.setEstado_transaccion("Success");
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        	respuestaServicio = gson.toJson(transaccion);
		}
		catch(SQLException ex) {
			transaccion.setEstado_transaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        	respuestaServicio = gson.toJson(transaccion);
		}
		
		return mensajeRespuesta;
	}
}
