package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class MostrarSorteosAction implements Action{

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
		
		MSSorteosDao sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
		
		List<SorteosForm> sorteosList = new LinkedList<SorteosForm>();
		for (DynaActionForm s : sorteos.select(null)){
			SorteosForm f = (SorteosForm) s;
			if (f.getfechaEjecucion() == null){
				f.setfechaEjecucion("-");
			}
			sorteosList.add(f);
		}
		request.setAttribute("sorteos", sorteosList);
		return mapping.getForwardByName("success");
	}

}
