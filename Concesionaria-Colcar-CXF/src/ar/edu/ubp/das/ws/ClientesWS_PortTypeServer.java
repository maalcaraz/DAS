
package ar.edu.ubp.das.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2018-04-24T20:39:12.694-03:00
 * Generated source version: 3.1.7
 * 
 */
 
public class ClientesWS_PortTypeServer{

    protected ClientesWS_PortTypeServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new ar.edu.ubp.das.ws.ClientesWS();
        String address = "http://localhost:9090/ClientesWSPort";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception { 
        new ClientesWS_PortTypeServer();
        System.out.println("Server ready..."); 
     }
}
 
 