package ar.edu.ubp.das.src.main;

public class Main {

	public static void main (String[] args){
		
		
		/* 1. Consultar en la BD local si existen sorteos pendientes */
		
		/* existenPendientes = verificarPendientes();
		 * fechaDefinidaSorteo = consultarFechaDefinida();
		 * 
		 * if (existenPendientes || (fechaDefinida == hoy) ){
		 * 
		 * 
		 * 	   Traer de la bd el ganador del ultimo sorteo.
		 * 
		 	 * if (verificar cancelado (idconcesionaria, ganador) == true ){
				 * 
				 * MSConcesionariaDao bla;
				 * List<ConcesionariaForm> all = bla.select(null); // traemos todas las concesionarias
				 * 
				 * for (ConcesionariaForm concesionaria : all ){
				 * 
				 * 		if (concesionaria.ultimaActualizacion > 15 dias ){
				 * 			
				 			try {
					 			// llamar a consultaQuincenal
					 		* 	json jsonConsultaQuincenal = concesionaria.getWebService.Consumir("getClientes", null); 			
				 			}
				 			catch (Exception ex)
				 			{
				 			* 	Guardar sorteo como pendiente (ex.getMessage());
				 			*   return;
				 			}
				 			Concesionaria.update (json);
				 * 		}
				 * }
				 * ganador = sortear();
				 * 
				 * do {
						 * registroOk = portal.registrarGanador(ganador);
						 * 
						 * if (registroOk){
						 * 		conc = ganador.idConcesionaria;
						 * 		
						 * 		 
						 * 		String respuesta =	conc.getWebService("notificarGanador", ganador);
						 * 
						 * 		if (respuesta != OK){
						 * 			registrarPendiente(idSorteo, respuesta)
						 * 			return; // que vaya a fin
						 * 		}
						 * 
						 * }
						 * else {
						 * 		intentos++;
						 * }
				* }
				* while (intentos < 3 );
					 * if (intentos == 3 ){
					 * 		registrarPendiente(idSorteo, respuesta);
					 * 		return;
					 * }
				* }
			 * 
			 * } 
			 * else{
			 *  // el ultimo ganador no fue cancelado
			 *  
			 *  	concesionaria.webService.Consumir (notificarGanador (ultimoGanador));
			 *  	registrarPendiente (idSorteo, "falta cancelar ultimo ganador");
			 *  	return;
			 * }
		 * }
		 * else { // no existen sorteos pendientes o la fecha no es hoy
			 * 		
			 * }
		 * */
		
		System.out.println("Hola mundo");
	}
	void registrarPendiente (String idSorteo, String razon){
		// MSSorteosDao Sorteos = new MSSorteosDao();
		// Sorteos
	}
	
}
