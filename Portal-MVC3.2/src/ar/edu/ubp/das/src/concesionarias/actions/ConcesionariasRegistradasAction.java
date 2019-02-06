package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;

public class ConcesionariasRegistradasAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			List<DynaActionForm> concesionarias = Concesionaria.select(null);
			request.setAttribute("concesionarias", concesionarias);
			return mapping.getForwardByName("success");
		}
		catch (Exception ex){
			String error = "[ConcesionariasRegistradasAction]No se pudieron mostrar las concesionarias registradas debido al siguiente error: "+ex.getMessage();
			request.setAttribute("error", error);
			return mapping.getForwardByName("failure");
		}
	}
}
