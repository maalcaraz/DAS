package ar.edu.ubp.das.src.servicios;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.NameValuePair;


public class ServicioCXF extends ServicioImpl{

	public String Consumir(String operacion, List<NameValuePair> parameters) {
		String consumo = "";
		Client client = null;
		try {
			
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			/* A createClient() se le pasa como parametro la URL del servicio a ser consumido.
			 * Para usar esa clase se importa la libreria org.apache.cxf.endpoint.Client;
			 * */
			String url = this.getUrl()+"?wsdl"; 
			System.out.println("[Servicio CXF]URL: "+ url);
		
			 client = dcf.createClient(url);
			/* el metodo invoke() toma como parametros
			 * 1. El nombre de la operacion a consumir
			 * 2. Los parametros que usa la operacion.
			 * Retorna lo que devuelve el servicio.
			 * */
			Object[] res = null;
			if (parameters!= null){
				System.out.println("[Servicio CXF] Viene con parametros");
				Object[] o = new String[parameters.size()];
				for (int i=0; i< parameters.size(); i++){
					System.out.println(parameters.get(i).getName()+ ":" + parameters.get(i).getValue());
					o[i] = parameters.get(i).getValue();
				}
				res = client.invoke(operacion, o);
			}
			else {
				System.out.println("[Servicio CXF:41] Viene sin parametros");
				res = client.invoke(operacion, parameters);
			}
			System.out.println("[Servicio CXF:44] Respuesta de CXF: "+res[0]);
			consumo = res[0].toString();
		} 
		catch (Exception e) {
			consumo = "[Servicio CXF:48 - Catch clause]Salto la excepcion: "+ e.getMessage() ;
			
		}
		if (client != null) client.destroy();
		else consumo = "No conectado";
		return consumo;
	}
}
