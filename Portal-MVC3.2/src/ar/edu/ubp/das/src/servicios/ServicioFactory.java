package ar.edu.ubp.das.src.servicios;



public class ServicioFactory {

	private ServicioFactory(){}
	
	public static Servicio getServicio(String nombreServicio) throws Exception, IllegalAccessException, ClassNotFoundException{
		
		/* Se manda como parametro el tipo de servicio que se va a crear
		 * Devuelve una instancia de ese servicio
		 * 
		 * */
		String nom = "Servicio"+nombreServicio;
		
		nom =  "ar.edu.ubp.das.src.servicios." + nom;
		ServicioImpl s = ServicioImpl.class.cast(Class.forName(nom).newInstance());
		return s;
	}
}
