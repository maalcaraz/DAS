package ar.edu.ubp.das.src.servicios.axis.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParseWsdlService {
	private static final String PORT = "wsdl:port";
	private static final String SERVICE = "wsdl:service";
	private static final String COMMENT = "#comment";
	private static final String TARGET_NAMESPACE = "targetNamespace";
	private static final String XML_SCHEMA_ATTR = "http://www.w3.org/2001/XMLSchema";
	private static final String OPERATION = "operation";
	private static final String OPERATION_1 = "wsdl:operation";
	private static final String ELEMENT_TAG = "xs:element";

	public ServiceDetail parse(String wsdlPath)
			throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		ServiceDetail sd = new ServiceDetail(wsdlPath);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(readWsdl(wsdlPath));

		removeComments(document, document.getChildNodes());

		String tagPrefix = getNamespacePrefix(document);
		if (tagPrefix != null) {
			sd.setPrefix(tagPrefix);
		}

		String nameSpace = getTargetNamespace(document);
		sd.setNameSpace(nameSpace);

		List<Operation> operations = getOperations(document);
		sd.setOperations(operations);

		String serviceName = getServiceName(document);
		sd.setServiceName(serviceName);

		String portName = getPortName(document);
		sd.setPortName(portName);
		return sd;
	}

	private String getServiceName(Document document) {
		String serviceName = null;
		if (document.getElementsByTagName(SERVICE).getLength() > 0) {
			NodeList nodeListOfService = document.getElementsByTagName(SERVICE);
			for (int i = 0; i < nodeListOfService.getLength(); i++) {
				Node serviceNode = nodeListOfService.item(i).getAttributes().getNamedItem("name");
				serviceName = serviceNode.getNodeValue();
			}
		}

		return serviceName;

	}

	private String getPortName(Document document) {
		String portName = null;
		if (document.getElementsByTagName(PORT).getLength() > 0) {
			NodeList nodeListOfService = document.getElementsByTagName(PORT);
			for (int i = 0; i < nodeListOfService.getLength(); i++) {
				Node portNode = nodeListOfService.item(i).getAttributes().getNamedItem("name");
				portName = portNode.getNodeValue();
			}
		}

		return portName;
	}

	private String getNamespacePrefix(Document document) {
		String tagPrefix = null;
		int l = document.getFirstChild().getAttributes().getLength();
		for (int i = 0; i < l; i++) {
			String cmpAttribute = document.getFirstChild().getAttributes().item(i).getNodeValue();
			if (cmpAttribute.equals(XML_SCHEMA_ATTR)) {
				tagPrefix = document.getFirstChild().getAttributes().item(i).getNodeName().replace("xmlns:", "");

			}
		}

		return tagPrefix;
	}

	private String getTargetNamespace(Document document) {
		return document.getFirstChild().getAttributes().getNamedItem(TARGET_NAMESPACE).getNodeValue();

	}

	private void removeComments(Document document, NodeList allNodesOfDocumnet) {
		for (int index = 0; index < allNodesOfDocumnet.getLength(); index++) {
			if (document.getFirstChild().getNodeName().equalsIgnoreCase(COMMENT)) {
				document.removeChild(document.getFirstChild());
			}
		}
	}

	private List<Operation> getOperations(Document document) {
		List<Operation> operations = new ArrayList<>();

		NodeList nodeListOfOperations = null;
		List<Node> operationsWithAttributes = new ArrayList<Node>();
		List<String> parametersList = null;
		NodeList prueba = null;
		
		String operacion = null;
		if ((document.getElementsByTagName(OPERATION).getLength() > 0)
				|| (document.getElementsByTagName(OPERATION_1).getLength() > 0)) {

			if (document.getElementsByTagName(OPERATION).getLength() > 0) {
				nodeListOfOperations = document.getElementsByTagName(OPERATION);
			} else if (document.getElementsByTagName(OPERATION_1).getLength() > 0) {
				nodeListOfOperations = document.getElementsByTagName(OPERATION_1);
			}

		}
		prueba = document.getElementsByTagName(ELEMENT_TAG);
		
		for (int i = 0; i < nodeListOfOperations.getLength(); i++) {
			operacion = nodeListOfOperations.item(i).getAttributes().getNamedItem("name").getNodeValue();
			
			for (int j = 0; j < prueba.getLength(); j++) {
				String operacionPrueba = (prueba.item(j).getAttributes().getNamedItem("name").getNodeValue());
				if( operacionPrueba.equals(operacion)
						&& !operationsWithAttributes.contains(prueba.item(j)))
				{
					operationsWithAttributes.add(prueba.item(j));
				}
			}
		}

		for (int i = 0; i < operationsWithAttributes.size(); i++) {
			
			NodeList nodesWithArguments = operationsWithAttributes.get(i).getChildNodes();
			parametersList = new ArrayList<String>();;
			for (int j = 0; j < nodesWithArguments.getLength(); j++) {
				//if(nodesWithArguments.item(j).hasAttributes() && nodesWithArguments.item(j).getAttributes().getNamedItem("name") != null)
				if(nodesWithArguments.item(j).getNodeType() == 1 && nodesWithArguments.item(j).hasAttributes())
				{
					parametersList.add(nodesWithArguments.item(j).getAttributes().getNamedItem("name").getNodeValue());
				}
			}
			Operation ope = new Operation(
					operationsWithAttributes.get(i).getAttributes().getNamedItem("name").getNodeValue(), parametersList);
			
			if (!operations.contains(ope)) {
				operations.add(ope);
			}
		}

		return operations;
	}

	private InputStream readWsdl(String wsdlUrl) throws IOException {
		URL url = new URL(wsdlUrl);
		URLConnection uc = url.openConnection();
		return uc.getInputStream();
	}
}
