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
 * 2018-04-30T09:17:24.283-03:00
 * Generated source version: 3.1.7
 * 
 */
@WebServiceClient(name = "ConcesionariaRossoWSService", 
                  wsdlLocation = "http://localhost:9090/ConcesionariaRossoWSPort?wsdl",
                  targetNamespace = "http://ws.das.ubp.edu.ar/") 
public class ConcesionariaRossoWSService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.das.ubp.edu.ar/", "ConcesionariaRossoWSService");
    public final static QName ConcesionariaRossoWSPort = new QName("http://ws.das.ubp.edu.ar/", "ConcesionariaRossoWSPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9090/ConcesionariaRossoWSPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ConcesionariaRossoWSService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:9090/ConcesionariaRossoWSPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ConcesionariaRossoWSService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ConcesionariaRossoWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConcesionariaRossoWSService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ConcesionariaRossoWSService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ConcesionariaRossoWSService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ConcesionariaRossoWSService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ConcesionariaRossoWS
     */
    @WebEndpoint(name = "ConcesionariaRossoWSPort")
    public ConcesionariaRossoWS getConcesionariaRossoWSPort() {
        return super.getPort(ConcesionariaRossoWSPort, ConcesionariaRossoWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConcesionariaRossoWS
     */
    @WebEndpoint(name = "ConcesionariaRossoWSPort")
    public ConcesionariaRossoWS getConcesionariaRossoWSPort(WebServiceFeature... features) {
        return super.getPort(ConcesionariaRossoWSPort, ConcesionariaRossoWS.class, features);
    }

}
