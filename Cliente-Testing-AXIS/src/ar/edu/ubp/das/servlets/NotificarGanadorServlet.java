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
 * Servlet implementation class NotificarGanadorServlet
 */
@WebServlet("/notificarGanador.jsp")
public class NotificarGanadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NotificarGanadorServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dniCliente = "23432255";
		String nomCliente = "Pablo Alcaraz";
		String emailCliente = "pabloalcaraz@gmail.com";
		String fechaSorteo = "03-03-18";
	
		try{
			ClientesWSService service = new ClientesWSService();
		
			ClientesWS respuesta = service.getClientesWSPort();
			
			
			request.setAttribute("error", respuesta.notificarGanador("Montironi", dniCliente, nomCliente, emailCliente, fechaSorteo));
			
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
