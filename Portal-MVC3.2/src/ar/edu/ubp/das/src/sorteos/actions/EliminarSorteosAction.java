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
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class EliminarSorteosAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		String sorteoAEliminar = request.getParameter("sorteoAEliminar");
		System.out.println("[EliminarAction]Sorteos: "+sorteoAEliminar);
		MSSorteosDao sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
		/* Arreglo con sorteos a ser eliminados 
		String eliminar[] = sorteosAEliminar.split(",");
		for (String s : eliminar ){
			System.out.println("\nEliminando sorteo "+s);
			DynaActionForm sorteo = new SorteosForm();
			sorteo.setItem("idSorteo", s);
			sorteos.delete(sorteo);
		}
		*/
		DynaActionForm sorteo = new SorteosForm();
		sorteo.setItem("idSorteo", sorteoAEliminar);
		sorteos.delete(sorteo);
		
		
		return mapping.getForwardByName("success");
	}

}
