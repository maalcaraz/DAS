package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

public class CargarSorteoAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {

		/*
		 * Logica de sesion. Luego de implementarla donde sea necesaria se evaluara removerla a otro paquete
		 * para no duplicar codigo
		 */
		HttpSession session = request.getSession(false);
		
		if(session == null){
			return mapping.getForwardByName("noSession");
		}
		else if(session.getAttribute("usuario") == null)
		{
			session.invalidate();
			return mapping.getForwardByName("noSession");
		}
		return mapping.getForwardByName("success");
	}
}
