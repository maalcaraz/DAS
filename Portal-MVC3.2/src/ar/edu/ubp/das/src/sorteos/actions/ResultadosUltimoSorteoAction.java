package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;
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

public class ResultadosUltimoSorteoAction implements Action {
	
	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {

			SorteosForm sorteo = null;
			List<DynaActionForm> sorteosList = null;
			
			
			try {
				MSSorteosDao Sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
				sorteosList = Sorteos.obtenerResultadoUltimoSorteo();
				if(sorteosList.size() != 0){
					sorteo = (SorteosForm) sorteosList.get(0);
					request.setAttribute("sorteo", sorteo);
				}
				
			}
			catch (SQLException ex){
				System.out.println(ex.getMessage());
				return mapping.getForwardByName("failure");
			}
					
			return mapping.getForwardByName("success");
		}

}
