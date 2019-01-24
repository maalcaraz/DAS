package ar.edu.ubp.das.src.main;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.SorteoBean;

public class Main {

	public static ParticipanteBean ganador;
	public static SorteoBean sorteo;
	
	public static void main (String[] args){
		
		/**** Inicializacion de variables ****/
		
		OperacionesSorteo op = new OperacionesSorteo();
		int intentos = 0;
		boolean notificar = true;
		boolean consultar = true;
		boolean registrar = true;
		boolean cancelarGanador  = true;
		boolean sortear  = true;
		
		/***********************************/
		
		/****Primer condicion del flujo del programa****/
		sorteo = op.consultarSorteosPendientes();
		
		if (sorteo == null){
			System.out.println("[Main]No hay sorteos pendientes");
			
			/*---- No hay sorteos pendientes, verificamos si hoy es fecha de sorteo ----*/
		
			sorteo = op.hoyEsSorteo();
			if (sorteo == null){
				System.out.println("[Main]Hoy no es fecha de sorteo");
				sortear = false;
				notificar = false;
				consultar = false;
				registrar = false;
				cancelarGanador = false;
			}
			else{
				System.out.println("[Main]Hoy es fecha de sorteo");
			}
			
		}
		else{
			System.out.println("[Main]Hay sorteos pendientes");
			
			/****Extrayendo razon de sorteo pendiente ****/
			Gson gson = new Gson();
			LinkedList<NameValuePair> razonPendiente = gson.fromJson(sorteo.getRazon(), new TypeToken<LinkedList<BasicNameValuePair>>(){}.getType());
			String operacionFallida = razonPendiente.get(0).getValue(); // operacion que fallo
			intentos = Integer.parseInt(razonPendiente.get(1).getValue()) ; // cantidad de intentos
			
			
			if (intentos >= 3){
				System.out.println("[Main]Se alcanzo el nro maximo de intentos. Cancelamos la fecha de sorteo");
				System.exit(-1); // hay que setear las variables como si hoy no fuese fecha
			}
			
			switch (operacionFallida){
			
			case "NotificarGanador":
				notificar = true;
				consultar = false; 
				sortear = false;
				registrar = false;
				cancelarGanador = false; // habria que doblecheckearlo
				break;
				
			case "ConsultarConcesionarias":
				consultar = true;
				sortear = true;
				registrar = true;
				notificar = true;
				cancelarGanador = true;
				break;
				
			case "RegistrarGanador":
				registrar = true;
				sortear = false;
				notificar = true;
				consultar = false;
				cancelarGanador = true;
				break;
				
			case "CancelarGanador":
				cancelarGanador = true;
				sortear = false;
				notificar = true;
				consultar = false;
				registrar = false;
				break;
				
			case "VerificarCancelado":
				cancelarGanador = true;
				sortear = false;
				notificar = true;
				consultar = false;
				registrar = false;
				break;
			
			}	
		} /*---- Fin chequeo sorteo pendiente ----*/
		
		/* ---- Se cancelo el ultimo ganador? ---- */
		if (cancelarGanador){
			
			//ganador = op.verificarCancelado();
			
			List<AdquiridoBean> aux = op.obtenerGanadores();
			
			if (aux != null && aux.isEmpty()){
				// No hay ganadores registrados. Se sigue con el flujo del programa y no se verifica el cancelado
				sortear = true;
				notificar = true;
				consultar = true;
				registrar = true;
			}
			else {
				if (aux == null){
					// Salir del programa
					System.out.println("[Main]Fallo la consulta de ganadores en la BD local");
					System.exit(-1);
				}
				if (!aux.isEmpty()){
					// Hay que verificar si el ganador esta cancelado
					
					AdquiridoBean ad = op.verificarCancelado(aux.get(0));
					
					if (ad == null){
						// Fallo el consumo
						intentos++;
						op.cambiarValorPendienteSorteo(sorteo, op.setearRazon("VerificarCancelado", intentos), true);
						notificar = false;
						sortear = false;
						consultar = false;
						registrar = false;
					}
					else {
						if (ad.getCancelado().equals("true")){
							notificar = true;
							sortear = true;
							consultar = true;
							registrar = true;
						}
						else{
							// setear a ganador con 
							notificar = true;
							sortear = false;
							consultar = false;
							registrar = false;
						}
					}
				}		
			}
		}
		/*--------------------------------------------*/
		
		/* ---- Hay concesionarias por consultar ---- */
		if (consultar){
			if (op.consultaQuincenal()){
				System.out.println("[Main]La consulta de concesionarias fue exitosa");
				op.setearParticipantes(sorteo);
				
				sorteo.setParticipantesSorteo(op.seleccionarParticipantes());
				System.out.println("[Main]PARTICIPANTES: "+sorteo.getParticipantesSorteo());
				// La consulta fue exitosa
			}
			else {
				intentos++;
				System.out.println("[Main]Hay concesionarias pendiente de consulta");
				op.cambiarValorPendienteSorteo(sorteo, op.setearRazon("ConsultarConcesionarias", intentos), true);
				sortear = false;
			}
		}

		if (sortear){
			/* ---- Hay que ejecutar sorteo? ---- */
			// buscar los participantes aptos para el sorteo
			System.out.println("[Main]Ejecucion del sorteo");
			System.out.println("[Main]************** PRE EJECUCION **************");
			System.out.println("[Main]Listado de participantes del sorteo: "+ sorteo.getParticipantesSorteo());
			System.out.println("[Main]Ganador del sorteo: "+ ganador);
			System.out.println("[Main]*******************************************");
			sortear();

			System.out.println("[Main]************** POST EJECUCION **************");
			if (sorteo.getParticipantesSorteo() == null) {
				System.out.println("[Main]Aun no hay participantes para el sorteo");
			}
			else{
				System.out.println("[Main]Listado de participantes del sorteo: "+ sorteo.getParticipantesSorteo().toString());
				System.out.println("[Main]Ganador del sorteo: "+ ganador.getApellidoNombre());
				
				// registrar fecha de ejecucion del sorteo
				// op.cambiarValorPendienteSorteo(sorteo, idRazon, false);
				
			}
			System.out.println("[Main]********************************************");
		}
		
		if (registrar){
			/* ---- Quedo pendiente el registro del ganador? ---- */
			
			System.out.println("[Main] Registrando el ganador " + ganador.getApellidoNombre());
			
			if (!op.registrarGanador(ganador)){
				// registrar pendiente y setear razon = RegistroGanador
				intentos++;
				System.out.println("[Main]No se pudo registrar el ganador");
				op.cambiarValorPendienteSorteo(sorteo, op.setearRazon("RegistrarGanador", intentos), true);
				// notificar = false?
			}
		}
		
		if (notificar){
			/* ---- Hay concesionarias por notificar? ---- */
			
			
		}
		
		System.out.println("[Main]Adios mundo");
	}
	

	public static void sortear(){
		
		LinkedList<ParticipanteBean> participantes = sorteo.getParticipantesSorteo();
		if (!participantes.isEmpty()){
			int iGanador = (int) (Math.random() * participantes.size());
			System.out.println("[Main]Indice ganador: "+iGanador);
			ganador = (ParticipanteBean)participantes.get(iGanador);
		}
		else System.out.println("[Main] No hay participantes para el sorteo");
	}
	
}
