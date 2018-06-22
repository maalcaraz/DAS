package ar.edu.ubp.das.src.servicios;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.NameValuePair;


public class ServicioCXF extends ServicioImpl{

	public String Consumir(String operacion, List<NameValuePair> parameters) {
		String consumo = "";
		try {
			
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			/* A createClient() se le pasa como parametro la URL del servicio a ser consumido.
			 * Para usar esa clase se importa la libreria org.apache.cxf.endpoint.Client;
			 * 
			 * */
			String url = "http://localhost:9090/Concesionaria"+this.getNomConcesionaria()+"WSPort?wsdl"; 
		
			Client client = dcf.createClient(url);
			/* el metodo invoke() toma como parametros
			 * 1. El nombre de la operacion a consumir
			 * 2. Los parametros que usa la operacion.
			 * Retorna lo que devuelve el servicio.
			 * */
			
			Object[] res = client.invoke(operacion, parameters);
			
			consumo = res.toString();
		} 
		catch (Exception e) {
		
			consumo = e.getMessage();
		}
		return consumo;
	}
}
