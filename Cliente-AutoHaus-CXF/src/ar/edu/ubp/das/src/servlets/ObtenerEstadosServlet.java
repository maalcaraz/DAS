package ar.edu.ubp.das.src.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.ws.EstadosClientes;
import ar.edu.ubp.das.ws.EstadosClientesService;

@WebServlet("/buscarEstados.jsp")
public class ObtenerEstadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerEstadosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Llega al servlet...");
		response.setContentType("text/html;charset=ISO-8859-1");
		
		EstadosClientesService service = new EstadosClientesService();
		
		EstadosClientes estadosClientes = service.getEstadosClientesPort();
		
		System.out.println(estadosClientes.getEstados());
		
		request.setAttribute("resultado",estadosClientes.getEstados());
		gotoPage("/resultados.jsp", request, response);
	}
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
	                          dispatcher.forward(request, response);
	    }
}
