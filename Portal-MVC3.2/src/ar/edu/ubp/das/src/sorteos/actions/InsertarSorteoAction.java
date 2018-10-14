package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class InsertarSorteoAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		String fechaSorteo = request.getParameter("nuevaFecha");
		String fechaProximo =  "";
		SorteosForm sorteo = new SorteosForm();
		Date hoy = new Date();
		sorteo.setIdSorteo("s"+ hoy.toString());
		sorteo.setFechaSorteado(fechaSorteo);
		sorteo.setFechaProximo(fechaProximo);
		
		try {
			MSSorteosDao Sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
			Sorteos.insert(sorteo);
		}
		catch (SQLException ex){
			System.out.println(ex.getMessage());
			return mapping.getForwardByName("failure");
		}
		
		return mapping.getForwardByName("success");
	}

}
