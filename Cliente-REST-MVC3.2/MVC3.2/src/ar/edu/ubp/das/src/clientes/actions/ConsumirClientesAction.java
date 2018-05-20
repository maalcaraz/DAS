package ar.edu.ubp.das.src.clientes.actions;

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
import ar.edu.ubp.das.src.clientes.daos.MSClientesDao;
import ar.edu.ubp.das.src.clientes.form.AdquiridoForm;
import ar.edu.ubp.das.src.clientes.form.ClienteForm;
import ar.edu.ubp.das.src.clientes.form.CuotaForm;
import ar.edu.ubp.das.src.clientes.form.PlanForm;
import ar.edu.ubp.das.src.clientes.form.TransaccionForm;

public class ConsumirClientesAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		String servicio = (request.getParameter("servicio")); 
		
		String s = "http://localhost:8080/Concesionaria-"+servicio+"-REST/rest/"+servicio+"/getClientes";
		System.out.println(s);
		
		try{
			URI uri = URI.create(s);            
			HttpPost req = new HttpPost();
			         req.setURI(uri);
			
			HttpClient client = HttpClientBuilder.create().build();	       
			HttpResponse resp = client.execute(req);
			HttpEntity responseEntity = resp.getEntity();
			StatusLine responseStatus = resp.getStatusLine();
			String restResp = EntityUtils.toString(responseEntity);	
			
			Gson gson = new Gson();
			
			TransaccionForm transaccionForm = gson.fromJson(restResp, new TypeToken<TransaccionForm>(){}.getType());
			String idConcesionaria = transaccionForm.getIdConcesionaria();
			String listaRetorno[] = transaccionForm.getRetorno().split("],");
			String strClientes = listaRetorno[0] + "]";
			LinkedList<ClienteForm> clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteForm>>(){}.getType() );
			/* Se almacena lo obtenido en la BD local del portal */
			MSClientesDao clientesDAO = new MSClientesDao();
			
			
			gson = new Gson();
			String strPlanes = listaRetorno[1] + "]";
			LinkedList<PlanForm> planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanForm>>(){}.getType() );
			
			gson = new Gson();
			String strAdquiridos = listaRetorno[2] + "]";
			LinkedList<AdquiridoForm> adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoForm>>(){}.getType() );
			
			gson = new Gson();
			
			LinkedList<CuotaForm> cuotas = gson.fromJson(listaRetorno[3], new TypeToken<LinkedList<CuotaForm>>(){}.getType() );
			
			
			
			
			
			
		}
		catch(Exception ex){
			
		}
		
		
		return mapping.getForwardByName("success");
	}

}
