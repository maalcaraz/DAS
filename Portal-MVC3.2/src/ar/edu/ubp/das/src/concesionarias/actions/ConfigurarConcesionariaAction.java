package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

public class ConfigurarConcesionariaAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		String idConcesionaria = request.getParameter("idConcesionaria");
		System.out.println("[ConfConc]Id concesionaria: "+idConcesionaria);
		request.setAttribute("idConcesionaria", idConcesionaria);
		return mapping.getForwardByName("success");
	}

}
