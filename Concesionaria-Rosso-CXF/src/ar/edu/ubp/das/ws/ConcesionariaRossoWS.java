package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "ConcesionariaRossoWSPort", serviceName = "ConcesionariaRossoWSService")
public class ConcesionariaRossoWS {

	
	@WebMethod(operationName = "getClientes", action = "urn:GetClientes")
	public String getClientes() throws Exception {
		String idConcesionaria = "Rosso";
		String idTransaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String estadoTransaccion = "Failed"; 
    	String mensajeRespuesta = " ";
        Date horaFechaTransaccion = new Date();
		
		try
		{
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				
			List<List<Bean>> lista = dao.selectListBeans();
			Gson gson = new Gson();
			String jsonClientes = gson.toJson(lista.get(0));
			gson = new Gson();
			String jsonAdquiridos = gson.toJson(lista.get(1));
			gson = new Gson();
			String jsonPlanes = gson.toJson(lista.get(2));
			gson = new Gson();
			String jsonCuotas = gson.toJson(lista.get(3));
	
			String retorno = jsonClientes + jsonPlanes + jsonAdquiridos + jsonCuotas;

        	estadoTransaccion = "Success";
        	
			TransaccionBean transaccion = new TransaccionBean();
        	transaccion.setId_transaccion(idTransaccion);
        	transaccion.setEstado_transaccion(estadoTransaccion);
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        	transaccion.setHoraFechaTransaccion(horaFechaTransaccion.toString());
        	transaccion.setIdConcesionaria(idConcesionaria);
        	transaccion.setRetorno(retorno);
        	String respuestaServicio = gson.toJson(transaccion);
        	System.out.println(respuestaServicio);
        	
        	return respuestaServicio;
		} 
		catch ( SQLException ex ) {
			throw new Exception(ex.getMessage());
		}
	}
	
	@WebMethod(operationName = "notificarGanador", action = "urn:NotificarGanador")
	public String notificarGanador(@WebParam(name = "id_portal") String idPortal,
								   @WebParam(name = "id_concesionaria") String idConcesionaria, 
								   @WebParam(name = "dni_cliente") String dniCliente, 
								   @WebParam(name = "nombre_apellido") String nombreApellido,
								   @WebParam(name = "id_plan") String idPlan,
								   @WebParam(name = "fecha_sorteo") String fechaSorteo) throws Exception {
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		
		String id_transaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String estado_transaccion = "Failed"; // by default
    	String mensajeRespuesta = " ";
        Date horaFechaTransaccion = new Date();
        
		try {
		
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ClienteBean cliente = new ClienteBean();
        	AdquiridoBean adquirido = new AdquiridoBean();
        	cliente.setDniCliente(dniCliente);
        	adquirido.setFechaSorteado(fechaSorteo);
        	adquirido.setIdPlan(idPlan);

        	
        	/*Si el ganador es un cliente de esta concesionaria, actualiza valores en la tabla Clientes*/
			if (idConcesionaria.equals("Colcar")){
				dao.update(cliente, adquirido);
				mensajeRespuesta="Se ha cancelado la cuenta del cliente ganador del sorteo";
			}
			else{ 
				/*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
				String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
				dao.insert(novedad);
				mensajeRespuesta = "Se ha insertado una entrada en la tabla novedades";
			}	

        	estado_transaccion = "Success";
        	
        	Gson gson = new Gson();
        	TransaccionBean transaccion = new TransaccionBean();
        	transaccion.setId_transaccion(id_transaccion);
        	transaccion.setEstado_transaccion(estado_transaccion);
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        	transaccion.setHoraFechaTransaccion(horaFechaTransaccion.toString());
        	String f = gson.toJson(transaccion);
        	System.out.println(f);
        	
        	return f;
		}
		catch(SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	@WebMethod(operationName = "verificarCancelado", action = "urn:VerificarCancelado")
	public String verificarCancelado(@WebParam(name = "arg0") String dniCliente, 
									 @WebParam(name = "arg1") String idPlan) throws Exception {
        try {
	        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
	        	ClienteBean c = new ClienteBean();
	        	PlanBean p = new PlanBean();
	        	c.setDniCliente(dniCliente);     
	        	p.setIdPlan(idPlan);
	        	String mensajeRespuesta = ((dao.valid2Beans(c, p) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
        	return mensajeRespuesta;
        }
        catch(SQLException ex) {
        	throw new Exception(ex.getMessage());
        }
	}
}
