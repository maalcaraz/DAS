package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSParticipantesDao;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class VerResultadosSorteoAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		String idSorteo = request.getParameter("idSorteo");
		SorteosForm sorteo = new SorteosForm();
		sorteo.setIdSorteo(idSorteo);
		/* Hay que traer los datos con ParticipanteBean*/ 
		/* Tambien hay que traer los datos del ganador del sorteo */
		MSParticipantesDao Participantes = (MSParticipantesDao)DaoFactory.getDao("Participantes", "sorteos");
		
		request.setAttribute("participantes", Participantes.select(sorteo));
		request.setAttribute("sorteo", sorteo);
		
		return mapping.getForwardByName("success");
	}

}
