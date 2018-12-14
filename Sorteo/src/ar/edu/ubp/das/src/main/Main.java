package ar.edu.ubp.das.src.main;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSGanadoresDao;

public class Main {

	public static void main (String[] args){
		int intentos = 0;
		boolean notificar = false;
		List<Bean> participantes = null;
		OperacionesSorteo opsSorteo = new OperacionesSorteo();
		SorteoBean sorteoActual = null;
		AdquiridoBean ganador = null;
		
		/*
		 * Operacion para verificar si se cancelo el ultimo ganador de un sorteo
		 */
		AdquiridoBean ultimoGanador = opsSorteo.verificarCancelado();
		/*
		 * Bean para representar el sorteo que se va a ejecutar. Puede ser uno nuevo o uno pendiente.
		 */
		boolean abortarSorteo = false;
		/* 
		 * AbortarSorteo: variable a utilizar para chequear estado de ejecucion
		 * y eventualmente guardar sorteo como pendiente
		 */
		sorteoActual = opsSorteo.consultarPendientes();
		/*
		 * consultarPendientes: Funciona que consulta por sorteos pendientes
		 */
		if(sorteoActual == null){
			System.out.println("[Main:41]No hay sorteos pendientes. Procedemos a consultar si hoy es fecha de sorteo...");
			/*
			 * obtenerSorteoHoy: funcion que verifica si hoy es fecha de sorteo o no
			 */
			sorteoActual = opsSorteo.obtenerSorteoHoy();
			if(sorteoActual == null)
			{
				System.out.println("[Main:48]Hoy no es fecha de sorteo. Cancelando ejecucion...");
				/*
				 * Como hoy no es fecha de sorteo, el programa termina.
				 */
				abortarSorteo = true; // esto quedaria de mas
				System.exit(0);
			}
		}
		else
		{
			/*
			 * SorteoActual = sorteoPendiente
			 */
			System.out.println("[Main:61]Hay un sorteo pendiente. Procedemos a verificar la razon...");
			System.out.println("[Main:62]El sorteo pendiente tiene los datos: "+ sorteoActual.getIdSorteo() +", razon:"+ sorteoActual.getRazon());
			/*
			 * Chequeando por que se puso como pendiente
			 */
			Gson gson = new Gson();
			String r = sorteoActual.getRazon();
			LinkedList<NameValuePair> razonPendiente = gson.fromJson(r, new TypeToken<LinkedList<BasicNameValuePair>>(){}.getType() );
			String operacionFallida = razonPendiente.get(0).getValue(); // operacion que fallo
			intentos = Integer.parseInt(razonPendiente.get(1).getValue()) ; // cantidad de intentos
			
			if (intentos >= 3){
				System.out.println("[Main:75]Se alcanzo el nro maximo de intentos. Cancelamos la fecha de sorteo");
				System.exit(-1); // Revisar si cortaria asi.
			}
			
			if (operacionFallida.equals("NotificarGanador")){
				/*
				 * Si la operacion fallida es notificarGanador, quiere decir que en el sorteoPendiente deberia tener
				 * la fecha de ejecucion, pero no la fecha de notificar
				 * 
				 */
				
				//abortarSorteo = true;
				notificar = true;
				if (ultimoGanador == null ){//opsSorteo.verificarCancelado() == null){
					//ganador = null;
					System.out.println("[Main:84]No se pudo obtener el ganador");
					intentos++;
					LinkedList<NameValuePair> nvp = new LinkedList<NameValuePair>();
					nvp.add(new BasicNameValuePair("operacion", "NotificarGanador"));
					nvp.add(new BasicNameValuePair("intentos", Integer.toString(intentos)));
					System.out.println("[Main]Razon de dejar el sorteo como pendiente: "+nvp);
					
					String jsonRazon = gson.toJson(nvp);
					opsSorteo.cambiarValorPendienteSorteo(sorteoActual, jsonRazon, true);
					//abortarSorteo = true;
					System.exit(0);
				}
				else{
					//ganador = opsSorteo.verificarCancelado();
				}
			}
		}
		
		if((abortarSorteo == false))
		{
			/* 
			 * En la condicion se pregunta con OR porque si no hay aun ganadores registrados
			 * se procede igual con la ejecucion del sorteo
			 */
			if((ultimoGanador == null) || (ultimoGanador.getCancelado().equals("true"))){ 
				/* Preguntar por ultimoGanador.empty() */
				System.out.println("[Main:113]Ultimo ganador cancelado. Podemos proceder con el sorteo");
			}
			else
			{
				/* 
				 * La cancelacion esta pendiente. Hay que invocar a NotificaGanador para la concesionaria  
				 * correspondiente.
				 */
				System.out.println("[SorteoActual fecha:]"+sorteoActual.getFechaEjecucion());
				ultimoGanador.setFechaSorteado(sorteoActual.getFechaEjecucion());
				boolean notificacion = opsSorteo.NotificarGanador(ultimoGanador); 
				if (!notificacion){
					intentos++;
					String jsonRazon = opsSorteo.setearRazon("NotificarGanador", intentos);
					opsSorteo.cambiarValorPendienteSorteo(sorteoActual, jsonRazon, true);
					//abortarSorteo = true;
					System.exit(0);
				}
				else {
					String idRazon = "El sorteo completo la etapa de notificacion";
					
					Date fechaNotificacion = new Date();
					SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
					sorteoActual.setFechaNotificacion(parser.format(fechaNotificacion));
					opsSorteo.cambiarValorPendienteSorteo(sorteoActual, idRazon, false);
					abortarSorteo = true;
					notificar = false;
				}
			}
		}
		
		if((abortarSorteo == false) && (notificar == false)){
			/*
			 * Operacion para consultar los datos de cada concesionaria y traer los clientes que cumplen las
			 * condiciones para participar del sorteo
			 */
			participantes = opsSorteo.consultaConcesionarias();
			if(participantes != null && !participantes.isEmpty()){
				System.out.println("[Main:143]Procedemos a sortear...");
			}
			else{    
				/* 
				 * Como todo el procesamiento de los participantes esta en consultaConcesionarias,
				 * no es posible saber por que pudo haber fallado la consulta, y por lo tanto se setea 
				 * el sorteo como pendiente y se aborta la ejecucion.
				 */ 
				intentos++;
				String jsonRazon = opsSorteo.setearRazon("ObtenerParticipantes", intentos);
				opsSorteo.cambiarValorPendienteSorteo(sorteoActual, jsonRazon, true);
				//abortarSorteo = true;
				System.exit(0);
			}
		}
		
		if((abortarSorteo == false)&& (notificar == false))
		{
			/*
			 * EJECUCION DEL SORTEO
			 */
			System.out.println("[Main:167]Ejecucion del sorteo");
			int iGanador = (int) (Math.random() * participantes.size());
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			ParticipanteBean participanteGanador = (ParticipanteBean)participantes.get(iGanador);
			System.out.println( "[Main:171]El ganador es " + participanteGanador.getDniCliente());
			
			Date fechaEjecucion = new Date();
			SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
			sorteoActual.setFechaEjecucion(parser.format(fechaEjecucion)); 
			
			System.out.println("[Main]La fecha de ejecucion del sorteo es: "+parser.format(fechaEjecucion));
			ganador = new AdquiridoBean();
			ganador.setDniCliente(participanteGanador.getDniCliente());
			ganador.setIdConcesionaria(participanteGanador.getIdConcesionaria());
			ganador.setIdPlan(participanteGanador.getIdPlan());
			ganador.setFechaSorteado(sorteoActual.getFechaEjecucion());
			
			// aca tambien tendriamos que poner notificar = true?
			
			/*Registro del ganador en la base de datos local */
			try {
				
				MSGanadoresDao Ganador = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
				participanteGanador.setIdSorteo(sorteoActual.getIdSorteo());
				Ganador.insert(participanteGanador);
				notificar = true;
			} 
			catch (SQLException e) {
				System.out.println("[Main:191]No se pudo registrar el ganador en la base de datos local: "+ e.getMessage());
				intentos++;
				String jsonRazon = opsSorteo.setearRazon("RegistrarGanador", intentos);
				opsSorteo.cambiarValorPendienteSorteo(sorteoActual, jsonRazon, true);
				System.exit(0);
			}
			
			/*Actualizacion de datos del sorteo: Seteo de fecha de ejecucion*/
			
		}
		if (notificar == true){
			if(opsSorteo.NotificarGanador(ganador) == true){
				System.out.println("[Main:209]Seteamos sorteo como NO pendiente...");
				Date fechaNotificacion = new Date();
				SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
				sorteoActual.setFechaNotificacion(parser.format(fechaNotificacion));
				
				opsSorteo.cambiarValorPendienteSorteo(sorteoActual, "[Main]El sorteo se ejecuto correctamente.", false);
			}
			else{
				System.out.println("[Main:213]Seteamos sorteo como pendiente porque no se pudo notificar el ganador con exito...");
				intentos++;
				/*
				 * Setea desde donde retomar y cuantos intentos van
				 */
				//String razon = "{ operacion: NotificarGanador, intentos:" +Integer.toString(intentos) + "}";
				
				String jsonRazon = opsSorteo.setearRazon("NotificarGanador", intentos);
				opsSorteo.cambiarValorPendienteSorteo(sorteoActual, jsonRazon, true); // Esta linea queda al pedo si no se corta el programa inmediatamente despues
				// abortarSorteo = true;
				System.exit(0);
			}
		}
		
		/*
		 * Este chequeo debe ir al final del Main para guardar el sorteo como pendiente
		 * en caso de que el proceso haya fallado en alguno momento.
		 */
		if(abortarSorteo == true && sorteoActual != null ){
			//System.out.println("[Main]Seteamos sorteo como pendiente...");
			//opsSorteo.cambiarValorPendienteSorteo(sorteoActual, "[Main]El sorteo no cumple las condiciones para ser ejecutado.", true);
		}
		System.out.println("[Main]Adios mundo");
	}	
}