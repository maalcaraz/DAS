package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class VerificarCanceladoAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		
		try {
				String idPortal = "PORTALGOB";
				/*En base al idConcesionaria, hay que recuperar el ws de esa concesionaria y consumir el servicio*/
				String idConcesionaria = request.getParameter("idConcesionaria");
				String dniCliente = request.getParameter("dniCliente");
				String idPlan = request.getParameter("idPlan");
				String restResp = "";
				
						
				List <NameValuePair> parameters = new ArrayList <NameValuePair>();
				parameters.add(new BasicNameValuePair("id_portal" , idPortal));
				parameters.add(new BasicNameValuePair("dni_cliente" , dniCliente));
		      	parameters.add(new BasicNameValuePair("id_plan" , idPlan));
	      	
		      	MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
				
				System.out.println("Llegamos al action de verificar");
				/*Falta hacer una consulta a la base de datos local,
				 * obtener el ultimo ganador
				 * preguntar a la concesionaria a la que pertenece si lo cancelo o no*/
				
				List<DynaActionForm> forms =  Concesionaria.select(null);
				for (DynaActionForm f : forms){
					ConcesionariaForm c = (ConcesionariaForm) f;
					if (c.getIdConcesionaria().equals(idConcesionaria)){
						restResp = c.getWebService().Consumir("notificarGanador", parameters);
						
					}
				}
				request.setAttribute("mensaje_respuesta", restResp);
				
			}
	        catch(Exception ex) {
	        	response.setStatus(400);
	        	request.setAttribute("error", ex.getMessage());
	        	
	        }
		return null;
	}

}
