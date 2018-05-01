package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.ubp.das.beans.ClienteBean;
import ar.edu.ubp.das.ws.ConcesionariaRossoWS;
import ar.edu.ubp.das.ws.ConcesionariaRossoWSService;

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
		
		try{
			ConcesionariaRossoWSService service = new ConcesionariaRossoWSService();
			ConcesionariaRossoWS respuesta = service.getConcesionariaRossoWSPort();
			
			
			// Armar de nuevo un bean de Clientes para pasarlo a otros jsp
			
			Gson gson = new Gson();
			LinkedList<ClienteBean> clientes = gson.fromJson(respuesta.getClientes(), new TypeToken<LinkedList<ClienteBean>>(){}.getType() );
			
			//request.setAttribute("servicio", servicio);
			request.setAttribute("clientes", clientes);
			this.gotoPage("/clientes.jsp", request, response);
			
		}
		catch (Exception ex){
			request.setAttribute("error", ex.getMessage());
			this.gotoPage("/error.jsp", request, response);
		}
	}
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
                          dispatcher.forward(request, response);
	}
}
