package ar.edu.ubp.das.src.clientes.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

public class DatosClienteAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		/*Deberiamos obtener desde cookies el dni del cliente y el idPlan para ejecutar algun procedimiento
		 * de la BD local y que me traiga los datos de cuenta del cliente que estoy solicitando.*/
		
		return null;
	}

}
