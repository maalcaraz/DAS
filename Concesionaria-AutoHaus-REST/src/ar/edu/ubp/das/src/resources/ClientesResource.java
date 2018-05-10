package ar.edu.ubp.das.src.resources;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;


@Path("/AutoHaus")
@Produces(MediaType.APPLICATION_JSON) 
public class ClientesResource {
	 @Path("/ejemplo")
	 @POST  
	 public Response funcionEjemplo( ) {
		 String ret = "Rest funciona";
		 return Response.status( Response.Status.OK ).entity( ret ).build();
	 }
	 
	@Path("/datosClientes")
	@POST
	//Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getClientes() {
			
		String id_transaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String estado_transaccion = "Failed"; 
    	String mensajeRespuesta = " ";
        Date horaFechaTransaccion = new Date();
			try {
				
				
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				System.out.println("Antes del select");
				List<List<Bean>> lista = dao.selectListBeans();
				System.out.println("Despues del select");
				Gson gson = new Gson();
				
				String jsonClientes = gson.toJson(lista.get(0));
				gson = new Gson();
				String jsonAdquiridos = gson.toJson(lista.get(1));
				gson = new Gson();
				String jsonPlanes = gson.toJson(lista.get(2));
				gson = new Gson();
				String jsonCuotas = gson.toJson(lista.get(3));
		
				String res = jsonClientes + jsonPlanes + jsonAdquiridos + jsonCuotas;

	        	estado_transaccion = "Success";
	        	
				TransaccionBean transaccion = new TransaccionBean();
	        	transaccion.setId_transaccion(id_transaccion);
	        	transaccion.setEstado_transaccion(estado_transaccion);
	        	transaccion.setMensajeRespuesta(mensajeRespuesta);
	        	transaccion.setHoraFechaTransaccion(horaFechaTransaccion.toString());
	        	transaccion.setRetorno(res);
	        	String f = gson.toJson(transaccion);
	        	System.out.println(f);
				
				return Response.status( Response.Status.OK ).entity(f).build();
			}
			catch ( SQLException error ) {
	    	    return Response.status( Response.Status.BAD_REQUEST ).entity( error.getMessage() ).build();
			}
		}
	
	@Path("/notificarGanador")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response notificarGanador(@FormParam("id_portal") String idPortal,
								  	 @FormParam("id_concesionaria") String idConcesionaria,
									 @FormParam("dni_cliente") String dniCliente, 
									 @FormParam("nombre_apellido") String nombreApellido,
									 @FormParam("id_plan") String idPlan,
									 @FormParam("fecha_sorteo") String fechaSorteo) {
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		String id_transaccion = "12345"; // definir en que momento y lugar se determina esto.
    	String estado_transaccion = "Failed"; 
    	String mensajeRespuesta = " ";
        Date horaFechaTransaccion = new Date();
        
    	
        try {
        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ClienteBean cliente = new ClienteBean();
        	AdquiridoBean adquirido = new AdquiridoBean();
        	cliente.setDniCliente(dniCliente);
        	adquirido.setFechaSorteado(fechaSorteo);
        	adquirido.setIdPlan(idPlan);

/*-------- Si el ganador es un cliente de esta concesionaria, actualiza valores en la tabla Clientes y Adquiridos --------*/
        	if (idConcesionaria.equals("AutoHaus")){
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
        	
        	return Response.status(Response.Status.OK).entity(f).build();
        }
        catch(SQLException ex) {
      /*  	estado_transaccion;  ver como devolverlo en la respuesta. Quizas no sea lo mejor que se 
        	devuelva este status como parte de la transaccion.*/
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
	}
	
	@Path("/verificarCancelado")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response verificarCancelado(@FormParam("dni_cliente") String dniCliente,
									   @FormParam("id_plan") String idPlan) {
        try {
        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	
        	ClienteBean c = new ClienteBean();
        	PlanBean p = new PlanBean();
        	c.setDniCliente(dniCliente);
        	//chequear por que un cliente puede tener mas de un plan
			p.setIdPlan(idPlan);
			String mensajeRespuesta = ((dao.valid2Beans(c,p) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
        	return Response.status(Response.Status.OK).entity(mensajeRespuesta).build();
        }
        catch(SQLException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
	}
}
