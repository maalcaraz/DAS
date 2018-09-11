package ar.edu.ubp.das.src.main;

import java.util.List;

import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;

public class Main {

	public static void main (String[] args){
		
		OpsSorteo opsSorteo = new OpsSorteo();
		/*
		 * Bean para representar el sorteo que se va a ejecutar.
		 * puede ser uno nuevo o uno pendiente.
		 */
		
		SorteoBean sorteoActual = null;
		
		List<Bean> sorteosPendientes = opsSorteo.consultarPendientes();
		
		if(sorteosPendientes != null && !sorteosPendientes.isEmpty()){
			
			/*
			 * Por ahora agarramos el primero por que es el mas viejo tenemos que definir
			 * si procesamos esta lista o que
			 */
			sorteoActual = (SorteoBean)sorteosPendientes.get(0);
			
			System.out.println("procedemos a sortear...");
		}
		else
		{
			
		}
		
		
		
		/*
		 * Operacion para verificar si se cancelo el ultimo ganador de un sorteo
		 */
		boolean cancelado = opsSorteo.verificarCancelado();
		
		if(cancelado){
			System.out.println("cancelado");
		}
		
		/*
		 * Operacion para consultar cada concesionaria dependiendo 
		 */
		
		List<Bean> participantes = opsSorteo.consultaConcesionarias();
		
		if(participantes != null && !participantes.isEmpty()){
			System.out.println("procedemos a sortear...");
		}
		
		
		
				
		System.out.println("Hola mundo");
	}
	void registrarPendiente (String idSorteo, String razon){
		// MSSorteosDao Sorteos = new MSSorteosDao();
		// Sorteos
	}
	
}
