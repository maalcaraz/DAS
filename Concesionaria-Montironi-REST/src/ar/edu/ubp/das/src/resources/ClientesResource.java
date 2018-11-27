package ar.edu.ubp.das.src.resources;
import java.sql.SQLException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;


@Path("/Montironi")

@Produces(MediaType.APPLICATION_JSON) 
public class ClientesResource {
	 @Path("/ejemplo")
	 @POST  
	 public Response funcionEjemplo( ) {
		 String ret = "Rest funciona";
		 return Response.status( Response.Status.OK ).entity( ret ).build();
	 }
	 
@Path("/getClientes")
	@POST
	//Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getClientes(String idPortal) {
		
		
		System.out.println("----------------------------------------\n\n\t POST \n");
		System.out.println("\n -->  Obtener clientes no envia parametros");
		System.out.println("\n\n----------------------------------------\n\n");
		
		String idConcesionaria = "AutoHaus1503004614";
		Date horaFechaTransaccion = new Date();
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		System.out.println(sqlTimestamp);
		String idTransaccion = "GC-"+horaFechaTransaccion.hashCode(); 
		System.out.println("----------------------------------------\n\n\t OBTENER DATOS DE CLIENTES\n");
		System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
		System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
        Gson gson = new Gson();
        String respuestaServicio = null;
        
        TransaccionBean transaccion = new TransaccionBean();
        
        
        String stringRespuesta = "";
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
        
        
			try {
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				ConcesionariaBean concesionaria = (ConcesionariaBean) dao.select().get(0);
				
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
			catch (SQLException ex) {
				//return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
	        	transaccion.setEstadoTransaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
			}
			System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
	        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
	        System.out.println("\n\n----------------------------------------");
			respuestaServicio = gson.toJson(transaccion);
			return Response.status( Response.Status.OK ).entity(respuestaServicio).build();
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
		

		System.out.println("----------------------------------------\n\n\t POST \n");
		System.out.println("\n -->  IdPortal: "+idPortal);
		System.out.println("\n -->  IdConcesionaria: "+idConcesionaria);
		System.out.println("\n -->  DniCliente: "+dniCliente);
		System.out.println("\n -->  Nombre y Apellido: "+nombreApellido);
		System.out.println("\n -->  IdPlan: "+idPlan);
		System.out.println("\n -->  Fecha de Sorteo: "+ fechaSorteo);
		System.out.println("\n\n----------------------------------------\n\n");
		
		/*
		 * ----------------- Esta operacion retorna un mensaje indicando el estado de la operacion ----------------
		 * 	*/
		Date horaFechaTransaccion = new Date(); 
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		
		String idTransaccion = "NG-"+horaFechaTransaccion.hashCode(); 
		System.out.println("----------------------------------------\n\n\t NOTIFICAR GANADOR\n");
		System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
		System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
		
    	String mensajeRespuesta = "";
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
    	
        try {
        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	AdquiridoBean adquirido = new AdquiridoBean();
        	adquirido.setDniCliente(dniCliente);
        	adquirido.setFechaSorteado(fechaSorteo);
        	adquirido.setIdPlan(idPlan);

        	/*
        	 * -------- Si el ganador es un cliente de esta concesionaria, 
        	 * 			actualiza valores en la tabla Clientes y Adquiridos --------*/
        	
        	if (idConcesionaria.contains("Montironi")){
        		dao.update(adquirido);
        		mensajeRespuesta = "Se ha cancelado la cuenta del cliente ganador del sorteo";
        	}
        	else{ 
        		/*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
        		String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
        		ConcesionariaBean concesionaria = new ConcesionariaBean();
        		concesionaria.setNovedad(novedad);
        		dao.insert(concesionaria);
        		mensajeRespuesta = "Se ha insertado una entrada en la tabla novedades con el ganador del sorteo";
        	}
        	
        	transaccion.setEstadoTransaccion("Success");
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        }
        catch(SQLException ex) {
        	//return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        	transaccion.setEstadoTransaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        }
        
        
        System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
        System.out.println("\n\n----------------------------------------");
        respuestaServicio = gson.toJson(transaccion);
        return Response.status(Response.Status.OK).entity(respuestaServicio).build();
	}
	
	@Path("/verificarCancelado")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response verificarCancelado(@FormParam("id_portal") String idPortal,
									   @FormParam("dni_cliente") String dniCliente,
									   @FormParam("id_plan") String idPlan) {
		
		System.out.println("----------------------------------------\n\n\t POST \n");
		System.out.println("\n -->  IdPortal: "+idPortal);
		System.out.println("\n -->  DniCliente: "+dniCliente);
		System.out.println("\n -->  IdPlan: "+idPlan);
		System.out.println("\n\n----------------------------------------\n\n");
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		System.out.println(idPortal);
		Date horaFechaTransaccion = new Date();
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		String idTransaccion = "VC-"+horaFechaTransaccion.hashCode(); 
		System.out.println("----------------------------------------\n\n\t NOTIFICAR GANADOR\n");
		System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
		System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
    	String mensajeRespuesta = "";
    	String idConcesionaria = "Montironi";
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        System.out.println("Cliente:" + dniCliente);
        System.out.println("IdPlan: "+idPlan);
        transaccion.setId_transaccion(idTransaccion);
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
			//return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        	transaccion.setEstadoTransaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        }
        
        System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
        System.out.println("\n\n----------------------------------------");
        respuestaServicio = gson.toJson(transaccion);
        return Response.status(Response.Status.OK).entity(respuestaServicio).build();
	}
}
