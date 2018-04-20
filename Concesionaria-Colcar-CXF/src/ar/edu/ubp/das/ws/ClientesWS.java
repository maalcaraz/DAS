package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.ClienteBean;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "ClientesWSPort", serviceName = "ClientesWSService")
public class ClientesWS {

	@WebMethod(operationName = "getCuentasClientes", action = "urn:GetCuentasClientes")
	public List<ClienteBean> getCuentasClientes() throws Exception {
		
		List<Bean> cts = new LinkedList<Bean>();
		List<ClienteBean> cuentas = new LinkedList<ClienteBean>(); 
		try {
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				
			cts = dao.select();
			
			for (Bean b : cts){
				cuentas.add((ClienteBean) b);
			}
			return cuentas;
		} 
		catch ( SQLException error ) {
			throw new Exception(error.getMessage());
		}
	}
	
	@WebMethod(operationName = "notificarGanador", action = "urn:NotificarGanador")
	public String notificarGanador(@WebParam(name = "arg0") String idConcesionaria, 
								   @WebParam(name = "arg1") String dniCliente, 
								   @WebParam(name = "arg2") String nombreApellido,
								   @WebParam(name = "arg3") String emailCliente,
								   @WebParam(name = "arg4") String fechaSorteo) {
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
			else{ /*Si el ganador es un cliente de otra concesionaria, crea una entrada en la tabla Novedades*/
				String novedad = "El ganador del sorteo de la fecha "+ fechaSorteo + " es "+ nombreApellido + " de la concesionaria "+ idConcesionaria;
				dao.insert(novedad);
				// Hay que programarlo todavia.
			}
			String mensajeRespuesta = "Notificacion exitosa";
			return mensajeRespuesta;
		}
		catch(SQLException ex) {
			return ex.getMessage();
		}
	}
	
	@WebMethod(operationName = "verificarCancelado", action = "urn:VerificarCancelado")
	public String verificarCancelado(@WebParam(name = "arg0") String dniCliente) {
        try {
        	MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
        	ClienteBean e = new ClienteBean();
        	e.setDniCliente(dniCliente);        	
        	String mensajeRespuesta = ((dao.valid(e) == true ) ? "{Cancelado: SI}" : "{Cancelado: NO}") ;
        	
        	return mensajeRespuesta;
        }
        catch(SQLException ex) {
			return ex.getMessage();
        }
	}
	
}