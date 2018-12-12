package ar.edu.ubp.das.src.clientes.actions;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.portal.forms.ClienteForm;

public class EstadoDatosClientesAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
			//Session para obtener dni de cliente para buscar info en BD
				//HttpSession session = request.getSession();
				String forward = "success";
				
				LinkedList<ConcesionariaForm> cons = new LinkedList<ConcesionariaForm>();
				try {

					MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
					LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Concesionaria.select(null);
					
					
					LinkedList<ClienteForm> clientesInsertar;
					boolean hayClientes = false;
					
					for (DynaActionForm f : forms){
						
						// Almacenarlas en una lista
						
						ConcesionariaForm c = (ConcesionariaForm) f;
						if(c.getAprobada().equals("S"))
						{
							LinkedList<DynaActionForm> clientes = (LinkedList<DynaActionForm>) Concesionaria.select(c);
							
							clientesInsertar = new LinkedList<ClienteForm>();
							
							if(!clientes.isEmpty()){
								hayClientes = true;
								for(DynaActionForm cli : clientes){
									ClienteForm cliente = (ClienteForm) cli;
									
									clientesInsertar.add(cliente);
								}
							}
							c.setClientes(clientesInsertar);

							System.out.println("[Consulta]NomConcesionaria: "+ c.getIdConcesionaria());

							cons.add(c);
						}
					}
					
					request.setAttribute("hay_clientes", hayClientes);
					request.setAttribute("concesionarias", cons);
					
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					String msg = "Error en Consulta Cliente: "+ e.getMessage();
					request.setAttribute("error",msg);
					forward = "failure";
				}
				return mapping.getForwardByName(forward);
		}
}
