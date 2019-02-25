package ar.edu.ubp.das.src.clientes.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.src.portal.forms.ClienteForm;
import ar.edu.ubp.das.src.portal.forms.CuotaForm;
import ar.edu.ubp.das.src.portal.forms.PlanForm;
import ar.edu.ubp.das.src.portal.forms.TransaccionForm;

public class EstadoClienteParticularAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		String dniCliente = (String)request.getParameter("dniCliente");
		String idConcesionaria = (String)request.getParameter("idConcesionaria");
		
		dniCliente = dniCliente.trim();
		idConcesionaria = idConcesionaria.trim();
		
		System.out.println(dniCliente.trim());
		System.out.println(idConcesionaria.trim());
		
		
		
		LinkedList<ConcesionariaForm> cons = new LinkedList<ConcesionariaForm>();
		String idPortal = "PORTALGOB";
		String consumo = "Fallo";
		List <NameValuePair> parameters = new ArrayList <NameValuePair>();
		parameters.add(new BasicNameValuePair("id_portal", idPortal));
		parameters.add(new BasicNameValuePair("dni_cliente", dniCliente));
		try {
			System.out.println("[ConsultaClienteParticular] Action de consulta cliente particular");
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Concesionaria.select(null);
			
			Gson gson = new Gson();
			TransaccionForm transaccion = null;
			String restResp = "";
			
			for (DynaActionForm f : forms){
				
				// Almacenarlas en una lista
				ConcesionariaForm c = (ConcesionariaForm) f;
				
				if (c.getIdConcesionaria().equals(idConcesionaria) && c.getAprobada().equals("S")){
					System.out.println("[Consulta]NomConcesionaria: "+ c.getNomConcesionaria());
					
					restResp = "Respuesta de "+ c.getNomConcesionaria() +":";
					System.out.println(restResp);
					restResp = c.getWebService().Consumir("getClienteParticular", parameters);
					
					System.out.println("[Consulta cliente particular  -Post consumo getClienteParticular]");
					
					//HACER MANEJO DE ERROR ACA SI ALGUNO DEVOLVIO ERROR
						
					transaccion = gson.fromJson(restResp, new TypeToken<TransaccionForm>(){}.getType());
					
					String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
					/*Listado de Clientes*/
					String strClientes = listaRetorno[0] + "]";
					LinkedList<ClienteForm> clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteForm>>(){}.getType() );
					/*Listado de Planes*/
					String strPlanes = listaRetorno[1] + "]";
					LinkedList<PlanForm> planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanForm>>(){}.getType() );
					/*Listado de Aquiridos*/
					String strAdquiridos = listaRetorno[2] + "]";
					LinkedList<AdquiridoForm> adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoForm>>(){}.getType() );
					/*Listado de Cuotas*/
					String strCuotas = listaRetorno[3];
					System.out.println("[ConsultaQuincenalAction]String de cuotas: "+strCuotas);
					LinkedList<CuotaForm> cuotas = gson.fromJson(strCuotas, new TypeToken<LinkedList<CuotaForm>>(){}.getType() );
					System.out.println("[Consulta Quincenal - Pre update]Hasta aca todo bien");
					c.setAdquiridos(adquiridos);
					c.setClientes(clientes);
					c.setCuotas(cuotas);
					c.setPlanes(planes);
					c.setTransacForm(transaccion);
					
					c.setItem("operacion", "insercionDatos");
					cons.add(c);	
					Concesionaria.update(c);
					System.out.println("[Consulta Quincenal - Post update]Hasta aca todo bien");
					
				}
				consumo = "OK";
			}
		}
		catch(SQLException ex){
			String msg = "Error en Consulta Quincenal: "+ ex.getMessage();
			request.setAttribute("consumo", msg);
			request.setAttribute("error", msg);
			return mapping.getForwardByName("failure");
		}
		
		request.setAttribute("consumo", consumo);
		request.setAttribute("concesionarias", cons);
		
		return mapping.getForwardByName("success");
	}

}
