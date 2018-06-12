package ar.edu.ubp.das.src.concesionarias.actions;

import java.net.URI;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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

public class ConsultaQuincenalAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		try {
			
			 /*Aca tenemos que hacer un for y recorrer las concesionarias, y con eso llenar nuestra BD */
			
			
			
			
			String servicio = "AutoHaus";
			
			String s = "http://localhost:8080/Concesionaria-"+servicio+"-REST/rest/"+servicio+"/getClientes";
			System.out.println(s);
			URI uri = URI.create(s);           
			HttpPost req = new HttpPost();
			         req.setURI(uri);
			HttpClient client = HttpClientBuilder.create().build();	       
			HttpResponse resp = client.execute(req);
			HttpEntity responseEntity = resp.getEntity();
			StatusLine responseStatus = resp.getStatusLine();
			String restResp = EntityUtils.toString(responseEntity);	
				
			if(responseStatus.getStatusCode() != 200) {
				request.setAttribute("error", restResp);
				return mapping.getForwardByName("failure");
				//throw new RuntimeException(restResp);
			}
			
			Gson gson = new Gson();
			
			TransaccionForm transaccion = gson.fromJson(restResp, new TypeToken<TransaccionForm>(){}.getType());
			String idConcesionaria = transaccion.getIdConcesionaria();
			
			
			String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");

			String strClientes = listaRetorno[0] + "]";
			LinkedList<ClienteForm> clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteForm>>(){}.getType() );
			
			gson = new Gson();
			String strPlanes = listaRetorno[1] + "]";
			System.out.println(listaRetorno[3]);
			LinkedList<PlanForm> planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanForm>>(){}.getType() );
			gson = new Gson();
			String strAdquiridos = listaRetorno[2] + "]";
			LinkedList<AdquiridoForm> adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoForm>>(){}.getType() );
			gson = new Gson();
			LinkedList<CuotaForm> cuotas = gson.fromJson(listaRetorno[3], new TypeToken<LinkedList<CuotaForm>>(){}.getType() );
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			
			Concesionaria.insertTransacciones(transaccion);
			Concesionaria.insertClientes(clientes, idConcesionaria);
			Concesionaria.insertPlanes(planes);
			Concesionaria.insertAdquiridos(adquiridos, idConcesionaria);
			Concesionaria.insertCuotas(cuotas, idConcesionaria);
			
			
			request.setAttribute("clientes", clientes);
			request.setAttribute("planes", planes);
			request.setAttribute("adquiridos", adquiridos);
			request.setAttribute("cuotas", cuotas);
			
			return mapping.getForwardByName("success");
		}
		catch(Exception ex){
			System.out.println("Error en Consulta Quincenal: "+ ex.getMessage());
			return null;
			
		}
	}

}
