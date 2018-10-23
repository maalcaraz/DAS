package ar.edu.ubp.das.src.concesionarias.actions;

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
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class MostrarConcesionariasAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			List<DynaActionForm> cAux = Concesionaria.select(null);
			List<ConcesionariaForm> concesionarias = new LinkedList<ConcesionariaForm>();
			List<ConcesionariaForm> pendientes = new LinkedList<ConcesionariaForm>();
			for (DynaActionForm c : cAux){
				ConcesionariaForm c1 = (ConcesionariaForm) c;
				if (c1.getAprobada().equals("S")) concesionarias.add(c1);
				else pendientes.add(c1);
			}
			request.setAttribute("pendientes", pendientes);
			request.setAttribute("concesionarias", concesionarias);
			return mapping.getForwardByName("success");
		}
		catch (Exception ex){
			String error = "No se pudieron mostrar las concesionarias debido al siguiente error: "+ex.getMessage();
			request.setAttribute("error", error);
			return mapping.getForwardByName("failure");
		}
	}
}
