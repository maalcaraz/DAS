
package ar.edu.ubp.das.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-04-30T09:17:24.181-03:00
 * Generated source version: 3.1.7
 * 
 */
public final class ConcesionariaRossoWS_ConcesionariaRossoWSPort_Client {

    private static final QName SERVICE_NAME = new QName("http://ws.das.ubp.edu.ar/", "ConcesionariaRossoWSService");

    private ConcesionariaRossoWS_ConcesionariaRossoWSPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ConcesionariaRossoWSService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ConcesionariaRossoWSService ss = new ConcesionariaRossoWSService(wsdlURL, SERVICE_NAME);
        ConcesionariaRossoWS port = ss.getConcesionariaRossoWSPort();  
        
        {
        System.out.println("Invoking getClientes...");
        try {
            java.lang.String _getClientes__return = port.getClientes();
            System.out.println("getClientes.result=" + _getClientes__return);

        } catch (Exception_Exception e) { 
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking verificarCancelado...");
        java.lang.String _verificarCancelado_dniCliente = "_verificarCancelado_dniCliente1560929927";
        java.lang.String _verificarCancelado_idPlan = "_verificarCancelado_idPlan1798931082";
        try {
            java.lang.String _verificarCancelado__return = port.verificarCancelado(_verificarCancelado_dniCliente, _verificarCancelado_idPlan);
            System.out.println("verificarCancelado.result=" + _verificarCancelado__return);

        } catch (Exception_Exception e) { 
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking notificarGanador...");
        java.lang.String _notificarGanador_arg0 = "_notificarGanador_arg01432950794";
        java.lang.String _notificarGanador_arg1 = "_notificarGanador_arg11355476248";
        java.lang.String _notificarGanador_arg2 = "_notificarGanador_arg2997105537";
        java.lang.String _notificarGanador_arg3 = "_notificarGanador_arg31693811993";
        java.lang.String _notificarGanador_arg4 = "_notificarGanador_arg41834538160";
        try {
            java.lang.String _notificarGanador__return = port.notificarGanador(_notificarGanador_arg0, _notificarGanador_arg1, _notificarGanador_arg2, _notificarGanador_arg3, _notificarGanador_arg4);
            System.out.println("notificarGanador.result=" + _notificarGanador__return);

        } catch (Exception_Exception e) { 
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
