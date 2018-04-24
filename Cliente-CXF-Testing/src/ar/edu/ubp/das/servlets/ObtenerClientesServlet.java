package ar.edu.ubp.das.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.ws.ClientesWS;
import ar.edu.ubp.das.ws.ClientesWSService;

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
			ClientesWSService service = new ClientesWSService();
		
			ClientesWS respuesta = service.getClientesWSPort();
			
			String clientes = respuesta.getCuentasClientes();
			
			request.setAttribute("error", clientes);
			this.gotoPage("/error.jsp", request, response);
			
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
