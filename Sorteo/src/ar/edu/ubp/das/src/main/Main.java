package ar.edu.ubp.das.src.main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSGanadoresDao;

public class Main {

	public static void main (String[] args){
		
		List<Bean> participantes = null;
		OperacionesSorteo opsSorteo = new OperacionesSorteo();
		/*
		 * Bean para representar el sorteo que se va a ejecutar. Puede ser uno nuevo o uno pendiente.
		 */
		SorteoBean sorteoActual = null;
		/* 
		 * AbortarSorteo: variable a utilizar para chequear estado de ejecucion
		 * y eventualmente guardar sorteo como pendiente
		 */
		boolean abortarSorteo = false;
		/*
		 * consultarPendientes: Funciona que consulta por sorteos pendientes
		 */
		sorteoActual = opsSorteo.consultarPendientes();
		if(sorteoActual == null){
			System.out.println("[Main]No hay sorteos pendientes. Procedemos a consultar si hoy es fecha de sorteo...");
			/*
			 * obtenerSorteoHoy: funcion que verifica si hoy es fecha de sorteo o no
			 */
			sorteoActual = opsSorteo.obtenerSorteoHoy();
			
			if(sorteoActual == null)
			{
				System.out.println("[Main]Hoy no es fecha de sorteo. Cancelando ejecucion...");
				/*
				 * Como hoy no es fecha de sorteo, el programa termina.
				 */
				abortarSorteo = true;
				//System.out.println("[Main]El error en ejecucion da porque el programa en este punto ya deberia terminarse...");
			}
		}
		else
		{
			/*
			 * Para esta altura sorteoActual no es null y ya tiene el sorteo pendiente
			 */
			System.out.println("[Main]Hay un sorteo pendiente. Procedemos a ejecutarlo...");
		}
		
		if(abortarSorteo == false)
		{
			/*
			 * Operacion para verificar si se cancelo el ultimo ganador de un sorteo
			 */
			AdquiridoBean ultimoGanador = opsSorteo.verificarCancelado();
			
			/* 
			 * En la condicion se pregunta con OR porque si no hay aun ganadores registrados
			 * se procede igual con la ejecucion del sorteo
			 */
			if( (ultimoGanador == null) || (ultimoGanador.getCancelado().equals("true"))){ 
				/* Preguntar por ultimoGanador.empty() */
				System.out.println("[Main]Ultimo ganador cancelado. Podemos proceder con el sorteo");
			}
			else
			{
				/* 
				 * La cancelacion esta pendiente. Hay que invocar a NotificaGanador para la concesionaria  
				 * correspondiente.
				 */
				opsSorteo.NotificarGanador(ultimoGanador); // CAMBIAR
				abortarSorteo = true;
			}
		}
		
		
		if(abortarSorteo == false){
			/*
			 * Operacion para consultar los datos de cada concesionaria y traer los clientes que cumplen las
			 * condiciones para participar del sorteo
			 */
			participantes = opsSorteo.consultaConcesionarias();
			if(participantes != null && !participantes.isEmpty()){
				System.out.println("[Main]Procedemos a sortear...");
			}
			else
			{
				/* 
				 * Como todo el procesamiento de los participantes esta en consultaConcesionarias,
				 * no es posible saber por que pudo haber fallado la consulta, y por lo tanto se setea 
				 * el sorteo como pendiente y se aborta la ejecucion.
				 */
				abortarSorteo = true;
			}
		}
		if(abortarSorteo == false){
			/*
			 * EJECUCION DEL SORTEO
			 */
			System.out.println("[Main]Ejecucion del sorteo");
			int iGanador = (int) (Math.random() * participantes.size());
			
			ParticipanteBean participanteGanador = (ParticipanteBean)participantes.get(iGanador);
			
			System.out.println( "[Main]El ganador es " + participanteGanador.getDniCliente());
			Date fechaEjecucion = new Date();
			SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
			sorteoActual.setFechaEjecucion(parser.format(fechaEjecucion)); 
			
			AdquiridoBean ganador = new AdquiridoBean();
			ganador.setDniCliente(participanteGanador.getDniCliente());
			ganador.setIdConcesionaria(participanteGanador.getIdConcesionaria());
			ganador.setIdPlan(participanteGanador.getIdPlan());
			ganador.setFechaSorteado(sorteoActual.getFechaEjecucion());
			
			//ganador.setIdSorteo(sorteoActual.getIdSorteo());
			/*Registro del ganador en la base de datos local */
			try {
				MSGanadoresDao Ganador = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
				participanteGanador.setIdSorteo(sorteoActual.getIdSorteo());
				Ganador.insert(participanteGanador);
			} 
			catch (SQLException e) {
				System.out.println("ERROR --> [Main]No se pudo registrar el ganador en la base de datos local: "+ e.getMessage());
				// errores++?
			}
			
			/*Actualizacion de datos del sorteo: Seteo de fecha de ejecucion*/
			 
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