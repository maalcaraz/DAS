
package ar.edu.ubp.das.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-05-08T19:23:06.390-03:00
 * Generated source version: 3.1.7
 * 
 */
 
public class ConcesionariaRossoWS_PortTypeServer{

    protected ConcesionariaRossoWS_PortTypeServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new ar.edu.ubp.das.ws.ConcesionariaRossoWS();
        String address = "http://localhost:9090/ConcesionariaRossoWSPort";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception { 
        new ConcesionariaRossoWS_PortTypeServer();
        System.out.println("Server ready..."); 
    }
}
 
 