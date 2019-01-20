package ar.edu.ubp.das.src.main;

import java.util.LinkedList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.sorteos.daos.MSGanadoresDao;

public class Main {

	public static ParticipanteBean ganador;
	public static SorteoBean sorteo;
	
	public static void main (String[] args){
		
		/**** Inicializacion de variables ****/
		
		OperacionesSorteo op = new OperacionesSorteo();
		int intentos = 0;
		boolean notificar = false;
		/*La consulta de concesionarias es obligatoria por defecto*/
		boolean consultar = true;
		boolean registrar = false;
		boolean cancelarGanador  = false;
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
				consultar = false; // esta linea puede ser que este al pedo
				sortear = false;
				break;
				
			case "ConsultarConcesionarias":
				consultar = true;
				sortear = true;
				break;
				
			case "RegistrarGanador":
				registrar = true;
				sortear = false;
				break;
				
			case "CancelarGanador":
				cancelarGanador = true;
				sortear = false;
				break;
			
			}	
		} /*---- Fin chequeo sorteo pendiente ----*/
		
		/* ---- Se cancelo el ultimo ganador? ---- */
		if (cancelarGanador){
			
			
			ganador = op.verificarCancelado();
			
			if (ganador == null){
				System.out.println("[Main]No sabemos si se cancelo el ultimo ganador");
				intentos++;
				/* ---- No sabemos si se cancelo el ultimo ganador ---- */
				op.cambiarValorPendienteSorteo(sorteo, op.setearRazon("CancelarGanador", intentos), true);
				notificar = false;
				sortear = false;
				consultar = false;
				registrar = false;
			}
			
		}
		/*--------------------------------------------*/
		
		/* ---- Hay concesionarias por consultar ---- */
		if (consultar){
			if (!op.consultaQuincenal()){
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

		if (notificar){
			/* ---- Hay concesionarias por notificar? ---- */
			
			
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
			
			if (!op.registrarGanador(ganador)){
				// registrar pendiente y setear razon = RegistroGanador
				intentos++;
				System.out.println("[Main]No se pudo registrar el ganador");
				op.cambiarValorPendienteSorteo(sorteo, op.setearRazon("RegistrarGanador", intentos), true);
				// notificar = false?
			}
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
