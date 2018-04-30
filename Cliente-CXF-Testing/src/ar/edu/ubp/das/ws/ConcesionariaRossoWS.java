package ar.edu.ubp.das.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-04-30T09:17:24.272-03:00
 * Generated source version: 3.1.7
 * 
 */
@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", name = "ConcesionariaRossoWS")
@XmlSeeAlso({ObjectFactory.class})
public interface ConcesionariaRossoWS {

    @WebMethod(action = "urn:GetClientes")
    @RequestWrapper(localName = "getClientes", targetNamespace = "http://ws.das.ubp.edu.ar/", className = "ar.edu.ubp.das.ws.GetClientes")
    @ResponseWrapper(localName = "getClientesResponse", targetNamespace = "http://ws.das.ubp.edu.ar/", className = "ar.edu.ubp.das.ws.GetClientesResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String getClientes() throws Exception_Exception;

    @WebMethod(action = "urn:VerificarCancelado")
    @RequestWrapper(localName = "verificarCancelado", targetNamespace = "http://ws.das.ubp.edu.ar/", className = "ar.edu.ubp.das.ws.VerificarCancelado")
    @ResponseWrapper(localName = "verificarCanceladoResponse", targetNamespace = "http://ws.das.ubp.edu.ar/", className = "ar.edu.ubp.das.ws.VerificarCanceladoResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String verificarCancelado(
        @WebParam(name = "dni_cliente", targetNamespace = "")
        java.lang.String dniCliente,
        @WebParam(name = "id_plan", targetNamespace = "")
        java.lang.String idPlan
    ) throws Exception_Exception;

    @WebMethod(action = "urn:NotificarGanador")
    @RequestWrapper(localName = "notificarGanador", targetNamespace = "http://ws.das.ubp.edu.ar/", className = "ar.edu.ubp.das.ws.NotificarGanador")
    @ResponseWrapper(localName = "notificarGanadorResponse", targetNamespace = "http://ws.das.ubp.edu.ar/", className = "ar.edu.ubp.das.ws.NotificarGanadorResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String notificarGanador(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    ) throws Exception_Exception;
}
