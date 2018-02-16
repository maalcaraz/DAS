package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.estados.daos.MSEstadosCuentasDao;

@Path("/ejemplo") // Esto es lo que se agrega al path del REST configurado
@Produces(MediaType.APPLICATION_JSON) 
public class PruebaRestResource {

	 @POST  public Response funcionEjemplo( ) {
		 String ret = "hola rest";
		 return Response.status( Response.Status.OK ).entity( ret ).build();
	 }
	 
		@Path("/getClientes")
		@POST//tenia un arroba de mas aca que lo saque
		//Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response getClientes(/*@FormParam("solicitante") String solicitante*/) {

			List<DynaActionForm> clientes = new LinkedList<DynaActionForm>();
			
			try {
				
				MSEstadosCuentasDao dao = (MSEstadosCuentasDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das.src.chexa" );
				
				DynaActionForm daf = new DynaActionForm();
				//daf.setItem("solicitante", solicitante);
				
				clientes = dao.select(daf);
				
				LinkedList<Map<String,Object>> clientesForJson = new LinkedList<Map<String,Object>>();
				
				for( DynaActionForm c : clientes ) {
					clientesForJson.add(c.getItems());
				}
				
				Gson gson = new GsonBuilder().create();
				
				return Response.status( Response.Status.OK ).entity( gson.toJson(clientesForJson) ).build();
				
			} catch ( SQLException error ) {

	    	    return Response.status( Response.Status.BAD_REQUEST ).entity( "Error al obtener datos" ).build();
			}
		}
	
}
