package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

import ar.edu.ubp.das.beans.AdquiridoBean;
import ar.edu.ubp.das.beans.ClienteBean;
import ar.edu.ubp.das.beans.CuotaBean;
import ar.edu.ubp.das.beans.PlanBean;
import ar.edu.ubp.das.beans.TransaccionBean;

/**
 * Servlet implementation class ObtenerClientesServlet
 */
@WebServlet("/getClientes.jsp")
public class ObtenerClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ObtenerClientesServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servicio = (request.getParameter("servicio")); 
		
		String s = "http://localhost:8080/Concesionaria-"+servicio+"-REST/rest/"+servicio+"/getClientes";
		System.out.println(s);
		URI uri = URI.create(s); // Podemos evitar este duplicado y sacarlo del path en el servicio           
		HttpPost req = new HttpPost();
		         req.setURI(uri);
		
		HttpClient client = HttpClientBuilder.create().build();	       
		HttpResponse resp = client.execute(req);
		HttpEntity responseEntity = resp.getEntity();
		StatusLine responseStatus = resp.getStatusLine();
		String restResp = EntityUtils.toString(responseEntity);	
			
		if(responseStatus.getStatusCode() != 200) {
			throw new RuntimeException(restResp);
		}
		
		Gson gson = new Gson();
		//LinkedList<ClienteBean> clientes = gson.fromJson(restResp, new TypeToken<LinkedList<ClienteBean>>(){}.getType() );
		
		TransaccionBean transaccion = gson.fromJson(restResp, new TypeToken<TransaccionBean>(){}.getType());
		String idConcesionaria = transaccion.getIdConcesionaria();
		
		String listaRetorno[] = transaccion.getRetorno().split("],");

		//System.out.println(listaRetorno[0]);
		String strClientes = listaRetorno[0] + "]";
		LinkedList<ClienteBean> clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteBean>>(){}.getType() );
		
		
		gson = new Gson();
		String strPlanes = listaRetorno[1] + "]";
		LinkedList<PlanBean> planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanBean>>(){}.getType() );
		
		gson = new Gson();
		String strAdquiridos = listaRetorno[2] + "]";
		LinkedList<AdquiridoBean> adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoBean>>(){}.getType() );
		
		gson = new Gson();
		LinkedList<CuotaBean> cuotas = gson.fromJson(listaRetorno[3], new TypeToken<LinkedList<CuotaBean>>(){}.getType() );
		
		request.setAttribute("idConcesionaria", idConcesionaria);
		request.setAttribute("clientes", clientes);
		request.setAttribute("planes", planes);
		request.setAttribute("adquiridos", adquiridos);
		request.setAttribute("cuotas", cuotas);
		
		this.gotoPage("/clientes.jsp", request, response);
		
	}
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
                          dispatcher.forward(request, response);
	}
}
