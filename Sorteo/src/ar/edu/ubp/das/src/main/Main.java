package ar.edu.ubp.das.src.main;

import java.util.List;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;

public class Main {

	public static void main (String[] args){
		
		List<Bean> participantes = null;
		OperacionesSorteo opsSorteo = new OperacionesSorteo();
		/*
		 * Bean para representar el sorteo que se va a ejecutar. Puede ser uno nuevo o uno pendiente.
		 */

		SorteoBean sorteoActual = null;
		/* 
		 * AbortarSorteo variable a utilizar para chequear estado de ejecucion
		 * y eventualmente guardar sorteo como pendiente
		 */
		boolean abortarSorteo = false;
		
		sorteoActual = opsSorteo.consultarPendientes();
		
		if(sorteoActual == null){
			System.out.println("[Main]No hay sorteos pendientes. Procedemos a consultar si hoy es fecha de sorteo...");
			
			sorteoActual = opsSorteo.obtenerSorteoHoy();
			
			if(sorteoActual == null)
			{
				System.out.println("[Main]Hoy no es fecha de sorteo. Cancelando ejecucion...");
				abortarSorteo = true;
				//System.out.println("[Main]El error en ejecucion da porque el programa en este punto ya deberia terminarse...");
			}
		}
		else
		{
			// Para esta altura sorteoActual no es null y ya tiene el sorteo pendiente
			System.out.println("[Main]Hay sorteo pendiente. Procedemos a ejecutarlo...");
		}
		
		if(abortarSorteo == false)
		{
			/*
			 * Operacion para verificar si se cancelo el ultimo ganador de un sorteo
			 */
			AdquiridoBean ultimoGanador = opsSorteo.verificarCancelado();
			
			/*En la condicion se pregunta con OR porque si no hay aun ganadores registrados
			 * se procede igual con la ejecucion del sorteo
			 * 
			 * */
			if( (ultimoGanador == null) || (ultimoGanador.getCancelado().equals("true"))){ /*Preguntar por ultimoGanador.empty()*/
				System.out.println("[Main]Ultimo ganador cancelado. Podemos proceder con el sorteo");
			}
			else
			{
				//Tenemos que notificar cancelacion pendiente
				// Algunos argumentos pendientes, chequear funcionamiento
				opsSorteo.NotificarGanador(ultimoGanador);
				abortarSorteo = true;
			}
			
		}

		/*
		 * Operacion para consultar cada concesionaria
		 */
		
		if(abortarSorteo == false){
			
			participantes = opsSorteo.consultaConcesionarias();
			if(participantes != null && !participantes.isEmpty()){
				System.out.println("[Main]Procedemos a sortear...");
			}
			else
			{
				/*como todo el procesamiento lo hacemos en la funcion aca ponemos pendiente pero no sabemos bien
				 * por que. En este caso hay que modificarlo
				 */
				abortarSorteo = true;
			}
		}
		
		
		if(abortarSorteo == false){
			// Ejecucion del sorteo
			System.out.println("[Main]Ejecucion del sorteo");
			int iGanador = (int) (Math.random() * participantes.size());
			
			ParticipanteBean participanteGanador = (ParticipanteBean)participantes.get(iGanador);
			
			System.out.println( "[Main]El ganador es " + participanteGanador.getDniCliente());
			
			AdquiridoBean ganador = new AdquiridoBean();
			ganador.setDniCliente(participanteGanador.getDniCliente());
			ganador.setIdConcesionaria(participanteGanador.getIdConcesionaria());
			opsSorteo.NotificarGanador(ganador);
			System.out.println("[Main]Seteamos sorteo como NO pendiente...");
			opsSorteo.cambiarValorPendienteSorteo(sorteoActual, "[Main]El sorteo se ejecuto correctamente.", false);
		}
		/*
		 * Este chequeo debe ir al final del Main para guardar el sorteo como pendiente
		 * en caso de que el proceso haya fallado en alguno momento.
		 */
		if(abortarSorteo == true && sorteoActual != null ){
			System.out.println("[Main]Seteamos sorteo como pendiente...");
			opsSorteo.cambiarValorPendienteSorteo(sorteoActual, "[Main]El sorteo no cumple las condiciones para ser ejecutado.", true);
		}
		System.out.println("[Main]Adios mundo");
	}	
}