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

public class NotificarGanadorAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		/* En base al proceso de sorteo, aca van a llegar los datos del ganador.
		 * Esos datos se van a enviar a cada una de las concesionarias
		 * */
		
		
		String idPortal = "PORTALGOB";
		String idConcesionaria = request.getParameter("idConcesionaria");
		String dniCliente = request.getParameter("dniCliente");//"23432255";
		String nomCliente = request.getParameter("nombreApellido");//"Pablo Alcaraz";
		String idPlan = request.getParameter("idPlan");//pabloalcaraz@gmail.com";
		String fechaSorteo = request.getParameter("fechaSorteo");//"03-03-18";
	
		List <NameValuePair> parameters = new ArrayList <NameValuePair>();
		parameters.add(new BasicNameValuePair("id_portal", idPortal));
		parameters.add(new BasicNameValuePair("id_concesionaria", idConcesionaria));
		parameters.add(new BasicNameValuePair("dni_cliente" , dniCliente));
		parameters.add(new BasicNameValuePair("nombre_apellido", nomCliente));
		parameters.add(new BasicNameValuePair("id_plan", idPlan));
		parameters.add(new BasicNameValuePair("fecha_sorteo", fechaSorteo));
		System.out.println("[NotificarGanadorAction]Pre try... ");
		try {
	      	// Hay que buscarla en la lista de concesionarias, invocar el webService y devolver el consumo
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			
			
			List<DynaActionForm> forms =  Concesionaria.select(null);
			for (DynaActionForm f : forms){
				String restResp = "";
				ConcesionariaForm c = (ConcesionariaForm) f;
				System.out.println("###############################################################");
				System.out.println("[NotificarGanadorAction]Concesionaria: "+ c.getNomConcesionaria());
				restResp += c.getWebService().Consumir("notificarGanador", parameters);
				System.out.println("[NotificarGanadorAction]Respuesta: "+ restResp);
				f.setItem("mensaje", restResp);
			}
			
			request.setAttribute("consumos", forms);
	       
        }
        catch(Exception ex) {
        	response.setStatus(400);
        	request.setAttribute("error", ex.getMessage());
        	
        }
		return null;
	}

}
