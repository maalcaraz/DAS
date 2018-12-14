package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class ProximasFechasAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
MSSorteosDao sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
		
		List<SorteosForm> sorteosList = new LinkedList<SorteosForm>();
		for (DynaActionForm s : sorteos.select(null)){
			SorteosForm f = (SorteosForm) s;
			if (f.getPendiente().equals("S")){
				f.setPendiente("Pendiente");
			}
			if (f.getPendiente().equals("N")){
				f.setPendiente("Sorteado");
			}
			sorteosList.add(f);
		}
		request.setAttribute("sorteos", sorteosList);
		return mapping.getForwardByName("success");
	}
// falta manejar error
}
