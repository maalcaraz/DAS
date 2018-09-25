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
			System.out.println("No hay sorteos pendientes. Procedemos a consultar si hoy es fecha de sorteo...");
			
			sorteoActual = opsSorteo.obtenerSorteoHoy();
			
			if(sorteoActual == null)
			{
				System.out.println("Hoy no es fecha de sorteo. Cancelando ejecucion...");
				abortarSorteo = true;
			}
		}
		else
		{
			// Para esta altura sorteoActual no es null y ya tiene el sorteo pendiente
			System.out.println("Hay sorteo pendiente. Procedemos a ejecutarlo...");
		}
		
		if(abortarSorteo == false)
		{
			/*
			 * Operacion para verificar si se cancelo el ultimo ganador de un sorteo
			 */
			AdquiridoBean ultimoGanador = opsSorteo.verificarCancelado();
			
			if(ultimoGanador.getCancelado().equals("true")){
				System.out.println("Ultimo ganador cancelado. Podemos proceder con el sorteo");
			}
			else
			{
				//Tenemos que notificar cancelacion pendiente
				// Algunos argumentos pendientes, chequear funcionamiento
				opsSorteo.informarCancelacionPendiente(ultimoGanador);
				abortarSorteo = true;
			}
			
		}

		/*
		 * Operacion para consultar cada concesionaria
		 */
		
		if(abortarSorteo == false){
			
			
			participantes = opsSorteo.consultaConcesionarias();
			if(participantes != null && !participantes.isEmpty()){
				System.out.println("procedemos a sortear...");
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
			
			int ganador = (int) (Math.random() * participantes.size());
			
			ParticipanteBean ganadorBean = (ParticipanteBean)participantes.get(ganador);
			
			System.out.println( "\n>> El ganador es " +
					ganadorBean.getDniCliente());
			
		}
		
		
		
		/*
		 * Este chequeo debe ir al final del Main para guardar el sorteo como pendiente
		 * en caso de que el proceso haya fallado en alguno momento.
		 */
		if(abortarSorteo == true && sorteoActual != null ){
			System.out.println("Seteamos sorteo como pendiente...");
			opsSorteo.registrarSorteoPendiente(sorteoActual, "El sorteo no cumple las condiciones para ser ejecutado.");
		}
		System.out.println("Adios mundo");
	}	
}