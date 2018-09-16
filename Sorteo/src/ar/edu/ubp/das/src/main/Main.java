package ar.edu.ubp.das.src.main;

import java.util.List;

import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;

public class Main {

	public static void main (String[] args){
		
		OperacionesSorteo opsSorteo = new OperacionesSorteo();
		/*
		 * Bean para representar el sorteo que se va a ejecutar.
		 * puede ser uno nuevo o uno pendiente.
		 */
		
		SorteoBean sorteoActual = null;
		
		sorteoActual = opsSorteo.consultarPendientes();
		
		if(sorteoActual == null){
			System.out.println("No hay sorteos pendientes. Procedemos a consultar si hoy es fecha de sorteo...");
			
			sorteoActual = opsSorteo.obtenerSorteoHoy();
			
			if(sorteoActual == null)
			{
				//Ver como salimos de aca
				System.out.println("Hoy no es fecha de sorteo. Cancelando ejecucion...");
			}
			
		}
		else
		{
			// Para esta altura sorteoActual no es null y ya tiene el sorteo pendiente
			System.out.println("Hay sorteo pendiente. Procedemos a ejecutarlo...");
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
