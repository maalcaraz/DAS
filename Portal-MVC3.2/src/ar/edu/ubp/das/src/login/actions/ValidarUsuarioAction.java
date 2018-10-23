package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.login.daos.MSLoginDao;

public class ValidarUsuarioAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		

		String user = request.getParameter("user") == null ? "Campo vacio" : request.getParameter("user");
		String pass = request.getParameter("pwd") == null ? "Campo vacio" : request.getParameter("pwd");
		
		
		MSLoginDao dao = (MSLoginDao)DaoFactory.getDao("Login", "login");
		
		DynaActionForm daf = new DynaActionForm();
		Timestamp loginTime = new Timestamp((new java.util.Date()).getTime());
		daf.setItem("user", user);
		daf.setItem("pass", pass);
		daf.setItem("loginTime", loginTime.toString());
		System.out.println("Momento en que se loggeo el usuario: "+ loginTime.toString());
		
		
		DynaActionForm res = dao.select(daf).get(0);
		HttpSession session = request.getSession();
		
		session.setAttribute( "usuario",  form.getItem( "usuario" ) );
		// Esto de arriba por que?
		
		switch( Integer.parseInt(res.getItem("tipo_usuario"))){
		case 0: //dao.insert(daf);
				session.setAttribute( "usuario",  user );
				session.setAttribute( "tipoUsuario",  "admin" );
				//Seteando tiempo de sesion a 4 minutos para testear
				session.setMaxInactiveInterval(4*60);
				return mapping.getForwardByName("admin");
		case 1: //dao.insert(daf);
				session.setAttribute( "usuario",  user );
				session.setAttribute( "tipoUsuario",  "cliente" );
				//Seteando tiempo de sesion a 4 minutos para testear
				session.setMaxInactiveInterval(4*60);
				return mapping.getForwardByName("cliente");
		case 2: //dao.insert(daf);
				return mapping.getForwardByName("sistema"); 
		default: throw new RuntimeException("Error de validacion de usuario"); 
		
		}
	}
}
