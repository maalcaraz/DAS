package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.login.daos.MSUsuarioDao;

public class RegistrarUsuarioAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		String user = (request.getParameter("userReg").trim()); // verificar por nulos
		String pass = (request.getParameter("pwdReg").trim());
		if( ((user == null) || user.equals("")) || ((pass == null) || pass.equals("")) ) {
			throw new RuntimeException("Los campos no pueden estar vacios");
		}
		
		DynaActionForm nuevoUsuario = new DynaActionForm();
		nuevoUsuario.setItem("id_usuario", user);
		nuevoUsuario.setItem("tipo_usuario", "admin");
		nuevoUsuario.setItem("clave_usuario", pass);
		
		MSUsuarioDao Usuario = (MSUsuarioDao)DaoFactory.getDao("Usuario", "login");
		Usuario.insert(nuevoUsuario);
		
		return mapping.getForwardByName("success");
	}

}
