package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

public class LogOutAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		
		HttpSession session = request.getSession(false);
		
		/*
		 * Chequeando el intervalo de tiempo inactivo para diferencias de las sesiones
		 * creadas en action controller para setear el lenguaje en SetLocale 
		 */
		while(session != null && session.getAttribute("usuario") != null)
		{
			session.invalidate();
			
			session = request.getSession(false);
		}
		
		return mapping.getForwardByName("success");
	}

}
