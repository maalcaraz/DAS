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
		
		String user = request.getParameter("userReg"); // verificar por nulos
		String pass = request.getParameter("pwdReg");
		System.out.println(user);
		System.out.println(pass);
		
		
		DynaActionForm nuevoUsuario = new DynaActionForm();
		nuevoUsuario.setItem("id_usuario", user);
		nuevoUsuario.setItem("tipo_usuario", "cliente");
		nuevoUsuario.setItem("clave_usuario", pass);
		
		MSUsuarioDao Usuario = (MSUsuarioDao)DaoFactory.getDao("Usuario", "login");
		Usuario.insert(nuevoUsuario);
		
		request.setAttribute("respuesta", "Usuario Registrado con exito!");
		return mapping.getForwardByName("success");
	}

}
