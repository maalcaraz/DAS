package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class InsercionConcesionariaAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		// Generar el idConcesionaria basado en nombre
		String url = request.getParameter("url");
		String nombreConcesionaria = request.getParameter("nombreConcesionaria");
		String tipoServicio = request.getParameter("tipoServicio");
		String idConcesionaria = nombreConcesionaria + nombreConcesionaria.hashCode();
		System.out.println(idConcesionaria);
		String email = request.getParameter("emailConcesionaria");
		String direccion = request.getParameter("direccionConcesionaria");
		String cuit = request.getParameter("cuit");
		String telefono = request.getParameter("telefonoConcesionaria");
		
		try {
			ConcesionariaForm nuevaConc = new ConcesionariaForm();
			nuevaConc.setIdConcesionaria(idConcesionaria);
			nuevaConc.setNomConcesionaria(nombreConcesionaria);
			nuevaConc.setCodTecnologia(tipoServicio);
			nuevaConc.setCuit(cuit);
			nuevaConc.setEmail(email);
			nuevaConc.setDireccion(direccion);
			nuevaConc.setTelefono(telefono);
			
			MSConcesionariaDao dao = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			
			dao.insert(nuevaConc);
			return null;
			// devolver el mensaje a mostrar en el alert
		}
		catch(Exception ex){
			System.out.println("Error en Suscripcion de concesionaria: "+ ex.getMessage());
			return null;
			
		}
	}

}
