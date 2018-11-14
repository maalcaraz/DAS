package ar.edu.ubp.das.src.servicios;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.http.NameValuePair;
import org.xml.sax.SAXException;

import ar.edu.ubp.das.src.servicios.axis.utils.DynamicWebServiceClient;
import ar.edu.ubp.das.src.servicios.axis.utils.ParseWsdlService;
import ar.edu.ubp.das.src.servicios.axis.utils.ServiceDetail;
import ar.edu.ubp.das.src.servicios.axis.utils.SoapMessageUtil;

public class ServicioAxis2 extends ServicioImpl {

	@Override
	public String Consumir(String operacion, List<NameValuePair> parameters) {
		
		String consumo = "";
		String url = this.getUrl()+"?wsdl"; // Esto habria que cambiarlo e incluir el WS aca.
		
		DynamicWebServiceClient dynamicClient = new DynamicWebServiceClient();
		ParseWsdlService parseService = new ParseWsdlService();
		ServiceDetail ServicioConcesionaria = null;
		
		try {
			ServicioConcesionaria = parseService.parse(url);
		} 
		catch (SAXException | IOException | ParserConfigurationException e) {
			
			e.printStackTrace();
		}
		
		SOAPMessage response = null;
		try {
			response = dynamicClient.invokeOperation(ServicioConcesionaria, operacion, parameters);
		} 
		catch (SOAPException | IOException e) {
			System.out.println("[ServicioAxis - invokeOperation]"+e.getMessage());
		}

		try {
			consumo = SoapMessageUtil.outputSoapMessage(response);
			System.out.println("Response: " + consumo);
			
			
			
		} 
		catch (SOAPException | IOException e) {
			e.printStackTrace();
		}
		
		return consumo;
	}

}
