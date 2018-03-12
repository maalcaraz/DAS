package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.login.daos.MSLoginDao;

public class ValidateLoginAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		

		String user = request.getParameter("user") == null ? "NO TRAJO NADA" : request.getParameter("user");
		String pass = request.getParameter("pwd") == null ? "NO TRAJO NADA" : request.getParameter("pwd");
		
		MSLoginDao dao = (MSLoginDao)DaoFactory.getDao("Login", "login");
		
		DynaActionForm daf = new DynaActionForm();
		
		daf.setItem("user", user);
		daf.setItem("pass", pass);
		boolean res = dao.valid(daf);
		if (res) {
			System.out.println(res);
			return mapping.getForwardByName("success");
		}
		else {
			System.out.println(res);
			return mapping.getForwardByName("failure");
		}
	}
}
