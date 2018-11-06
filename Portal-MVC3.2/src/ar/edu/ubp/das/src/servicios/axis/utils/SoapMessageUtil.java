package ar.edu.ubp.das.src.servicios.axis.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SoapMessageUtil {

	public static String outputSoapMessage(SOAPMessage msg) throws SOAPException, IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		msg.writeTo(out);
		return new String(out.toByteArray());		
	}
}
