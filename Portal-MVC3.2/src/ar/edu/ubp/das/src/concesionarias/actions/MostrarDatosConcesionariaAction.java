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
import ar.edu.ubp.das.portal.forms.ClienteForm;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class MostrarDatosConcesionariaAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
		
		List<DynaActionForm> forms = Concesionaria.select(null);
		String idConcesionaria = request.getParameter("idConcesionaria");
		System.out.println("idconcesionaria que viene como parametro: "+ idConcesionaria);
		
		for (DynaActionForm f : forms){
			ConcesionariaForm c = (ConcesionariaForm) f;
			if (c.getIdConcesionaria().equals(idConcesionaria)){
				System.out.println("Esta en el action de Mostrar datos concesionaria");
				request.setAttribute("concesionaria", c);
				

				List<DynaActionForm> clientesAux = Concesionaria.select(c);
				
				
				List<ClienteForm> clientes = new LinkedList<ClienteForm>();
				
				for (DynaActionForm cliDAF : clientesAux){
					ClienteForm cliF = (ClienteForm) cliDAF;
					clientes.add(cliF); 
				}
				
				request.setAttribute("clientes", clientes);
				System.out.println("idconcesionaria que viene de la base: "+ c.getIdConcesionaria());
				//System.out.println("Clientes: " + c.getClientes().toString());
			}
		}
			
		return mapping.getForwardByName("success");
	}

}
