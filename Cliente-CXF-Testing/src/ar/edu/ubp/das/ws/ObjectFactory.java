
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

    private final static QName _GetClientes_QNAME = new QName("http://ws.das.ubp.edu.ar/", "getClientes");
    private final static QName _GetClientesResponse_QNAME = new QName("http://ws.das.ubp.edu.ar/", "getClientesResponse");
    private final static QName _NotificarGanador_QNAME = new QName("http://ws.das.ubp.edu.ar/", "notificarGanador");
    private final static QName _NotificarGanadorResponse_QNAME = new QName("http://ws.das.ubp.edu.ar/", "notificarGanadorResponse");
    private final static QName _VerificarCancelado_QNAME = new QName("http://ws.das.ubp.edu.ar/", "verificarCancelado");
    private final static QName _VerificarCanceladoResponse_QNAME = new QName("http://ws.das.ubp.edu.ar/", "verificarCanceladoResponse");
    private final static QName _Exception_QNAME = new QName("http://ws.das.ubp.edu.ar/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.edu.ubp.das.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClientes }
     * 
     */
    public GetClientes createGetClientes() {
        return new GetClientes();
    }

    /**
     * Create an instance of {@link GetClientesResponse }
     * 
     */
    public GetClientesResponse createGetClientesResponse() {
        return new GetClientesResponse();
    }

    /**
     * Create an instance of {@link NotificarGanador }
     * 
     */
    public NotificarGanador createNotificarGanador() {
        return new NotificarGanador();
    }

    /**
     * Create an instance of {@link NotificarGanadorResponse }
     * 
     */
    public NotificarGanadorResponse createNotificarGanadorResponse() {
        return new NotificarGanadorResponse();
    }

    /**
     * Create an instance of {@link VerificarCancelado }
     * 
     */
    public VerificarCancelado createVerificarCancelado() {
        return new VerificarCancelado();
    }

    /**
     * Create an instance of {@link VerificarCanceladoResponse }
     * 
     */
    public VerificarCanceladoResponse createVerificarCanceladoResponse() {
        return new VerificarCanceladoResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "getClientes")
    public JAXBElement<GetClientes> createGetClientes(GetClientes value) {
        return new JAXBElement<GetClientes>(_GetClientes_QNAME, GetClientes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "getClientesResponse")
    public JAXBElement<GetClientesResponse> createGetClientesResponse(GetClientesResponse value) {
        return new JAXBElement<GetClientesResponse>(_GetClientesResponse_QNAME, GetClientesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificarGanador }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "notificarGanador")
    public JAXBElement<NotificarGanador> createNotificarGanador(NotificarGanador value) {
        return new JAXBElement<NotificarGanador>(_NotificarGanador_QNAME, NotificarGanador.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificarGanadorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "notificarGanadorResponse")
    public JAXBElement<NotificarGanadorResponse> createNotificarGanadorResponse(NotificarGanadorResponse value) {
        return new JAXBElement<NotificarGanadorResponse>(_NotificarGanadorResponse_QNAME, NotificarGanadorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificarCancelado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "verificarCancelado")
    public JAXBElement<VerificarCancelado> createVerificarCancelado(VerificarCancelado value) {
        return new JAXBElement<VerificarCancelado>(_VerificarCancelado_QNAME, VerificarCancelado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificarCanceladoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "verificarCanceladoResponse")
    public JAXBElement<VerificarCanceladoResponse> createVerificarCanceladoResponse(VerificarCanceladoResponse value) {
        return new JAXBElement<VerificarCanceladoResponse>(_VerificarCanceladoResponse_QNAME, VerificarCanceladoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.das.ubp.edu.ar/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
