package ar.edu.ubp.das.src.servicios;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.NameValuePair;

public class ServicioAxis2 extends ServicioImpl {

	@Override
	public String Consumir(String operacion, List<NameValuePair> parameters) {
		String consumo = "";
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try {
			
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			/* A createClient() se le pasa como parametro la URL del servicio a ser consumido.
			 * Para usar esa clase se importa la libreria org.apache.cxf.endpoint.Client;
			 * */
			String url = this.getUrl()+"?wsdl"; 
			System.out.println("[Servicio CXF]URL: "+ url);
		
			Client client = dcf.createClient(url);
			/* el metodo invoke() toma como parametros
			 * 1. El nombre de la operacion a consumir
			 * 2. Los parametros que usa la operacion.
			 * Retorna lo que devuelve el servicio.
			 * */
			System.out.println("[Servicio CXF]Aca no llega...");
			Object[] res = null;
			if (parameters!= null){
				Object[] o = new String[parameters.size()];
				for (int i=0; i< parameters.size(); i++){
					//System.out.println(l.getName()+ ":" + l.getValue());
					o[i] = parameters.get(i).getValue();
				}
				res = client.invoke(operacion, o);
				
			}
			else {
				res = client.invoke(operacion, parameters);
			}
			System.out.println("[Servicio CXF] Respuesta de CXF: "+res[0]);
			consumo = res[0].toString();
		} 
		catch (Exception e) {
			consumo = "[Servicio CXF - Catch clause]Salto la excepcion: "+ e.getMessage() ;
			e.printStackTrace();
		}
		Thread.currentThread().setContextClassLoader(cl);
		return consumo;
		/*
		String returnContent = null;
		String url = this.getUrl()+"?wsdl"; // Esto habria que cambiarlo e incluir el WS aca.
		
		DynamicWebServiceClient dynamicClient = new DynamicWebServiceClient();
		ParseWsdlService parseService = new ParseWsdlService();
		ServiceDetail ServicioConcesionaria = null;
		
		try {
			ServicioConcesionaria = parseService.parse(url);
			
		} 
		catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("[Servicio Axis]Error en parseo del servicio: "+e.getMessage());
		}
		
		SOAPMessage response = null;
		try {
			
			response = dynamicClient.invokeOperation(ServicioConcesionaria, operacion, parameters);
		} 
		catch (SOAPException | IOException e) {
			System.out.println("[ServicioAxis - invokeOperation]"+e.getMessage());
		}

		try {
	        // get the body
	        SOAPBody soapBody = response.getSOAPBody();
	        // find your node based on tag name
	        NodeList nodes = soapBody.getElementsByTagName("ns:return");
	        // check if the node exists and get the value 
	        Node node = nodes.item(0);
	        returnContent = node != null ? node.getTextContent() : "";

	        System.out.println("Return de AXIS " + returnContent);
		} 
		catch (SOAPException e) {
			System.out.println("[Servicio Axis]No se pudo realizar el consumo por el error: "+e.getMessage());
		}
		return returnContent;
		*/
	}

}
