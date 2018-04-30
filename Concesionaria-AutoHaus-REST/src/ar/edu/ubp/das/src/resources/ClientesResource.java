package ar.edu.ubp.das.src.resources;
import java.sql.SQLException;
import java.util.LinkedList;
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
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.PlanBean;

@Path("/autohaus")
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
			List<Bean> clientes = new LinkedList<Bean>();
			try {
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				clientes = dao.select();
				
				Gson gson = new Gson();
				String json = gson.toJson(clientes);
				
				System.out.println(json);
				return Response.status( Response.Status.OK ).entity(json).build();
			}
			catch ( SQLException error ) {
	    	    return Response.status( Response.Status.BAD_REQUEST ).entity( error.getMessage() ).build();
			}
		}
	
	@Path("/notificarGanador")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response notificarGanador(@FormParam("id_concesionaria") String idConcesionaria,
									@FormParam("dni_cliente") String dniCliente, 
									@FormParam("nombre_apellido") String nombreApellido,
									@FormParam("email_cliente") String emailCliente,
									@FormParam("fecha_sorteo") String fechaSorteo) {
        try {
        	
        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	
        	ClienteBean e = new ClienteBean();
        	e.setDniCliente(dniCliente);
        	e.setNomCliente(nombreApellido);
        	e.setFechaSorteo(fechaSorteo);
        	
        	/*Si el ganador es un cliente de esta concesionaria, actualiza valores en la tabla Clientes*/
        	if (idConcesionaria.equals("Montironi")){
        		dao.update(e);
        		System.out.println("Entrando a concesionaria");
        	}
        	else{ /*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
        		
        		String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
        		dao.insert(novedad);
        		// Hay que programarlo todavia.
        	}
        	
        	String mensajeRespuesta = "Notificacion exitosa";
        	return Response.status(Response.Status.OK).entity(mensajeRespuesta).build();
        }
        catch(SQLException ex) {
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

        	//String mensajeRespuesta = ((dao.valid(e) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
			String mensajeRespuesta = ((dao.valid2Beans(c,p) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
        	
        	
        	return Response.status(Response.Status.OK).entity(mensajeRespuesta).build();
        }
        catch(SQLException ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
	}
}
