package ar.edu.ubp.das.src.clientes.actions;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.portal.forms.TransaccionForm;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;

public class DatosClienteAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		/*Deberiamos obtener desde cookies el dni del cliente y el idPlan para ejecutar algun procedimiento
		 * de la BD local y que me traiga los datos de cuenta del cliente que estoy solicitando.*/
		
		HttpSession session = request.getSession();
		
		System.out.println("usuario: " + session.getAttribute( "usuario" ));
		
		/*
		 * tenemos que traer todos los datos y ademas la fecha de la ultima actualizacion
		 * 
		 */
		/*
		MSConcesionariaDao Cliente = (MSConcesionariaDao)DaoFactory.getDao("Cliente", "clientes");
		LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Cliente.select(null);
		*/

		return mapping.getForwardByName("success");
	}

}
