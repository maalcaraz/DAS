package ar.edu.ubp.das.src.concesionarias.actions;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

public class VerificarCanceladoAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		
		try {
				String idPortal = "PORTALGOB";
				//String idConcesionaria = request.getParameter("idConcesionaria");
				String dniCliente = request.getParameter("dniCliente");//"23432255";
				String idPlan = request.getParameter("idPlan");//pabloalcaraz@gmail.com";
	
				String servicio = "AutoHaus";
						
				List <NameValuePair> nvps = new ArrayList <NameValuePair>();
				nvps.add(new BasicNameValuePair("id_portal" , idPortal));
		      	nvps.add(new BasicNameValuePair("dni_cliente" , dniCliente));
		      	nvps.add(new BasicNameValuePair("id_plan" , idPlan));
	      	
		      	URI uri = URI.create("http://localhost:8080/Concesionaria-"+servicio+"-REST/rest/"+servicio+"/verificarCancelado");
	
		      	HttpPost req = new HttpPost();
		        req.setURI(uri);
		        req.setEntity(new UrlEncodedFormEntity(nvps)); 
				HttpClient client = HttpClientBuilder.create().build();	       
				HttpResponse resp = client.execute(req);
				HttpEntity responseEntity = resp.getEntity();
				StatusLine responseStatus = resp.getStatusLine();
				String restResp = EntityUtils.toString(responseEntity);	
			
				if(responseStatus.getStatusCode() != 200) { 
					throw new RuntimeException(restResp); 
				}
			
			}
	        catch(Exception ex) {
	        	response.setStatus(400);
	        	request.setAttribute("error", ex.getMessage());
	        	
	        }
		return null;
	}

}
