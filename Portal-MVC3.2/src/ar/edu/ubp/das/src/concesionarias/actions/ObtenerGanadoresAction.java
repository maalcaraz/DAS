package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.ganadores.daos.MSGanadoresDao;

public class ObtenerGanadoresAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ganadores");
		try {
			
			List<DynaActionForm> lGanadores = Ganadores.select(null);
			
			
			request.setAttribute("ganadores", lGanadores);
			return mapping.getForwardByName("success");
		}
		catch(Exception ex){
			request.setAttribute("error", ex.getMessage());
			return mapping.getForwardByName("failure");
		}
	}
}
