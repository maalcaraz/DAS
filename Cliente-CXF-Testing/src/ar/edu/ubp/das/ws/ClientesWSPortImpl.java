
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package ar.edu.ubp.das.ws;

import java.util.logging.Logger;
// import javax.jws.WebMethod;
// import javax.jws.WebParam;
// import javax.jws.WebResult;
// import javax.jws.WebService;
// import javax.xml.bind.annotation.XmlSeeAlso;
// import javax.xml.ws.RequestWrapper;
// import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-04-23T23:20:24.981-03:00
 * Generated source version: 3.1.7
 * 
 */

@javax.jws.WebService(
                      serviceName = "ClientesWSService",
                      portName = "ClientesWSPort",
                      targetNamespace = "http://ws.das.ubp.edu.ar/",
                      wsdlLocation = "http://localhost:9090/ClientesWSPort?wsdl",
                      endpointInterface = "ar.edu.ubp.das.ws.ClientesWS")
                      
public class ClientesWSPortImpl implements ClientesWS {

    private static final Logger LOG = Logger.getLogger(ClientesWSPortImpl.class.getName());

    /* (non-Javadoc)
     * @see ar.edu.ubp.das.ws.ClientesWS#getCuentasClientes()*
     */
    public java.lang.String getCuentasClientes() throws Exception_Exception   { 
        LOG.info("Executing operation getCuentasClientes");
        try {
            java.lang.String _return = "_return-1671706027";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new Exception_Exception("Exception...");
    }

    /* (non-Javadoc)
     * @see ar.edu.ubp.das.ws.ClientesWS#verificarCancelado(java.lang.String arg0)*
     */
    public java.lang.String verificarCancelado(java.lang.String arg0) { 
        LOG.info("Executing operation verificarCancelado");
        System.out.println(arg0);
        try {
            java.lang.String _return = "_return1466938553";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see ar.edu.ubp.das.ws.ClientesWS#notificarGanador(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4)*
     */
    public java.lang.String notificarGanador(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4) { 
        LOG.info("Executing operation notificarGanador");
        System.out.println(arg0);
        System.out.println(arg1);
        System.out.println(arg2);
        System.out.println(arg3);
        System.out.println(arg4);
        try {
            java.lang.String _return = "_return-1120749549";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
