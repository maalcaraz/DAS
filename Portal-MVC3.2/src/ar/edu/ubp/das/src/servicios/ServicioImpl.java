package ar.edu.ubp.das.src.servicios;

public abstract class ServicioImpl implements Servicio{
	
	private String url;
	private String tecnologia;
	private String nomConcesionaria;
	private String puerto;
	
	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}

	public String getNomConcesionaria() {
		return nomConcesionaria;
	}

	public void setNomConcesionaria(String nomConcesionaria) {
		this.nomConcesionaria = nomConcesionaria;
	}

}
