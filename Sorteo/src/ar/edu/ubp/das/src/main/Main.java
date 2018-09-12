package ar.edu.ubp.das.src.main;

import java.util.List;

import ar.edu.ubp.das.src.db.Bean;

public class Main {

	public static void main (String[] args){
		
		OperacionesSorteo opsSorteo = new OperacionesSorteo();
		/*
		 * Bean para representar el sorteo que se va a ejecutar.
		 * puede ser uno nuevo o uno pendiente.
		 */
		/*
		SorteoBean sorteoActual = null;
		
		List<Bean> sorteosPendientes = opsSorteo.consultarPendientes();
		
		if(sorteosPendientes != null && !sorteosPendientes.isEmpty()){
		
			sorteoActual = (SorteoBean)sorteosPendientes.get(0);
			
			System.out.println("procedemos a sortear...");
		}
		else
		{
			
			
		}
		*/
		
		
		/*
		 * Operacion para verificar si se cancelo el ultimo ganador de un sorteo
		 */
		boolean cancelado = opsSorteo.verificarCancelado();
		
		if(cancelado){
			System.out.println("Verificar cancelado = true --> La cuenta de ese cliente esta cancelada.");
		}
		
		/*
		 * Operacion para consultar cada concesionaria
		 */
		
		List<Bean> participantes = opsSorteo.consultaConcesionarias();
		
		if(participantes != null && !participantes.isEmpty()){
			
			System.out.println("Procedemos a sortear, ya tenemos los participantes...");
		}
		
				
		System.out.println("Hola mundo");
	}	
}
