package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.ClienteBean;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "ConcesionariaColcarWSPort", serviceName = "ConcesionariaColcarWSService")
public class ConcesionariaColcarWS {

		@WebMethod(operationName = "getClientes", action = "urn:GetClientes")
		public String getClientes() throws Exception {
			
			List<Bean> clientes = new LinkedList<Bean>();
			
			try
			{
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				clientes = dao.select();
				Gson gson = new Gson();
				String json = gson.toJson(clientes);
				System.out.println(json);
				return json;
			} 
			catch ( SQLException error ) {
				throw new Exception(error.getMessage());
			}
		}
		
		@WebMethod(operationName = "notificarGanador", action = "urn:NotificarGanador")
		public String notificarGanador(@WebParam(name = "id_concesionaria") String idConcesionaria, 
									   @WebParam(name = "dni_cliente") String dniCliente, 
									   @WebParam(name = "nombre_apellido") String nombreApellido,
									   @WebParam(name = "email_cliente") String emailCliente,
									   @WebParam(name = "fecha_sorteo") String fechaSorteo) throws Exception {
			try {
			
				MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				ClienteBean e = new ClienteBean();
				e.setDniCliente(dniCliente);
				e.setNomCliente(nombreApellido);
				e.setFechaSorteo(fechaSorteo);
			
				/*Si el ganador es un cliente de esta concesionaria, actualiza valores en la tabla Clientes*/
				if (idConcesionaria.equals("Colcar")){
					dao.update(e);
					System.out.println("Entrando a concesionaria");
				}
				else{ 
					/*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
					String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
					dao.insert(novedad);
					// Hay que programarlo todavia.
				}
				String mensajeRespuesta = "Notificacion exitosa";
				return mensajeRespuesta;
			}
			catch(SQLException ex) {
				throw new Exception(ex.getMessage());
			}
		}
		
		@WebMethod(operationName = "verificarCancelado", action = "urn:VerificarCancelado")
		public String verificarCancelado(@WebParam(name = "dni_cliente") String dniCliente, 
										 @WebParam(name = "id_plan") String idPlan) throws Exception  {
	        try {
	        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
	        	ClienteBean e = new ClienteBean();
	        	e.setDniCliente(dniCliente);     
	        	e.setIdPlan(idPlan);
	        	String mensajeRespuesta = ((dao.valid(e) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
	        	return mensajeRespuesta;
	        }
	        catch(SQLException ex) {
	        	throw new Exception(ex.getMessage());
	        }
		}
}
