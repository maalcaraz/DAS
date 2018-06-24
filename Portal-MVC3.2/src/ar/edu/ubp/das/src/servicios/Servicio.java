package ar.edu.ubp.das.src.servicios;

import java.util.List;

import org.apache.http.NameValuePair;

public interface Servicio {

	public abstract String Consumir(String operacion, List <NameValuePair> parameters);
	public String getPuerto();
	public String getNomConcesionaria();
	public void setUrl(String url);
	public String getUrl();
	

}
