package ar.edu.ubp.das.src.resources;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.bean.CriterioBean;
import ar.edu.ubp.das.daos.MSEstadosCuentasDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;

@Path("/autohaus")
@Produces(MediaType.APPLICATION_JSON) 
public class EstadosCuentasResource {
	 @Path("/ejemplo")
	 @POST  
	 public Response funcionEjemplo( ) {
		 String ret = "Rest funciona";
		 return Response.status( Response.Status.OK ).entity( ret ).build();
	 }
	 
	@Path("/estadosClientes")
	@POST
	public Response getEstadosClientes() {
			List<Bean> estadosCuentas = new LinkedList<Bean>();
			try {
				MSEstadosCuentasDao dao = (MSEstadosCuentasDao)DaoFactory.getDao( "EstadosCuentas", "ar.edu.ubp.das" );
				estadosCuentas = dao.select();
				return Response.status( Response.Status.OK ).entity(estadosCuentas).build();
			} 
			catch ( SQLException error ) {
	    	    return Response.status( Response.Status.BAD_REQUEST ).entity( error.getMessage() ).build();
			}
		}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response notificarGanador(@FormParam("fecha_sorteo") String fechaSorteo, 
			                  		 @FormParam("dni_ganador") String dniGanador,
			                  		 @FormParam("nombre_apellido") String nombreApellido,
			                  		 @FormParam("nom_concesionaria") String nombreConcesionaria,
			                  		 @FormParam("plan_adquirido") String planAdquirido
			                  		 ) {
		try {
			MSEstadosCuentasDao dao = (MSEstadosCuentasDao)DaoFactory.getDao( "EstadosCuentas", "ar.edu.ubp.das" );
			//estadosCuentas = dao.select();
			return Response.status( Response.Status.OK ).entity(estadosCuentas).build();
		} 
		catch ( SQLException error ) {
    	    return Response.status( Response.Status.BAD_REQUEST ).entity( error.getMessage() ).build();
		}
	}

	//Agregue esto para el PR
}