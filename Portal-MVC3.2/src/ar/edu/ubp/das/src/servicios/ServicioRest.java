package ar.edu.ubp.das.src.servicios;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
//import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ServicioRest extends ServicioImpl{

	@Override
	public String Consumir(String operacion, List <NameValuePair> parameters) {
		
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		HttpPost req = new HttpPost();
		
		try {
			if (!parameters.isEmpty()){
				
				for (NameValuePair p : parameters){
					nvps.add(new BasicNameValuePair(p.getName() , p.getValue()));
				}
				req.setEntity(new UrlEncodedFormEntity(nvps)); 
			}
			String nombre = this.getNomConcesionaria();
			
			URI uri = URI.create("http://localhost:8080/Concesionaria-"+nombre+"-REST/rest/"+nombre+"/"+operacion);  
			req.setURI(uri);
			HttpClient client = HttpClientBuilder.create().build();	       
			HttpResponse resp = client.execute(req);
			HttpEntity responseEntity = resp.getEntity();
			//StatusLine responseStatus = resp.getStatusLine();
			String restResp = EntityUtils.toString(responseEntity);	
			return restResp;
		}
		catch(Exception ex){
			return ex.getMessage();
		}
	}
}
