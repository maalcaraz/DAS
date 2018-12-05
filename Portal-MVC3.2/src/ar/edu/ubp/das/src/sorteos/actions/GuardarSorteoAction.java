package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;

public class GuardarSorteoAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		String idSorteo = request.getParameter("idSorteo");
		String nuevaFecha = request.getParameter("nuevaFecha");
		System.out.println("[GuardarSorteoAction]Nueva fecha: "+nuevaFecha);
		MSSorteosDao sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
		DynaActionForm sorteo = new DynaActionForm();
		sorteo.setItem("idSorteo", idSorteo);
		sorteo.setItem("descripcion", "");
		sorteo.setItem("fechaSorteo", nuevaFecha);
		sorteos.update(sorteo);
		
		return mapping.getForwardByName("success");
	}

}
