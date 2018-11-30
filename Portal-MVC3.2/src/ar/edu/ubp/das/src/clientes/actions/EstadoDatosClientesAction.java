package ar.edu.ubp.das.src.clientes.actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clientes.daos.MSClienteDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.src.portal.forms.ClienteForm;
import ar.edu.ubp.das.src.portal.forms.CuotaForm;
import ar.edu.ubp.das.src.portal.forms.PlanForm;
import ar.edu.ubp.das.src.portal.forms.TransaccionForm;

public class EstadoDatosClientesAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
			//Session para obtener dni de cliente para buscar info en BD
				HttpSession session = request.getSession();
				String forward = "success";
				
				LinkedList<ConcesionariaForm> cons = new LinkedList<ConcesionariaForm>();
				try {

					MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
					LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Concesionaria.select(null);
					
					Gson gson = new Gson();
					TransaccionForm transaccion = null;
					String restResp = "";
					LinkedList<ClienteForm> clientesInsertar;
					
					for (DynaActionForm f : forms){
						
						// Almacenarlas en una lista
						
						ConcesionariaForm c = (ConcesionariaForm) f;
						LinkedList<DynaActionForm> clientes = (LinkedList<DynaActionForm>) Concesionaria.select(c);
						
						clientesInsertar = new LinkedList<ClienteForm>();
						
						for(DynaActionForm cli : clientes){
							ClienteForm cliente = (ClienteForm) cli;
							
							clientesInsertar.add(cliente);
						}
						c.setClientes(clientesInsertar);

						System.out.println("[Consulta]NomConcesionaria: "+ c.getIdConcesionaria());

						cons.add(c);
					}
					
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
