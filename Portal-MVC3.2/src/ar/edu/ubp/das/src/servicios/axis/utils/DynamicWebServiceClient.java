package ar.edu.ubp.das.src.servicios.axis.utils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.http.NameValuePair;


public class DynamicWebServiceClient {

	public SOAPMessage invokeOperation(ServiceDetail serviceDetail, String operationName, List<NameValuePair> parameters)
			throws SOAPException, IOException {

		System.out.println("[DynamicWebServiceClient]1");
		QName serviceQN = new QName(serviceDetail.getNameSpace(), serviceDetail.getServiceName());
		QName portQN = new QName(serviceDetail.getNameSpace(), serviceDetail.getPortName());

		Service service = Service.create(serviceQN);
		service.addPort(portQN, SOAPBinding.SOAP11HTTP_BINDING, serviceDetail.getWsdl());
		System.out.println("[DynamicWebServiceClient]Se creo el servicio");
		Operation foundOp = foundOperation(serviceDetail, operationName);
		if (foundOp == null) {
			throw new RuntimeException(serviceDetail.getWsdl() + " Not support operation: " + operationName);
		}

		Dispatch<SOAPMessage> dispatcher = service.createDispatch(portQN, SOAPMessage.class, Service.Mode.MESSAGE);
		System.out.println("[DynamicWebServiceClient]Post Dispatcher");

		MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
		SOAPMessage request = mf.createMessage();

		SOAPPart part = request.getSOAPPart();

		SOAPEnvelope env = part.getEnvelope();
		env.addNamespaceDeclaration(serviceDetail.getPrefix(), serviceDetail.getNameSpace());
		System.out.println("[DynamicWebServiceClient]POstNamespace");
		SOAPBody body = env.getBody();
		SOAPElement operation = body.addChildElement(operationName, serviceDetail.getPrefix());
		System.out.println("[DynamicWebServiceClient]foundOp: "+foundOp.getParameterNames());
		
		/*
		int argrIndex = 0;
		for (String arg : foundOp.getParameterNames()) {
			 SOAPElement value = operation.addChildElement(arg);
            value.addTextNode(args.get(argrIndex));
            argrIndex++;

		}*/
		System.out.println("Entrando al for de parametros");
		List<String> pn = new LinkedList<String>();
		if(parameters != null){
			for (NameValuePair nv : parameters){
				pn.add(nv.getName());
			}
		}
		foundOp.setParameterNames(pn);
		
		/*for (NameValuePair nv : parameters){
			System.out.println(nv.getName()+":"+nv.getValue());
			SOAPElement value = operation.addChildElement(nv.getName());
			value.addTextNode(nv.getValue());
		}
		*/ 
		 int argrIndex = 0;

		for (String arg : foundOp.getParameterNames()) {

            SOAPElement value = operation.addChildElement(arg);

            value.addTextNode(parameters.get(argrIndex).getValue());
            argrIndex++;

        }
		
		request.saveChanges();
		System.out.println("Request: " + SoapMessageUtil.outputSoapMessage(request));
		return dispatcher.invoke(request);

	}

	private Operation foundOperation(ServiceDetail serviceDetail, String operationName) {
		Operation operation = serviceDetail.getOperations().stream().filter(e -> e.getName().equals(operationName))
				.findFirst().orElse(null);
		return operation != null ? operation : null;
	}

}
