package ar.edu.ubp.das.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.ws.ConcesionariaRossoWS;
import ar.edu.ubp.das.ws.ConcesionariaRossoWSService;

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
		
		String idPortal = "PORTALGOB";
		String idConcesionaria = request.getParameter("idConcesionaria");
		String dniCliente = request.getParameter("dniCliente");
		String nomCliente = request.getParameter("nombreApellido");
		String idPlan = request.getParameter("idPlan");
		String fechaSorteo = request.getParameter("fechaSorteo");
	
		try{
			ConcesionariaRossoWSService service = new ConcesionariaRossoWSService();
			ConcesionariaRossoWS respuesta = service.getConcesionariaRossoWSPort();
			request.setAttribute("error", respuesta.notificarGanador(idPortal, idConcesionaria, dniCliente, nomCliente, idPlan, fechaSorteo));
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
