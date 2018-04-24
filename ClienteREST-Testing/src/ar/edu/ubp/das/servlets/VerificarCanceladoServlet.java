package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet("/verificarCancelado.jsp")
public class VerificarCanceladoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VerificarCanceladoServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// toma dni_cliente
		
		try {
			
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	      	nvps.add(new BasicNameValuePair("dni_cliente" , "Montironi"));
	      	
	      	URI uri = URI.create("http://localhost:8080/Concesionaria-AutoHaus-REST/rest/autohaus/verificarCancelado");
	
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
				
			request.setAttribute("error", restResp);
	    	this.gotoPage("/error.jsp", request, response);
	       
	        
		}
        catch(RuntimeException ex) {
        	response.setStatus(400);
        	request.setAttribute("error", ex.getMessage());
        	this.gotoPage("/error.jsp", request, response);
        }
		
	}
	
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
                          dispatcher.forward(request, response);
	}

}
