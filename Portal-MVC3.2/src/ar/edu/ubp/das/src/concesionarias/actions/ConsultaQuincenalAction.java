package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.portal.forms.ClienteForm;
import ar.edu.ubp.das.portal.forms.CuotaForm;
import ar.edu.ubp.das.portal.forms.PlanForm;
import ar.edu.ubp.das.portal.forms.TransaccionForm;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class ConsultaQuincenalAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		try {
			System.out.println("Action de consulta quincenal");
			/*
			 * Logica de sesion. Luego de implementarla donde sea necesaria se evaluara removerla a otro paquete
			 * para no duplicar codigo
			 */
			HttpSession session = request.getSession(false);
			
			if(session == null){
				return mapping.getForwardByName("noSession");
			}
			else if(session.getAttribute("usuario") == null)
			{
				session.invalidate();
				return mapping.getForwardByName("noSession");
			}
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Concesionaria.select(null);
			
			Gson gson = new Gson();
			TransaccionForm transaccion = null;
			
			String restResp = "";
			
			for (DynaActionForm f : forms){
				
					
				// Almacenarlas en una lista
				System.out.println("[ConsultaQuincenalAction]Select entrado: " + f.toString());
				ConcesionariaForm c = (ConcesionariaForm) f;
				restResp = "Respuesta de "+ c.getNomConcesionaria() +":";
				System.out.println(restResp);
				restResp = c.getWebService().Consumir("getClientes", null);
				//HACER MANEJO DE ERROR ACA SI ALGUNO DEVOLVIO ERROR
					
				transaccion = gson.fromJson(restResp, new TypeToken<TransaccionForm>(){}.getType());
				
				String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
				/*Listado de Clientes*/
				String strClientes = listaRetorno[0] + "]";
				LinkedList<ClienteForm> clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteForm>>(){}.getType() );
				/*Listado de Planes*/
				String strPlanes = listaRetorno[1] + "]";
				LinkedList<PlanForm> planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanForm>>(){}.getType() );
				/*Listado de Aquiridos*/
				String strAdquiridos = listaRetorno[2] + "]";
				LinkedList<AdquiridoForm> adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoForm>>(){}.getType() );
				/*Listado de Cuotas*/
				String strCuotas = listaRetorno[3];
				LinkedList<CuotaForm> cuotas = gson.fromJson(strCuotas, new TypeToken<LinkedList<CuotaForm>>(){}.getType() );
				
				c.setAdquiridos(adquiridos);
				c.setClientes(clientes);
				c.setCuotas(cuotas);
				c.setPlanes(planes);
				c.setTransacForm(transaccion);
				
				c.setItem("operacion", "insercionDatos");
				Concesionaria.update(c);
				
				request.setAttribute("transaccion", transaccion);
				request.setAttribute("clientes", clientes);
				request.setAttribute("planes", planes);
				request.setAttribute("adquiridos", adquiridos);
				request.setAttribute("cuotas", cuotas);
				
				request.setAttribute("concesionaria", c);
			
				
			}
			return mapping.getForwardByName("success");
		}
		catch(Exception ex){
			String msg = "Error en Consulta Quincenal: "+ ex.getMessage();
			request.setAttribute("error_msg", msg);
			return mapping.getForwardByName("failure");
		}
	}
}
