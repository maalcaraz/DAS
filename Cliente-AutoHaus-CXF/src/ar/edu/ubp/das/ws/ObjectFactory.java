
package ar.edu.ubp.das.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.edu.ubp.das.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetEstados_QNAME = new QName("http://ws.das.ubp.edu.ar/", "getEstados");
    private final static QName _GetEstadosResponse_QNAME = new QName("http://ws.das.ubp.edu.ar/", "getEstadosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.edu.ubp.das.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEstados }
     * 
     */
    public GetEstados createGetEstados() {
        return new GetEstados();
    }

    /**
     * Create an instance of {@link GetEstadosResponse }
     * 
     */
    public GetEstadosResponse createGetEstadosResponse() {
        return new GetEstadosResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEstados }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "getEstados")
    public JAXBElement<GetEstados> createGetEstados(GetEstados value) {
        return new JAXBElement<GetEstados>(_GetEstados_QNAME, GetEstados.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEstadosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "getEstadosResponse")
    public JAXBElement<GetEstadosResponse> createGetEstadosResponse(GetEstadosResponse value) {
        return new JAXBElement<GetEstadosResponse>(_GetEstadosResponse_QNAME, GetEstadosResponse.class, null, value);
    }

}
