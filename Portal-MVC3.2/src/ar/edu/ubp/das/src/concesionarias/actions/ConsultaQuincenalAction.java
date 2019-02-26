package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class ConsultaQuincenalAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		LinkedList<ClienteForm> clientes;
		LinkedList<PlanForm> planes;
		LinkedList<AdquiridoForm> adquiridos;
		LinkedList<CuotaForm> cuotas;
		
		try {
			LinkedList<ConcesionariaForm> cons = new LinkedList<ConcesionariaForm>();
			String idPortal = "PORTALGOB";
			String consumo = "Fallo";
			List <NameValuePair> parameters = new ArrayList <NameValuePair>();
			parameters.add(new BasicNameValuePair("id_portal", idPortal));
			
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
			LinkedList<DynaActionForm> forms = (LinkedList<DynaActionForm>) Concesionaria.select(null);
			
			Gson gson = new Gson();
			
			String restResp = "";
			
			for (DynaActionForm f : forms){
				
				ConcesionariaForm c = (ConcesionariaForm) f;
				
				if (c.getAprobada().equals("S")){
					
					System.out.println("[Consulta]NomConcesionaria: "+ c.getNomConcesionaria());
					
					System.out.println("Respuesta de "+ c.getNomConcesionaria() +":");
					
					restResp = c.getWebService().Consumir("getClientes", parameters);
					TransaccionForm transaccion = gson.fromJson(restResp, new TypeToken<TransaccionForm>(){}.getType());
					
					String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
					/*Listado de Clientes*/
					String strClientes = listaRetorno[0] + "]";
					clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteForm>>(){}.getType() );
					/*Listado de Planes*/
					String strPlanes = listaRetorno[1] + "]";
					planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanForm>>(){}.getType() );
					/*Listado de Aquiridos*/
					String strAdquiridos = listaRetorno[2] + "]";
					adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoForm>>(){}.getType() );
					/*Listado de Cuotas*/
					String strCuotas = listaRetorno[3];
					cuotas = gson.fromJson(strCuotas, new TypeToken<LinkedList<CuotaForm>>(){}.getType() );
					
					Date fechaHoy = new Date();
					SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
					
					c.setAdquiridos(adquiridos);
					c.setClientes(clientes);
					c.setCuotas(cuotas);
					c.setPlanes(planes);
					c.setTransacForm(transaccion);
					c.setUltimaActualizacion(parser.format(fechaHoy));
					//c.setConsultaPendiente(false);
					c.setItem("operacion", "insercionDatos");
					
					cons.add(c);	
					Concesionaria.update(c);
				}
				consumo = "OK";
			}
			request.setAttribute("consumo", consumo);
			request.setAttribute("concesionarias", cons);
			return mapping.getForwardByName("success");
		}
		catch(SQLException ex){
			String msg = "Error en Consulta Quincenal: "+ ex.getMessage();
			request.setAttribute("consumo", msg);
			request.setAttribute("error", msg);
			return mapping.getForwardByName("failure");
		}
	}
}
