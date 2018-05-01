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

@WebServlet("/verificarCancelado.jsp")
public class VerificarCanceladoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VerificarCanceladoServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			ConcesionariaRossoWSService service = new ConcesionariaRossoWSService();
		
			ConcesionariaRossoWS respuesta = service.getConcesionariaRossoWSPort();
			String dniCliente = request.getParameter("dniVerificar");//"23432255";
			String idPlan = request.getParameter("idPlan");//"303458";
			
			request.setAttribute("error", respuesta.verificarCancelado(dniCliente, idPlan));
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
