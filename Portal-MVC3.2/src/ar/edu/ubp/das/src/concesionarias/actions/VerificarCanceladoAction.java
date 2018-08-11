package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import ar.edu.ubp.das.src.ganadores.daos.MSGanadoresDao;

public class VerificarCanceladoAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		
		try {
			System.out.println("Llegamos al action de verificar");
				String idPortal = "PORTALGOB";
				/*En base al idConcesionaria, hay que recuperar el ws de esa concesionaria y consumir el servicio*/
				/*String idConcesionaria = request.getParameter("idConcesionaria");
				String dniCliente = request.getParameter("dniCliente");
				String idPlan = request.getParameter("idPlan");*/
				String restResp = "";
				
				MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ganadores");
				LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Ganadores.select(null);
				System.out.println(forms);
				String dniCliente = forms.get(0).getItem("dni_cliente");
				String idPlan = forms.get(0).getItem("id_plan");
				String idConcesionaria = forms.get(0).getItem("id_concesionaria");
						
				List <NameValuePair> parameters = new ArrayList <NameValuePair>();
				parameters.add(new BasicNameValuePair("id_portal" , idPortal));
				parameters.add(new BasicNameValuePair("dni_cliente" , dniCliente));
		      	parameters.add(new BasicNameValuePair("id_plan" , idPlan));
		      	System.out.println("Idconcesionaria: " +idConcesionaria);
		      	MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
				
		      	
				/*Falta hacer una consulta a la base de datos local,
				 * obtener el ultimo ganador
				 * preguntar a la concesionaria a la que pertenece si lo cancelo o no*/
				
				List<DynaActionForm> formsC =  Concesionaria.select(null);
				for (DynaActionForm f : formsC){
					ConcesionariaForm c = (ConcesionariaForm) f;
					System.out.println("Verificando cancelado en la concesionaria "+ idConcesionaria);
					if (c.getIdConcesionaria().equals(idConcesionaria)){
						System.out.println("Verificando cancelado en la concesionaria "+ idConcesionaria);
						restResp = c.getWebService().Consumir("verificarCancelado", parameters);
					}
				}
				request.setAttribute("respuesta", restResp);

				return mapping.getForwardByName("success");
			}
	        catch(Exception ex) {
	        	response.setStatus(400);
	        	request.setAttribute("respuesta", ex.getMessage());

				return mapping.getForwardByName("success");
	        }
	}

}
