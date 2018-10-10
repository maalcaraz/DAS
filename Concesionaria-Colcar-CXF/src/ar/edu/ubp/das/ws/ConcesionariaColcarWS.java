package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "ConcesionariaColcarWSPort", serviceName = "ConcesionariaColcarWSService")
public class ConcesionariaColcarWS {

		@WebMethod(operationName = "ejemplo", action = "urn:Ejemplo")
		public String ejemplo (){
			String ret = "CXF Funciona";
			return ret;
		}
	
		@WebMethod(operationName = "getClientes", action = "urn:GetClientes")
		public String getClientes(@WebParam(name = "arg0") String idPortal) throws Exception {
			
			System.out.println("----------------------------------------\n\n\t POST \n");
			System.out.println("\n -->  Obtener clientes no envia parametros");
			System.out.println("\n\n----------------------------------------\n\n");
			
			String idConcesionaria = "Colcar256";
			Date horaFechaTransaccion = new Date();		
			long lnMilisegundos = horaFechaTransaccion.getTime();
			Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
			String idTransaccion = "GC-"+horaFechaTransaccion.hashCode(); 
			System.out.println("----------------------------------------\n\n\t OBTENER DATOS DE CLIENTES\n");
			System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
			System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
	        Gson gson = new Gson();
			String respuestaServicio = null;
			
			TransaccionBean transaccion = new TransaccionBean();
			String stringRespuesta = "";
	        
			transaccion.setIdTransaccion(idTransaccion);
			transaccion.setIdConcesionaria(idConcesionaria);
			transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
			try
			{
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
			catch ( SQLException ex ) {
				transaccion.setEstadoTransaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
			}
			System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
	        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
	        System.out.println("\n\n----------------------------------------");
			
			respuestaServicio = gson.toJson(transaccion);
			return respuestaServicio;
		}
		
		@WebMethod(operationName = "notificarGanador", action = "urn:NotificarGanador")
		public String notificarGanador( @WebParam(name = "id_portal") String idPortal,
									    @WebParam(name = "id_concesionaria") String idConcesionaria, 
									    @WebParam(name = "dni_cliente") String dniCliente, 
									    @WebParam(name = "nombre_apellido") String nombreApellido,
									    @WebParam(name = "id_plan") String idPlan,
									    @WebParam(name = "fecha_sorteo") String fechaSorteo) throws Exception {
			System.out.println("----------------\n\n\t POST -> NOTIFICAR GANADOR \n");
			System.out.println("\n -->  IdPortal: "+idPortal);
			System.out.println("\n -->  IdConcesionaria: "+idConcesionaria);
			System.out.println("\n -->  DniCliente: "+dniCliente);
			System.out.println("\n -->  Nombre y Apellido: "+nombreApellido);
			System.out.println("\n -->  IdPlan: "+idPlan);
			System.out.println("\n -->  Fecha de Sorteo: "+ fechaSorteo);
			System.out.println("\n\n----------------------------------------\n\n");
			
			Date horaFechaTransaccion = new Date();
			long lnMilisegundos = horaFechaTransaccion.getTime();
			Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
			
			String idTransaccion = "NG-"+horaFechaTransaccion.hashCode(); 
			System.out.println("----------------------------------------\n\n\t NOTIFICAR GANADOR\n");
			System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
			System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
			
			String mensajeRespuesta = "";
	        Gson gson = new Gson();
	        String respuestaServicio = null;
	        TransaccionBean transaccion = new TransaccionBean();
	        transaccion.setIdTransaccion(idTransaccion);
	        transaccion.setIdConcesionaria(idConcesionaria);
	        transaccion.setHoraFechaTransaccion(sqlTimestamp.toString());
	        
	        try {			
	        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
	        	AdquiridoBean adquirido = new AdquiridoBean();
	        	adquirido.setDniCliente(dniCliente);
	        	adquirido.setFechaSorteado(fechaSorteo);
	        	adquirido.setIdPlan(idPlan);
	        	
				/*
				 * Si el ganador es un cliente de esta concesionaria, 
				 * actualiza valores en la tabla Clientes
				 * */
				if (idConcesionaria.contains("Colcar")){
					dao.update(adquirido);
					mensajeRespuesta = "Se ha cancelado la cuenta del cliente ganador del sorteo";
				}
				else{
					/*
					 * Si el ganador es un cliente de otra concesionaria, 
					 * crea una entrada en la tabla Novedades
					 * */
	        		String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
	        		ConcesionariaBean concesionaria = new ConcesionariaBean();
	        		concesionaria.setNovedad(novedad);
	        		dao.insert(concesionaria);
	        		mensajeRespuesta = "Se ha insertado una entrada en la tabla novedades";
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
	        return respuestaServicio;
		}
		
		@WebMethod(operationName = "verificarCancelado", action = "urn:VerificarCancelado")
		public String verificarCancelado(@WebParam(name = "id_portal") String idPortal, 
										 @WebParam(name = "dni_cliente") String dniCliente, 
										 @WebParam(name = "id_plan") String idPlan) throws Exception  {
			System.out.println("----------------------------------------\n\n\t POST \n");
			System.out.println("\n -->  IdPortal: "+idPortal);
			System.out.println("\n -->  DniCliente: "+dniCliente);
			System.out.println("\n -->  IdPlan: "+idPlan);
			System.out.println("\n\n----------------------------------------\n\n");
			
			/*----------------- Esta operacion retorna lo siguiente: ----------------*/
			Date horaFechaTransaccion = new Date();
			long lnMilisegundos = horaFechaTransaccion.getTime();
			Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);
			String idTransaccion = "VC-"+horaFechaTransaccion.hashCode(); 
			System.out.println("----------------------------------------\n\n\t NOTIFICAR GANADOR\n");
			System.out.println("\n -->  Fecha: "+ horaFechaTransaccion.toString());
			System.out.println("\n -->  IdTransaccion: "+ idTransaccion);
	    	String mensajeRespuesta = "";
	    	String idConcesionaria = "Colcar256";
	        
	        Gson gson = new Gson();
	        String respuestaServicio = null;
	        TransaccionBean transaccion = new TransaccionBean();
	        transaccion.setIdTransaccion(idTransaccion);
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
	        	transaccion.setEstadoTransaccion("Failed");
	        	transaccion.setMensajeRespuesta(ex.getMessage());
	        }
	        System.out.println("\n -->  Estado Transaccion: "+ transaccion.getEstadoTransaccion());
	        System.out.println("\n -->  Mensaje Respuesta: "+ transaccion.getMensajeRespuesta());
	        System.out.println("\n\n----------------------------------------");
	        
	        respuestaServicio = gson.toJson(transaccion);
        	return respuestaServicio;
		}
}
