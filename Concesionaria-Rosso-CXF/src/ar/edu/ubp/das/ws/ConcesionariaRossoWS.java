package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.Date;
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
	

			stringRespuesta = jsonClientes +","+ jsonPlanes +","+ jsonAdquiridos +","+ jsonCuotas;
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
	
	@WebMethod(operationName = "notificarGanador", action = "urn:NotificarGanador")
	public String notificarGanador(@WebParam(name = "id_portal") String idPortal,
								   @WebParam(name = "id_concesionaria") String idConcesionaria, 
								   @WebParam(name = "dni_cliente") String dniCliente, 
								   @WebParam(name = "nombre_apellido") String nombreApellido,
								   @WebParam(name = "id_plan") String idPlan,
								   @WebParam(name = "fecha_sorteo") String fechaSorteo) throws Exception {        
        /*----------------- Esta operacion retorna lo siguiente: ----------------*/
		String idTransaccion = "12345"; // definir en que momento y lugar se determina esto.
		String mensajeRespuesta = "";
		Date horaFechaTransaccion = new Date();
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(idTransaccion);
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
			if (idConcesionaria.equals("Rosso")){
				dao.update(cliente, adquirido);
				mensajeRespuesta="Se ha cancelado la cuenta del cliente ganador del sorteo";
			}
			else{ 
				/*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
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
			//return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        	transaccion.setEstado_transaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        	respuestaServicio = gson.toJson(transaccion);
		}
		
		return respuestaServicio;
	}
	
	@WebMethod(operationName = "verificarCancelado", action = "urn:VerificarCancelado")
	public String verificarCancelado(@WebParam(name = "dni_cliente") String dniCliente, 
									 @WebParam(name = "id_plan") String idPlan) throws Exception {
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		String id_transaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String mensajeRespuesta = "";
    	String idConcesionaria = "Colcar";
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
        
        return mensajeRespuesta;
	}
}
