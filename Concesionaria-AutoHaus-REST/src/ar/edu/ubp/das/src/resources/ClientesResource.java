package ar.edu.ubp.das.src.resources;
import java.sql.SQLException;
import java.util.Date;
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
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;

@Path("/AutoHaus")
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
		String idConcesionaria = "AH123456";
		Date horaFechaTransaccion = new Date();
		
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		
		
		System.out.println(sqlTimestamp);

		String idTransaccion = "GC-"+horaFechaTransaccion.hashCode(); 
      
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        String stringRespuesta = "";
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
        
        
			try {
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
	        	transaccion.setMensajeRespuesta(stringRespuesta);
	        	respuestaServicio = gson.toJson(transaccion);
			}
			catch (SQLException ex) {
				
				//return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
	        	transaccion.setEstado_transaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
	        	respuestaServicio = gson.toJson(transaccion);
			}
			
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
        	ConcesionariaBean concesionaria = new ConcesionariaBean();
        	ClienteBean cliente = new ClienteBean();
        	List<ClienteBean> clientes = new LinkedList<ClienteBean>();
        	clientes.add(cliente);
        	AdquiridoBean adquirido = new AdquiridoBean();
        	List<AdquiridoBean> adquiridos = new LinkedList<AdquiridoBean>();
        	adquiridos.add(adquirido);
        	cliente.setDniCliente(dniCliente);
        	adquirido.setFechaSorteado(fechaSorteo);
        	adquirido.setIdPlan(idPlan);
        	concesionaria.setClientes(clientes);
        	concesionaria.setAdquiridos(adquiridos);

/*-------- Si el ganador es un cliente de esta concesionaria, actualiza valores en la tabla Clientes y Adquiridos --------*/
        	if (idConcesionaria.equals("AutoHaus")){
        		dao.update(concesionaria);
        		//dao.update(cliente, adquirido);
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
        
        return Response.status(Response.Status.OK).entity(respuestaServicio).build();
	}
	
	@Path("/verificarCancelado")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response verificarCancelado(@FormParam("id_portal") String idPortal,
									   @FormParam("dni_cliente") String dniCliente,
									   @FormParam("id_plan") String idPlan) {
		
		/*----------------- Esta operacion retorna lo siguiente: ----------------*/
		Date horaFechaTransaccion = new Date();
		java.util.Date utilDate = new java.util.Date(); //fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		String idTransaccion = "VC-"+horaFechaTransaccion.hashCode(); 
    	String mensajeRespuesta = "";
    	String idConcesionaria = "AutoHaus";
  
        Gson gson = new Gson();
        String respuestaServicio = null;
        TransaccionBean transaccion = new TransaccionBean();
        
        transaccion.setId_transaccion(idTransaccion);
        transaccion.setIdConcesionaria(idConcesionaria);
        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
        
        try {
        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ConcesionariaBean concesionaria = new ConcesionariaBean();
        	AdquiridoBean adquirido = new AdquiridoBean();
        	/*
        	ClienteBean cliente = new ClienteBean();
        	List<ClienteBean> clientes = new LinkedList<ClienteBean>();
        	PlanBean plan = new PlanBean();
        	List<PlanBean> planes = new LinkedList<PlanBean>();
        	

        	cliente.setDniCliente(dniCliente);
        	plan.setIdPlan(idPlan);
        	clientes.add(cliente);
        	planes.add(plan);
        	
        	concesionaria.setClientes(clientes);
        	concesionaria.setPlanes(planes);
			
			*/
        	adquirido.setDniCliente(dniCliente);
        	adquirido.setIdPlan(idPlan);
			
			//mensajeRespuesta = ((dao.valid2Beans(cliente,plan) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}");
			mensajeRespuesta = ((dao.valid(adquirido) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}");
			
        	transaccion.setEstado_transaccion("Success");
        	transaccion.setMensajeRespuesta(mensajeRespuesta);
        	respuestaServicio = gson.toJson(transaccion);
        	//return Response.status(Response.Status.OK).entity(mensajeRespuesta).build();
        }
        catch(SQLException ex) {
			//return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        	transaccion.setEstado_transaccion("Failed");
        	transaccion.setMensajeRespuesta(ex.getMessage());
        	respuestaServicio = gson.toJson(transaccion);
        }
        
        return Response.status(Response.Status.OK).entity(respuestaServicio).build();
	}
}
