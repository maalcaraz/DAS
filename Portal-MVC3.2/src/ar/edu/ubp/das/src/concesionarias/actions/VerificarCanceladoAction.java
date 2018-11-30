package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.ganadores.daos.MSGanadoresDao;
import ar.edu.ubp.das.src.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.src.portal.forms.TransaccionForm;

public class VerificarCanceladoAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		
		try {
			    System.out.println("[Portal - VerificarCanceladoAction]Llegamos al action de verificar");
				String idPortal = "PORTALGOB";
				/*En base al idConcesionaria, hay que recuperar el ws de esa concesionaria y consumir el servicio*/
				/*String idConcesionaria = request.getParameter("idConcesionaria");*/
				String dniCliente = request.getParameter("dniVerificar");
				String idPlan = request.getParameter("idPlan");
				String restResp = "";
				Gson gson = new Gson();
				
				MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ganadores");
				LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Ganadores.select(null);
                
                if (forms.isEmpty()){
					restResp = "Aun no hay ganadores registrados";
				}
				else{
					DynaActionForm g = forms.get(0);
                    // DNI CLIENTE E ID PLAN TENEMOS QUE USAR QUE PUSIMOS EN EL FORM!
					/*String dniCliente = g.getItem("dniCliente"); // forms.get(0).getItem("dni_cliente");
					String idPlan = g.getItem("idPlan");//forms.get(0).getItem("id_plan");*/
					String idConcesionaria = g.getItem("idConcesionaria"); //forms.get(0).getItem("concesionaria");
                    String nombreConcesionaria = forms.get(0).getItem("nombreConcesionaria");
					
					System.out.println("[VerificarCanceladoAction]Ultimo ganador: "+ dniCliente + " - " + idPlan);
							
					List <NameValuePair> parameters = new ArrayList <NameValuePair>();
					parameters.add(new BasicNameValuePair("id_portal" , idPortal));
					parameters.add(new BasicNameValuePair("dni_cliente" , dniCliente));
			      	parameters.add(new BasicNameValuePair("id_plan" , idPlan));
			      	System.out.println("[VerificarCanceladoAction]Idconcesionaria: " + idConcesionaria);
			      	MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
					
			      	
					List<DynaActionForm> formsC =  Concesionaria.select(null);
					for (DynaActionForm f : formsC){
						ConcesionariaForm c = (ConcesionariaForm) f;
						if (c.getIdConcesionaria().equals(idConcesionaria)){
							System.out.println("[VerificarCanceladoAction]Verificando cancelado en la concesionaria "+ c.getNomConcesionaria());
							restResp = c.getWebService().Consumir("verificarCancelado", parameters);
						}
					}
				}
				
				request.setAttribute("consumo", restResp);			

				return mapping.getForwardByName("success");
			}
	        catch(Exception ex) {
	        	//response.setStatus(400);
	        	request.setAttribute("error", ex.getMessage());
				return mapping.getForwardByName("failure");
	        }
	}

}
