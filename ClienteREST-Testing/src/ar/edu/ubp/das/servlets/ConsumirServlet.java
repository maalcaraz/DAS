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

@WebServlet("/consumir.jsp")
public class ConsumirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsumirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String dniCliente = "23.432.255";
		String nomCliente = "Pablo Alcaraz";
		String emailCliente = "pabloalcaraz@gmail.com";
		String fechaSorteo = "13-03-2018";
	
	try {
      	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
      	nvps.add(new BasicNameValuePair("id_concesionaria" , "Montironi"));
		nvps.add(new BasicNameValuePair("dni_cliente" , dniCliente));
		nvps.add(new BasicNameValuePair("nombre_apellido", nomCliente));
		nvps.add(new BasicNameValuePair("email_cliente", emailCliente));
		nvps.add(new BasicNameValuePair("fecha_sorteo", fechaSorteo));

		URI uri = URI.create("http://localhost:8080/Concesionaria-AutoHaus-REST/rest/autohaus/notificarGanador");            
			            
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
			
		//Gson gson = new Gson();
		
		
		//LinkedList<AlumnosCurso> alumnos = gson.fromJson(restResp, new TypeToken<LinkedList<AlumnosCurso>>(){}.getType());
		request.setAttribute("error", restResp);
    	this.gotoPage("/error.jsp", request, response);
       
        }
        catch(RuntimeException ex) {
        	response.setStatus(400);
        	request.setAttribute("error", ex.getMessage());
        	this.gotoPage("/error.jsp", request, response);
        }
		/*Consumo primer servicio
		URI uri = URI.create("http://localhost:8080/Concesionaria-AutoHaus-REST/rest/autohaus/datosClientes");            
        
		HttpPost req = new HttpPost();
		         req.setURI(uri);
		         //req.setEntity(new UrlEncodedFormEntity(nvps)); 
		HttpClient client = HttpClientBuilder.create().build();	       
		HttpResponse resp = client.execute(req);
		HttpEntity responseEntity = resp.getEntity();
		StatusLine responseStatus = resp.getStatusLine();
		String restResp = EntityUtils.toString(responseEntity);	
			
		if(responseStatus.getStatusCode() != 200) {
			throw new RuntimeException(restResp);
		}
		request.setAttribute("error", restResp);
		this.gotoPage("/error.jsp", request, response);*/
		
	}
	 private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
	                          dispatcher.forward(request, response);
	 }
}
