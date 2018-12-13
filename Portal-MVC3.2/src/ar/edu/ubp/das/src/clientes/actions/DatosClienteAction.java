package ar.edu.ubp.das.src.clientes.actions;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clientes.daos.MSClienteDao;
import ar.edu.ubp.das.src.portal.forms.ClienteForm;

public class DatosClienteAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		/*
		 * TO DO: implementer respetando consigna que el usuario debe generarse un usuario
		 * la primera vez que se logea.
		 */
		//Session para obtener dni de cliente para buscar info en BD
		HttpSession session = request.getSession();
		String forward = "failure";
		
		/*
		 * tenemos que traer todos los datos y ademas la fecha de la ultima actualizacion
		 * 
		 */
		
		try {
			ClienteForm clienteForm = new ClienteForm();
			
			//clienteForm.setDniCliente(Integer.toString(25555555));
			//System.out.println("[DatosClienteAction]"+session.getAttribute( "usuario" ).toString());
			//clienteForm.setIdConcesionaria("AutoHaus1503004614"); // --> Por ahora hardcodeado
			
			
			MSClienteDao cliente = (MSClienteDao)DaoFactory.getDao("Cliente", "clientes");
			LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>)cliente.select(clienteForm);
			clienteForm = (ClienteForm)forms.get(0);
			
			System.out.println(clienteForm.getDniCliente());
			System.out.println(clienteForm.getNomCliente());
				
			request.setAttribute("cliente", clienteForm);
			request.setAttribute("adquiridos", clienteForm.getAdquiridos());

			forward = "success";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			String msg = "Error en Consulta Cliente: "+ e.getMessage();
			request.setAttribute("error",msg);
			forward = "failure";
		}

		return mapping.getForwardByName(forward);
	}

}
