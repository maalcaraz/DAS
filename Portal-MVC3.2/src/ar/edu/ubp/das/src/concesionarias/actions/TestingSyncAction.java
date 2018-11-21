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
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class TestingSyncAction  implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		
		try {
			// hacer select de concesionarias que estan en la BD.
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			
			System.out.println("[Testing sync]Llegamos al action");
			
			List<DynaActionForm> forms =  Concesionaria.select(null);
			
			
			for (DynaActionForm f : forms){
				// Almacenarlas en una lista
				System.out.println("[Testing sync]Select entrado: " + f.toString());
				ConcesionariaForm c = (ConcesionariaForm) f;
				String mensaje = "[Testing sync]Respuesta de "+ c.getNomConcesionaria() +":";
				mensaje += c.getWebService().Consumir("ejemplo", null);//Consumir("ejemplo", null);
				System.out.println("[Testing sync : 40]"+mensaje);
				f.setItem("mensaje", mensaje);
			}
			
			request.setAttribute("consumos", forms);
			return mapping.getForwardByName("success");
			// Recorrer esa lista, y de a una ir consumiendo la funcion de ejemplo.
			
			// Los mensajes se deberian mandar al jsp para que se muestre si estan conectadas o no.
			
		}
		catch (Exception ex){
			//System.out.println(ex.getMessage());
			ex.printStackTrace();
			request.setAttribute("consumos", ex.getMessage());
			return mapping.getForwardByName("success");
			
		}
	}

}
