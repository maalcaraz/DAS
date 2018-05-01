package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.ClienteBean;

public class ConcesionariaTagleWS {

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
			catch ( SQLException ex ) {
				throw new Exception(ex.getMessage());
			}
	}
	
	public String notificarGanador(String idConcesionaria, 
			   					   String dniCliente, 
			   					   String nombreApellido,
			   					   String emailCliente,
			   					   String fechaSorteo) throws Exception {
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
		throw new Exception(ex.getMessage());
		}
	}
	
	public String verificarCancelado(String dniCliente, 
			 						 String idPlan) throws Exception {
		try {
			MSClientesDao dao = (MSClientesDao) DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
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
