package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.estados.daos.MSEstadosClientesDao;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "EstadosClientesWSPort", serviceName = "EstadosClientesWSService")
public class EstadosClientesWS {

	@WebMethod(operationName = "getEstados", action = "urn:GetEstados")
	public String getEstados(){
		
		List<DynaActionForm> estadosCuentas = new LinkedList<DynaActionForm>();
		
		try {
			MSEstadosClientesDao dao = (MSEstadosClientesDao)DaoFactory.getDao( "EstadosClientes", "ar.edu.ubp.das.src.estados" );
			DynaActionForm daf = new DynaActionForm();
		
			estadosCuentas = dao.select(daf);
			
			LinkedList<Map<String,Object>> estadosForJson = new LinkedList<Map<String,Object>>();
			
			for( DynaActionForm c : estadosCuentas ) {
				estadosForJson.add(c.getItems());
			}
			
			Gson gson = new GsonBuilder().create();
			
			return gson.toJson(estadosForJson);
			
		} 
		catch ( SQLException error ) {
    	    return error.toString();
		}
	}
}