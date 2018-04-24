package ar.edu.ubp.das.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-04-23T23:20:25.008-03:00
 * Generated source version: 3.1.7
 * 
 */
@WebServiceClient(name = "ClientesWSService", 
                  wsdlLocation = "http://localhost:9090/ClientesWSPort?wsdl",
                  targetNamespace = "http://ws.das.ubp.edu.ar/") 
public class ClientesWSService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.das.ubp.edu.ar/", "ClientesWSService");
    public final static QName ClientesWSPort = new QName("http://ws.das.ubp.edu.ar/", "ClientesWSPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9090/ClientesWSPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ClientesWSService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:9090/ClientesWSPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ClientesWSService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ClientesWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ClientesWSService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ClientesWSService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ClientesWSService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ClientesWSService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ClientesWS
     */
    @WebEndpoint(name = "ClientesWSPort")
    public ClientesWS getClientesWSPort() {
        return super.getPort(ClientesWSPort, ClientesWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ClientesWS
     */
    @WebEndpoint(name = "ClientesWSPort")
    public ClientesWS getClientesWSPort(WebServiceFeature... features) {
        return super.getPort(ClientesWSPort, ClientesWS.class, features);
    }

}
