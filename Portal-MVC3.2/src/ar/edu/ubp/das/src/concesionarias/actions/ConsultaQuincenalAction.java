package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			 /*Aca tenemos que hacer un for y recorrer las concesionarias, y con eso llenar nuestra BD */
			
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Concesionaria.select(null);
			
			Gson gson = new Gson();
			TransaccionForm transaccion = null;
			
			String restResp = "";
			
			for (DynaActionForm f : forms){
				
					
				// Almacenarlas en una lista
				System.out.println("Select entrado: " + f.toString());
				ConcesionariaForm c = (ConcesionariaForm) f;
				restResp = "Respuesta de "+ c.getNomConcesionaria() +":";
				System.out.println(restResp);
				restResp = c.getWebService().Consumir("getClientes", null);
				//HACER MANEJO DE ERROR ACA SI ALGUNO DEVOLVIO ERROR
					
				transaccion = gson.fromJson(restResp, new TypeToken<TransaccionForm>(){}.getType());
				
				String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
	
				String strClientes = listaRetorno[0] + "]";
				LinkedList<ClienteForm> clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteForm>>(){}.getType() );
				
				String strPlanes = listaRetorno[1] + "]";
				
				LinkedList<PlanForm> planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanForm>>(){}.getType() );
				
					
				String strAdquiridos = listaRetorno[2] + "]";
				LinkedList<AdquiridoForm> adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoForm>>(){}.getType() );

				String strCuotas = listaRetorno[3];
				LinkedList<CuotaForm> cuotas = gson.fromJson(strCuotas, new TypeToken<LinkedList<CuotaForm>>(){}.getType() );
				
				c.setAdquiridos(adquiridos);
				c.setClientes(clientes);
				c.setCuotas(cuotas);
				c.setPlanes(planes);
				c.setTransacForm(transaccion);
				
				Concesionaria.update(c);
				request.setAttribute("transaccion", transaccion);
				request.setAttribute("clientes", clientes);
				request.setAttribute("planes", planes);
				request.setAttribute("adquiridos", adquiridos);
				request.setAttribute("cuotas", cuotas);
					
				
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
